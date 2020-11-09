package com.alex.eyepetizer.mvp.model

import android.content.Context
import com.alex.eyepetizer.mvp.model.bean.HotBean
import com.alex.eyepetizer.network.ApiService
import com.alex.eyepetizer.network.RetrofitClient
import io.reactivex.Observable

class HotModel {
    fun loadData(context: Context, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService  = retrofitClient.create(ApiService::class.java)
        return apiService?.getHotData(10, strategy!!,"26868b32e808498db32fd51fb422d00175e179df",83)

    }
}