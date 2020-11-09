package com.alex.eyepetizer.ui.fragment

import com.alex.eyepetizer.ui.CacheActivity
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import com.alex.eyepetizer.R
import com.alex.eyepetizer.ui.AdviseActivity
import com.alex.eyepetizer.ui.WatchActivity
import kotlinx.android.synthetic.main.mine_fragment.*

class MineFragment : BaseFragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_watch ->{
                val intent = Intent(activity, WatchActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_advise ->{
                val intent = Intent(activity, AdviseActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_save ->{
                val intent = Intent(activity, CacheActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.mine_fragment
    }

    override fun initView() {
        tv_advise.setOnClickListener(this)
        tv_watch.setOnClickListener(this)
        tv_save.setOnClickListener(this)
        tv_advise.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_watch.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_save.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

}
