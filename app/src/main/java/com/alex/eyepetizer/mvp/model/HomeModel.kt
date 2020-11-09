package com.alex.eyepetizer.mvp.model

import android.content.Context
import com.alex.eyepetizer.mvp.model.bean.HomeBean
import com.alex.eyepetizer.network.ApiService
import com.alex.eyepetizer.network.RetrofitClient
import io.reactivex.Observable


class HomeModel{
    fun loadData(context: Context,isFirst: Boolean,data: String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService  = retrofitClient.create(ApiService::class.java)
        return when(isFirst) {
            true -> apiService?.getHomeData()

            false -> apiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}