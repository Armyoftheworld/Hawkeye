package com.education.cateye.ui.main.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.education.cateye.R
import com.education.cateye.config.MainBaseActivity
import com.education.cateye.ui.main.delegate.MainDelegate
import com.education.cateye.ui.main.fragment.CatEyeFragment
import com.education.cateye.ui.main.fragment.MainFragment
import com.education.cateye.ui.main.fragment.MyFragment

class MainActivity : MainBaseActivity<MainDelegate>() {

    private lateinit var tagClassPairs: Array<Pair<String, String>>

    companion object {
        val TABCHANGE = "MainActivity_TABCHANGE"
    }

    override fun initEventAndData() {
        tagClassPairs = arrayOf(getString(R.string.mainPage) to MainFragment::class.java.name,
                getString(R.string.catEyePage) to CatEyeFragment::class.java.name,
                getString(R.string.myPage) to MyFragment::class.java.name)
        viewDelegate.setTabSelection(0)
    }

    override fun onInteractive(action: String?, data: Bundle?) {
        if (TABCHANGE == action) {
            val tabPosition = data?.getInt("tabPosition", 0)
            val fragment = supportFragmentManager.findFragmentByTag(tagClassPairs[tabPosition!!].first)
            val beginTransaction = supportFragmentManager.beginTransaction()
            if (fragment == null) {
                beginTransaction.add(R.id.container, Fragment.instantiate(this,
                        tagClassPairs[tabPosition].second), tagClassPairs[tabPosition].first)
            } else {
                beginTransaction.show(fragment)
            }
            val preTabIndex = viewDelegate.getPreTabIndex()
            if (preTabIndex != -1) {
                val preFragment = supportFragmentManager.findFragmentByTag(tagClassPairs[preTabIndex].first)
                if (preFragment != null) {
                    beginTransaction.hide(preFragment)
                }
            }
            beginTransaction.commitAllowingStateLoss()
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun getDelegateClass(): Class<MainDelegate> {
        return MainDelegate::class.java
    }

    override fun isShowToolBar(): Boolean {
        return false
    }

    override fun initCustomTopbar() {
    }

    override fun injectActivity() {
        activityComponent.inject(this)
    }

}
