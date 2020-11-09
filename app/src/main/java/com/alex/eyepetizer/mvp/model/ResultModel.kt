package com.alex.eyepetizer.mvp.model

import android.content.Context
import com.alex.eyepetizer.mvp.model.bean.HotBean
import com.alex.eyepetizer.network.ApiService
import com.alex.eyepetizer.network.RetrofitClient
import io.reactivex.Observable


class ResultModel {
    fun loadData(context: Context, query: String, start: Int): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getSearchData(10, query, start)

    }
}
