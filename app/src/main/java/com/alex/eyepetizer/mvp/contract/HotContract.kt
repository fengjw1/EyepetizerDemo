package com.alex.eyepetizer.mvp.contract

import com.alex.eyepetizer.base.BasePresenter
import com.alex.eyepetizer.base.BaseView
import com.alex.eyepetizer.mvp.model.bean.HotBean

/**
 * Created by lvruheng on 2017/7/5.
 */
interface HotContract{
    interface View : BaseView<Presenter> {
        fun setData(bean : HotBean)
    }
    interface Presenter : BasePresenter {
        fun requestData(strategy: String)
    }
}