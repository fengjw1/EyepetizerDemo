package com.alex.eyepetizer.ui.fragment

import com.alex.eyepetizer.R
import com.alex.eyepetizer.adapter.FindAdapter
import com.alex.eyepetizer.mvp.contract.FindContract
import com.alex.eyepetizer.mvp.model.bean.FindBean
import com.alex.eyepetizer.mvp.presenter.FindPresenter
import kotlinx.android.synthetic.main.find_fragment.*

class FindFragment : BaseFragment(), FindContract.View {

    var mPresenter: FindPresenter? = null
    var mAdapter : FindAdapter? = null
    var mList : MutableList<FindBean>? = null
    override fun setData(beans: MutableList<FindBean>) {
        mAdapter?.mList = beans
        mList = beans
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.find_fragment
    }

    override fun initView() {
        mPresenter = context?.let { FindPresenter(it, this) }
        mPresenter?.start()
        mAdapter = context?.let { FindAdapter(it, mList) }
        gv_find.adapter = mAdapter
        gv_find.setOnItemClickListener { parent, view, position, id ->
//            var bean = mList?.get(position)
//            var name = bean?.name
//            var intent : Intent = Intent(context,FindDetailActivity::class.java)
//            intent.putExtra("name",name)
//            startActivity(intent)

        }
    }
}