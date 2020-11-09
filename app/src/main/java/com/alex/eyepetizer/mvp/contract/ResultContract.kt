package com.alex.eyepetizer.mvp.contract

import com.alex.eyepetizer.base.BasePresenter
import com.alex.eyepetizer.base.BaseView
import com.alex.eyepetizer.mvp.model.bean.HotBean


interface ResultContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(query: String, start: Int)
    }
}