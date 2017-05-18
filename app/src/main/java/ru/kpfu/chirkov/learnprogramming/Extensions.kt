package ru.kpfu.chirkov.learnprogramming

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author ilya
 */

fun <T> Observable<T>.addSchedulers(): Observable<T> {
    subscribeOn(Schedulers.io())
    observeOn(AndroidSchedulers.mainThread())
    return this
}
