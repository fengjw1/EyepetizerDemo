package com.alex.eyepetizer.mvp.contract

import com.alex.eyepetizer.base.BasePresenter
import com.alex.eyepetizer.base.BaseView
import com.alex.eyepetizer.mvp.model.bean.HomeBean

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setData(bean : HomeBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }

}