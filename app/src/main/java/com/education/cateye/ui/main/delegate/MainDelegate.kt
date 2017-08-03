package com.education.cateye.ui.main.delegate

import android.os.Bundle
import com.education.cateye.R
import com.education.cateye.ui.main.activity.MainActivity
import com.juziwl.commonlibrary.config.BaseAppDelegate
import com.juziwl.commonlibrary.view.BottomTabContainer.TabItem
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/1
 * @description
 */
class MainDelegate : BaseAppDelegate() {

    val bundle: Bundle = Bundle()

    override fun initWidget() {
        val tabItem1 = TabItem(R.string.mainPage, R.drawable.selector_mainpage_icon, R.color.selector_tab_textcolor)
        val tabItem2 = TabItem(R.string.catEyePage, R.drawable.selector_mainpage_icon, R.color.selector_tab_textcolor)
        val tabItem3 = TabItem(R.string.myPage, R.drawable.selector_mainpage_icon, R.color.selector_tab_textcolor)
        rootView.tabcontainer.setTabs(arrayOf(tabItem1, tabItem2, tabItem3))
        rootView.tabcontainer.setOnTabChangeListener({
            position ->
            bundle.putInt("tabPosition", position)
            interactiveListener.onInteractive(MainActivity.TABCHANGE, bundle)
        })
    }

    fun setTabSelection(position: Int) {
        rootView.tabcontainer.setSelection(position)
    }

    fun getPreTabIndex(): Int {
        return rootView.tabcontainer.preIndex
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main
    }
}