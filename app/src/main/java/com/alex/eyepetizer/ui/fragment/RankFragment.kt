package com.alex.eyepetizer.ui.fragment

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.eyepetizer.R
import com.alex.eyepetizer.adapter.RankAdapter
import com.alex.eyepetizer.mvp.contract.HotContract
import com.alex.eyepetizer.mvp.model.bean.HotBean
import com.alex.eyepetizer.mvp.presenter.HotPresenter
import kotlinx.android.synthetic.main.rank_fragment.*

class RankFragment : BaseFragment(), HotContract.View {
    lateinit var mPresenter: HotPresenter
    lateinit var mStrategy: String
    lateinit var mAdapter: RankAdapter
    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()
    override fun getLayoutResources(): Int {
        return R.layout.rank_fragment
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context, mList)
        recyclerView.adapter = mAdapter
        arguments?.let {
            mStrategy = it.getString("strategy").toString()
            mPresenter = HotPresenter(context, this)
            mPresenter.requestData(mStrategy)
        }
    }

    override fun setData(bean: HotBean) {
        Log.e("rank", bean.toString())
        if(mList.size>0){
            mList.clear()
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

}