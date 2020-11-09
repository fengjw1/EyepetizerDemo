package com.alex.eyepetizer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 仅在activity中使用的toast
 */
fun Context.showToast(message: String): Toast{
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
    return toast
}

/**
 * 启动activity
 */
inline fun <reified T: Activity> Activity.newInstance(){
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

/**
 * rxjava的线程封装
 */
fun <T> Observable<T>.applySchedulers(): Observable<T>{
    return subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}