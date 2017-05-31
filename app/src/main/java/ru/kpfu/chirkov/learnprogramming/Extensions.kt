package ru.kpfu.chirkov.learnprogramming

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author ilya
 */

fun <T> Observable<T>.addSchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun String.toLangId(): Int {
    return hashMapOf<String, Int>(
            "bash" to 14,
            "c" to 1,
            "clojure" to 13,
            "cobol" to 36,
            "cpp" to 2,
            "csharp" to 9,
            "d" to 22,
            "db2" to 44,
            "erlang" to 16,
            "fortran" to 54,
            "fsharp" to 33,
            "go" to 21,
            "groovy" to 31,
            "haskell" to 12,
            "java" to 3,
            "java8" to 43,
            "javascript" to 20,
            "lolcode" to 38,
            "lua" to 18,
            "mysql" to 10,
            "objectivec" to 32,
            "ocaml" to 23,
            "octave" to 46,
            "oracle" to 11,
            "pascal" to 25,
            "perl" to 6,
            "php" to 7,
            "python" to 5,
            "python3" to 30,
            "r" to 24,
            "racket" to 49,
            "ruby" to 8,
            "rust" to 50,
            "sbcl" to 26,
            "scala" to 15,
            "smalltalk" to 39,
            "swift" to 51,
            "tcl" to 40,
            "tsql" to 42,
            "visualbasic" to 37,
            "whitespace" to 41,
            "xquery" to 48
    )[this.toLowerCase()]!!
}
