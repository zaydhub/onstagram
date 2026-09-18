package com.onstagram.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewAssetLoader;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        setContentView(webView);

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);

        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        WebViewAssetLoader assetLoader =
                new WebViewAssetLoader.Builder()
                        .addPathHandler(
                                "/assets/",
                                new WebViewAssetLoader.AssetsPathHandler(this)
                        )
                        .build();

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public WebResourceResponse shouldInterceptRequest(
                    WebView view,
                    WebResourceRequest request
            ) {
                return assetLoader.shouldInterceptRequest(
                        request.getUrl()
                );
            }

            @Override
            @SuppressWarnings("deprecation")
            public WebResourceResponse shouldInterceptRequest(
                    WebView view,
                    String url
            ) {
                return assetLoader.shouldInterceptRequest(
                        android.net.Uri.parse(url)
                );
            }
        });

        webView.addJavascriptInterface(
                new WebAppInterface(),
                "Android"
        );

        webView.loadUrl(
                "https://appassets.androidplatform.net/assets/index.html"
        );
    }

    private class WebAppInterface {

        @android.webkit.JavascriptInterface
        public void openInstagram(String url) {

            runOnUiThread(() -> {
                webView.loadUrl(url);
            });
        }
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {

            webView.goBack();

        } else {

            super.onBackPressed();

        }
    }
}
