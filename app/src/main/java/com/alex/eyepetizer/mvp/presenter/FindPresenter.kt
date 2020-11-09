package com.alex.eyepetizer.mvp.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.alex.eyepetizer.mvp.contract.FindContract
import com.alex.eyepetizer.mvp.model.FindModel
import com.alex.eyepetizer.mvp.model.bean.FindBean
import com.alex.eyepetizer.ui.fragment.FindFragment
import com.alex.eyepetizer.utils.applySchedulers
import io.reactivex.Observable


class FindPresenter(context: Context, view: FindFragment) : FindContract.Presenter{
    var mContext : Context? = null
    var mView : FindContract.View? = null
    val mModel : FindModel by lazy {
        FindModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {
        requestData()
    }

    @SuppressLint("CheckResult")
    override fun requestData() {
        val observable : Observable<MutableList<FindBean>>? = mContext?.let { mModel.loadData(mContext!!) }
        observable?.applySchedulers()?.subscribe { beans : MutableList<FindBean> ->
            mView?.setData(beans)
        }
    }



}