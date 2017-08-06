package com.education.cateye.ui.main.delegate

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.DelegateAdapter.Adapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.education.cateye.R
import com.education.cateye.ui.main.adapter.*
import com.education.cateye.ui.main.fragment.CatEyeFragment
import com.juziwl.commonlibrary.config.BaseAppDelegate
import com.juziwl.commonlibrary.utils.DisplayUtils
import kotlinx.android.synthetic.main.fragment_cateye.view.*
import kotlinx.android.synthetic.main.layout_viewpager.view.*


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description
 */
class CatEyeFragmentDelegate : BaseAppDelegate() {

    private lateinit var delegateAdapter: DelegateAdapter
    private lateinit var viewPool: RecyclerView.RecycledViewPool

    override fun initWidget() {
        val layoutManager = VirtualLayoutManager(getActivity())
        viewPool = RecyclerView.RecycledViewPool()
        viewPool.setMaxRecycledViews(viewPagerType, 1)
        viewPool.setMaxRecycledViews(viewPagerItemType, 10)
        viewPool.setMaxRecycledViews(schoolNameItemType, 5)
        viewPool.setMaxRecycledViews(cameraItemType, 10)
        rootView.recyclerview.layoutManager = layoutManager
        rootView.recyclerview.recycledViewPool = viewPool
        delegateAdapter = DelegateAdapter(layoutManager, true)
        rootView.recyclerview.adapter = delegateAdapter
    }

    fun setBanner(data: ArrayList<String>) {
        delegateAdapter.addAdapter(0, object : SubAdapter(LinearLayoutHelper(), 1) {
            private var pagerAdapter: PagerAdapter? = null
            private var changeListener: ChangeListener? = null
            private var indicators: Array<View>? = null
            private var prePosition = 0
            private var viewPager: ViewPager? = null
            private val delay = 3_000L
            private var curPosition = 0

            private val mHandler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message?) {
                    if (msg?.what == 0 && viewPager != null) {
                        curPosition = viewPager!!.currentItem
                        curPosition = (curPosition + 1) % viewPager!!.adapter.count
                        prePosition = curPosition
                        viewPager?.currentItem = curPosition
                        sendEmptyMessageDelayed(0, delay)
                    }
                }
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                if (holder?.itemView?.id == R.id.viewpager_container) {
                    if (pagerAdapter == null) {
                        pagerAdapter = PagerAdapter(this, viewPool, data)
                    }
                    viewPager = holder.itemView!!.viewpager
                    holder.itemView!!.viewpager.adapter = pagerAdapter
                    if (indicators == null) {
                        indicators = Array(data.size, { View(getActivity()) })
                        initIndicator(holder.itemView.indicator_container, indicators!!)
                        changeListener = ChangeListener(indicators!!)
                        holder.itemView.viewpager.addOnPageChangeListener(changeListener)
                    }
                    holder.itemView.viewpager.setCurrentItem(prePosition, true)
                    mHandler.removeMessages(0)
                    mHandler.sendEmptyMessageDelayed(0, delay)
                }
            }

            override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {
                if (holder?.itemView?.id == R.id.viewpager_container) {
                    mHandler.removeMessages(0)
                    holder.itemView!!.viewpager.adapter = null
                }
            }

            inner class ChangeListener(val indicators: Array<View>) : ViewPager.OnPageChangeListener {

                var prePosition = 0

                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    if (prePosition != -1) {
                        indicators[prePosition].isSelected = false
                    }
                    indicators[position].isSelected = true
                    prePosition = position
                }

            }


            fun initIndicator(container: ViewGroup, indicators: Array<View>) {
                val margin = DisplayUtils.dip2px(2f)
                data.forEachIndexed { index, s ->
                    val view = View(getActivity())
                    view.setBackgroundResource(R.drawable.selector_indicator)
                    val layoutParams = LinearLayout.LayoutParams(DisplayUtils.dip2px(4f), DisplayUtils.dip2px(4f))
                    layoutParams.setMargins(margin, margin, margin, margin)
                    view.layoutParams = layoutParams
                    view.isSelected = index == 0
                    container.addView(view)
                    indicators[index] = view
                }
            }

            override fun getItemViewType(position: Int): Int = viewPagerType
        })
    }

    fun setSchoolCamera() {
        val adapters = ArrayList<Adapter<RecyclerView.ViewHolder>>()
        val subAdapters = ArrayList<SubAdapter>()

        subAdapters.add(object : SubAdapter(LinearLayoutHelper(), 1) {
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                super.onBindViewHolder(holder, position)
            }

            override fun getItemViewType(position: Int): Int = schoolNameItemType
        })

        subAdapters.add(object : SubAdapter(GridLayoutHelper(2), 8) {
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                click(holder?.itemView, {
                    interactiveListener.onInteractive(CatEyeFragment.WATCHCAMERA, null)
                })
            }

            override fun getItemViewType(position: Int): Int = cameraItemType
        })

        adapters.addAll(subAdapters)
        delegateAdapter.addAdapters(adapters)
    }

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_cateye
    }
}