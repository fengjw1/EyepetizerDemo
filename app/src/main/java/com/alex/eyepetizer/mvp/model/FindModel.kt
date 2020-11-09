package com.alex.eyepetizer.mvp.model

import android.content.Context
import com.alex.eyepetizer.mvp.model.bean.FindBean
import com.alex.eyepetizer.network.ApiService
import com.alex.eyepetizer.network.RetrofitClient
import io.reactivex.Observable


class FindModel() {
    fun loadData(context: Context): Observable<MutableList<FindBean>>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindData()
    }
}