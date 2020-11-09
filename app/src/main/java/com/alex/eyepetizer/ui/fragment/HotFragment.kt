package com.alex.eyepetizer.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alex.eyepetizer.R
import com.alex.eyepetizer.adapter.HotAdapter
import kotlinx.android.synthetic.main.hot_fragment.*

class HotFragment : BaseFragment() {
    var mTabs = listOf("周排行", "月排行", "总排行").toMutableList()
    lateinit var mFragments: ArrayList<Fragment>
    val STRATEGY = arrayOf("weekly", "monthly", "historical")
    override fun getLayoutResources(): Int {
        return R.layout.hot_fragment
    }

    override fun initView() {
        val weekFragment: RankFragment = RankFragment()
        val weekBundle = Bundle()
        weekBundle.putString("strategy", STRATEGY[0])
        weekFragment.arguments = weekBundle
        val monthFragment: RankFragment = RankFragment()
        val monthBundle = Bundle()
        monthBundle.putString("strategy", STRATEGY[1])
        monthFragment.arguments = monthBundle
        val allFragment: RankFragment = RankFragment()
        val allBundle = Bundle()
        allBundle.putString("strategy", STRATEGY[2])
        allFragment.arguments = allBundle
        mFragments = ArrayList()
        mFragments.add(weekFragment as Fragment)
        mFragments.add(monthFragment as Fragment)
        mFragments.add(allFragment as Fragment)
        vp_content.adapter = fragmentManager?.let { HotAdapter(it, mFragments, mTabs) }
        tabs.setupWithViewPager(vp_content)
    }

}