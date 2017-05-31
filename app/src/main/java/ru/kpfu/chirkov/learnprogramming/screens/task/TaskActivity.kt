package ru.kpfu.chirkov.learnprogramming.screens.task

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.webkit.JavascriptInterface
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_task.*
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.base.BaseActivity
import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse


/**
 * @author ilya
 */
class TaskActivity : BaseActivity(), TaskView {

    private lateinit var presenter: TaskPresenter
    private var isWebViewLoaded = false;
    private var task: TaskResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(JSInterface(), "Android")
        webView.loadUrl("file:///android_asset/editor.html")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        presenter = TaskPresenter(intent.getLongExtra("id", -1), this, LoaderLifecycleHandler.create(this, supportLoaderManager))
        presenter.init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_confirm, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showTask(task: TaskResponse) {
        description.text = task.description
        if (isWebViewLoaded) {
            sendDataToWebView(task)
        }
        this.task = task
    }

    inner class JSInterface {
        @JavascriptInterface
        fun onLoaded() {
            if (task != null) {
                webView.post {
                    sendDataToWebView(task);
                }
            }
            isWebViewLoaded = true;
        }

        @JavascriptInterface
        fun getCode(): String {
            return task?.initialCode ?: "";
        }

        fun receiveCode(code: String) {

        }
    }

    fun sendDataToWebView(task: TaskResponse?) {
        webView.loadUrl("javascript:setCode(\"" + task?.language?.toLowerCase() + "\")")
    }
}