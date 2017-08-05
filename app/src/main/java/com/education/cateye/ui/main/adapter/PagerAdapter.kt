package com.education.cateye.ui.main.adapter

import android.support.v7.widget.RecyclerView
import com.alibaba.android.vlayout.RecyclablePagerAdapter
import com.juziwl.commonlibrary.utils.LoadingImgUtil
import kotlinx.android.synthetic.main.layout_pager_item.view.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/5
 * @description 猫眼页面的轮播适配器
 */
class PagerAdapter(subAdapter: SubAdapter, pool: RecyclerView.RecycledViewPool, val list: ArrayList<String>) :
        RecyclablePagerAdapter<RecyclerView.ViewHolder>(subAdapter, pool) {
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        LoadingImgUtil.loadimg(list[position], viewHolder?.itemView!!.image, null, false)
    }

    override fun getCount(): Int  = list.size

    override fun getItemViewType(position: Int): Int  = viewPagerItemType
}
