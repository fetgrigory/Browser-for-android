package com.example.browser_for_android;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebView;
// Класс для веб-клиента
public class WebClient extends WebViewClient {
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
