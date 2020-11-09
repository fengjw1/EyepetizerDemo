package com.alex.eyepetizer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    var isFirst = true
    var rootView: View? = null
    var isFragmentVisiable = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null){
            rootView = inflater.inflate(getLayoutResources(), container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            isFragmentVisiable = true
        }
        if (rootView == null) return

        if (!isFirst && isFragmentVisiable){
            onFragmentVisiableChange(true)
            return
        }

        if (isFragmentVisiable){
            onFragmentVisiableChange(false)
            isFragmentVisiable = false
        }

    }

    protected open fun onFragmentVisiableChange(b: Boolean){

    }

    abstract fun getLayoutResources(): Int

    abstract fun initView()

}