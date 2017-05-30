package ru.kpfu.chirkov.learnprogramming.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.support.v4.toast

/**
 * @author ilya
 */
abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(getLayoutId(), container, false);
        return v;
    }

    @LayoutRes abstract fun getLayoutId(): Int

    override fun showError(error: String) {
        toast(error)
    }
}