package prosolupov.konstantin.ru.taskyandexschool.ui

import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import prosolupov.konstantin.ru.taskyandexschool.R
import android.webkit.WebViewClient
import butterknife.BindView
import butterknife.ButterKnife
import java.net.URISyntaxException


class WebActivity : AppCompatActivity() {


    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_activity)
        ButterKnife.bind(this)
        mWebView = findViewById(R.id.webView) as WebView
        // включаем поддержку JavaScript
        mWebView!!.settings.javaScriptEnabled = true
        // указываем страницу загрузки
        mWebView!!.loadUrl("https://oauth.yandex.ru/authorize?response_type=token&client_id=b46228f475e5434f8fe14d8f42fb98ac")

    }

}