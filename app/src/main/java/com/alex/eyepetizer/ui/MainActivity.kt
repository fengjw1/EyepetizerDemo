package com.alex.eyepetizer.ui

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.alex.eyepetizer.R
import com.alex.eyepetizer.search.SEARCH_TAG
import com.alex.eyepetizer.search.SearchFragment
import com.alex.eyepetizer.ui.fragment.FindFragment
import com.alex.eyepetizer.ui.fragment.HomeFragment
import com.alex.eyepetizer.ui.fragment.HotFragment
import com.alex.eyepetizer.ui.fragment.MineFragment
import com.alex.eyepetizer.utils.showToast
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var homeFragment: HomeFragment
    lateinit var findFragment: FindFragment
    lateinit var hotFragemnt: HotFragment
    lateinit var mineFragment: MineFragment
    var mExitTime: Long = 0
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params
        setRadioButton()
        initToolbar()
        initFragment(savedInstanceState)

    }


    override fun onClick(v: View?) {
        clearState()
        when(v?.id){
            R.id.rb_find ->{
                rb_find.isChecked = true
                rb_find.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(findFragment)
                    .hide(homeFragment)
                    .hide(mineFragment)
                    .hide(hotFragemnt)
                    .commit()
                tv_bar_title.text = "Discover"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_home ->{
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(homeFragment)
                    .hide(findFragment)
                    .hide(mineFragment)
                    .hide(hotFragemnt)
                    .commit()
                tv_bar_title.text = getToday()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_hot ->{
                rb_hot.isChecked = true
                rb_hot.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(hotFragemnt)
                    .hide(findFragment)
                    .hide(mineFragment)
                    .hide(homeFragment)
                    .commit()
                tv_bar_title.text = "Ranking"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_mine ->{
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(mineFragment)
                    .hide(findFragment)
                    .hide(homeFragment)
                    .hide(hotFragemnt)
                    .commit()
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setImageResource(R.drawable.icon_setting)
            }

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
                toast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                toast = showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun setRadioButton(){
        rb_home.isChecked = true
        rb_home.setTextColor(Color.BLACK)
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_mine.setOnClickListener(this)
    }


    private fun initFragment(savedInstanceState: Bundle?){
        if (savedInstanceState != null) {
            //异常情况,例如横屏
            val mFragment = supportFragmentManager.fragments
            mFragment.forEach {
                if (it is HomeFragment) homeFragment = it
                if (it is FindFragment) findFragment = it
                if (it is HotFragment) hotFragemnt = it
                if (it is MineFragment) mineFragment = it
            }
        }else{
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            hotFragemnt = HotFragment()
            mineFragment = MineFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, homeFragment)
                .add(R.id.fl_content, findFragment)
                .add(R.id.fl_content, hotFragemnt)
                .add(R.id.fl_content, mineFragment)
                .commit()
        }
        //初始化
        supportFragmentManager.beginTransaction().show(homeFragment)
            .hide(findFragment)
            .hide(mineFragment)
            .hide(hotFragemnt)
            .commit()
    }

    private fun clearState(){
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
    }

    private fun initToolbar(){
        tv_bar_title.text = getToday()
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener{
            if (!rb_mine.isChecked){
                //当前界面不是在“我的”
                SearchFragment().show(fragmentManager, SEARCH_TAG)
            }
        }
    }

    private fun getToday(): String{
        val list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date;
        var index = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) index = 0
        return list[index]
    }

}