package com.example.browser_for_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    // Определение переменных
    ProgressBar progressBar;
    EditText inputUrl;
    WebView webView;
    ImageButton findButton, forwardButton, backButton, restartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Инициализация всех объектов
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputUrl = (EditText) findViewById(R.id.autoCompleteTextView);
        webView = (WebView) findViewById(R.id.webView);
        findButton = (ImageButton) findViewById(R.id.findButton);
        forwardButton = (ImageButton) findViewById(R.id.forwardButton);
        backButton = (ImageButton) findViewById(R.id.backButton);
        restartButton = (ImageButton) findViewById(R.id.rebootButton);
        //Установка веб-клиента для webView
        webView.setWebViewClient(new WebViewClient());
        // Установка прогрессбар, который будет отображать процесс загрузки страницы.
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress==100)
                    progressBar.setVisibility(View.GONE);
                else
                    progressBar.setVisibility(View.VISIBLE);
            }
        });
        // Включание поддержки JavaScript и настройка url по умолчанию
        WebSettings webset = webView.getSettings();
        webset.setJavaScriptEnabled(true);

        webView.loadUrl("http://www.ya.ru");

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = inputUrl.getText().toString();

                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                webView.loadUrl(url);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webView.getWindowToken(), 0);
            }
        });
        // Установка слушателей для кнопок и настройка их действий при нажатии
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward())
                    webView.goForward();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack())
                    webView.goBack();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });

    }
}