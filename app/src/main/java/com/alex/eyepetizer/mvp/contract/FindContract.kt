package com.alex.eyepetizer.mvp.contract

import com.alex.eyepetizer.base.BasePresenter
import com.alex.eyepetizer.base.BaseView
import com.alex.eyepetizer.mvp.model.bean.FindBean

interface FindContract{
    interface View : BaseView<Presenter> {
        fun setData(beans : MutableList<FindBean>)
    }
    interface Presenter : BasePresenter {
        fun requestData()
    }
}