package com.alex.eyepetizer.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alex.eyepetizer.R
import com.alex.eyepetizer.mvp.model.bean.HomeBean
import com.alex.eyepetizer.mvp.model.bean.VideoBean
import com.alex.eyepetizer.ui.VideoDetailActivity
import com.alex.eyepetizer.utils.ImageLoadUtils
import com.alex.eyepetizer.utils.ObjectSaveUtils
import com.alex.eyepetizer.utils.SPUtils

class HomeAdapter(context: Context?, list: MutableList<HomeBean.IssueListBean.ItemListBean>?) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>()  {
    var context : Context? = null;
    var list : MutableList<HomeBean.IssueListBean.ItemListBean>? = null
    var inflater : LayoutInflater
    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(inflater.inflate(R.layout.item_home,parent,false), context!!)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val bean = list?.get(position)
        val title = bean?.data?.title
        val category = bean?.data?.category
        val minute = bean?.data?.duration?.div(60)?: 1
        Log.d("HomeAdapter", "minute>>$minute")
        val second = bean?.data?.duration?.minus((minute.times(60)) as Long )?: 60
        Log.d("HomeAdapter", "second>>$second")
        val realMinute : String
        val realSecond : String
        realMinute = if(minute <10){
            "0"+minute
        }else{
            minute.toString()
        }
        realSecond = if(second <10){
            "0"+second
        }else{
            second.toString()
        }
        var playUrl = bean?.data?.playUrl
        val photo = bean?.data?.cover?.feed
        val author = bean?.data?.author
        context?.let { ImageLoadUtils.display(it,holder?.iv_photo, photo as String) }
        holder?.tv_title?.text = title
        holder?.tv_detail?.text = "发布于 $category / $realMinute:$realSecond"
        if(author!=null){
            ImageLoadUtils.display(context!!,holder?.iv_user,author.icon as String)
        }else{
            holder?.iv_user?.visibility = View.GONE
        }
        holder?.itemView?.setOnClickListener {
            //跳转视频详情页
            val intent : Intent = Intent(context, VideoDetailActivity::class.java)
            val desc = bean?.data?.description
            val duration = bean?.data?.duration
            val playUrl = bean?.data?.playUrl
            val blurred = bean?.data?.cover?.blurred
            val collect = bean?.data?.consumption?.collectionCount
            val share = bean?.data?.consumption?.shareCount
            val reply = bean?.data?.consumption?.replyCount
            val time = System.currentTimeMillis()
            val videoBean  = VideoBean(photo,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)
            val url = SPUtils.getInstance(context!!,"beans").getString(playUrl!!)
            if(url.equals("")){
                var count = SPUtils.getInstance(context!!,"beans").getInt("count")
                count = if(count!=-1){
                    count.inc()
                }else{
                    1
                }
                SPUtils.getInstance(context!!,"beans").put("count",count)
                SPUtils.getInstance(context!!,"beans").put(playUrl!!,playUrl)
                ObjectSaveUtils.saveObject(context!!,"bean$count",videoBean)
            }
            intent.putExtra("data",videoBean as Parcelable)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?:0
    }

    class HomeViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var tv_detail : TextView?= null
        var tv_title : TextView? = null
        var tv_time : TextView? = null
        var iv_photo : ImageView? = null
        var iv_user : ImageView? = null
        init {
            tv_detail = itemView.findViewById(R.id.tv_detail) as TextView?
            tv_title = itemView.findViewById(R.id.tv_title) as TextView?
            iv_photo = itemView.findViewById(R.id.iv_photo) as ImageView?
            iv_user =  itemView.findViewById(R.id.iv_user) as ImageView?
            tv_title?.typeface = Typeface.createFromAsset(context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")

        }
    }
}