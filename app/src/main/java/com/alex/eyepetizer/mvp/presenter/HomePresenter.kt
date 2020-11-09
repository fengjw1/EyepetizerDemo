package com.alex.eyepetizer.mvp.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.alex.eyepetizer.mvp.contract.HomeContract
import com.alex.eyepetizer.mvp.model.HomeModel
import com.alex.eyepetizer.utils.applySchedulers

class HomePresenter(context: Context, view : HomeContract.View) : HomeContract.Presenter {

    val mContext = context
    val mView = view
    val model by lazy {
        HomeModel()
    }

    @SuppressLint("CheckResult")
    override fun requestData() {
        val observable = model.loadData(mContext, true, "0")
        observable?.applySchedulers()?.subscribe {
            it?.let {
                mView.setData(it)
            }
        }
    }

    override fun start() {
        requestData()
    }

    @SuppressLint("CheckResult")
    fun moreData(data: String){
        val observable = model.loadData(mContext, false, data)
        observable?.applySchedulers()?.subscribe {
            it?.let {
                mView.setData(it)
            }
        }
    }
}