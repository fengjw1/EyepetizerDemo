package com.alex.eyepetizer.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alex.eyepetizer.R
import com.alex.eyepetizer.ui.ResultActivity
import com.google.android.flexbox.FlexboxLayoutManager

class SearchAdapter(context: Context, list: ArrayList<String>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var context: Context? = null;
    var list: ArrayList<String>? = null
    var inflater: LayoutInflater? = null
    var mDialogListener : onDialogDismiss? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(inflater?.inflate(R.layout.item_search, parent, false), context!!)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder?.tv_title?.text = list!![position]
        val params = holder?.tv_title?.layoutParams
        if (params is FlexboxLayoutManager.LayoutParams) {
            (holder?.tv_title?.layoutParams as FlexboxLayoutManager.LayoutParams).flexGrow = 1.0f
        }
        holder?.itemView?.setOnClickListener {
            var keyWord = list?.get(position)
            var intent : Intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("keyWord",keyWord)
            context?.startActivity(intent)
            mDialogListener?.onDismiss()
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    class SearchViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView!!) {
        var tv_title: TextView = itemView?.findViewById(R.id.tv_title) as TextView

    }
    interface onDialogDismiss{
        fun onDismiss()
    }
    fun setOnDialogDismissListener(onDialogDismiss:onDialogDismiss){
        mDialogListener = onDialogDismiss
    }
}