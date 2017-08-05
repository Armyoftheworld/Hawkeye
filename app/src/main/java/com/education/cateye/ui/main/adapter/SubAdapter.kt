package com.education.cateye.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.education.cateye.R

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/5
 * @description 猫眼页面RecyclerView的适配器
 */
open class SubAdapter(val layoutHelper: LayoutHelper, val mCount: Int) : DelegateAdapter.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateLayoutHelper(): LayoutHelper = layoutHelper

    override fun getItemCount(): Int = mCount

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        var layoutId = android.R.layout.simple_list_item_1
        when (viewType) {
            viewPagerType -> layoutId = R.layout.layout_viewpager
            viewPagerItemType -> layoutId = R.layout.layout_pager_item
            schoolNameItemType -> layoutId = R.layout.layout_schoolname
            cameraItemType -> layoutId = R.layout.layout_camera_item
        }
        return ItemViewHolder(LayoutInflater.from(parent?.context).inflate(layoutId, parent, false))
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}