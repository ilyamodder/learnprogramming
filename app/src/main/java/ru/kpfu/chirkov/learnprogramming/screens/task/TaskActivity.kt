package ru.kpfu.chirkov.learnprogramming.screens.task

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.JavascriptInterface
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_task.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.indeterminateProgressDialog
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
    private var pDialog: Dialog? = null

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.confirm -> {
                webView.loadUrl("javascript:getCode()")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
                    sendDataToWebView(task)
                }
            }
            isWebViewLoaded = true;
        }

        @JavascriptInterface
        fun getCode(): String {
            return task?.initialCode ?: ""
        }

        @JavascriptInterface
        fun receiveCode(code: String) {
            presenter.codeSubmitted(code)
        }
    }

    fun sendDataToWebView(task: TaskResponse?) {
        webView.loadUrl("javascript:setCode(\"" + task?.language?.toLowerCase() + "\")")
    }

    override fun showProgressDialog() {
        pDialog = indeterminateProgressDialog(title = "Подождите...", message = "Проверка решения задачи")
        pDialog?.show()
    }

    override fun hideProgressDialog() {
        pDialog?.dismiss()
        pDialog = null
    }

    override fun showErrorMessage(error: String) {
        alert(Appcompat, error, "Ошибка") {
            positiveButton("OK", {})
        }.show()
    }

    override fun showSuccess() {
        alert(Appcompat, "Вы успешно выполнили задание!", "Поздравляем!") {
            positiveButton("OK", {})
        }.show()
    }
}