package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juziwl.commonlibrary.R;


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description 底部tab栏
 */
public class BottomTabContainer extends LinearLayout {

    private ViewGroup[] tabs;
    private int curIndex = -1, preIndex = -1;
    private onTabChangeListener onTabChangeListener;

    public BottomTabContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    public void setTabs(TabItem[] tabItems) {
        if (tabItems == null || tabItems.length == 0) {
            throw new NullPointerException("tabItems must not be null");
        }
        removeAllViews();
        tabs = new ViewGroup[tabItems.length];
        for (int i = 0; i < tabItems.length; i++) {
            int position = i;
            ViewGroup view = createTab(tabItems[i]);
            view.setOnClickListener(v -> setSelection(position));
            tabs[i] = view;
            addView(view, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        }
    }

    private ViewGroup createTab(TabItem tabItem) {
        ViewGroup tab = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_tab_item, null);
        ImageView icon = (ImageView) tab.getChildAt(0);
        icon.setImageResource(tabItem.iconId);

        TextView text = (TextView) tab.getChildAt(1);
        text.setText(tabItem.textId);
        text.setTextColor(getResources().getColorStateList(tabItem.textColorId));
        return tab;
    }

    public void setOnTabChangeListener(BottomTabContainer.onTabChangeListener onTabChangeListener) {
        this.onTabChangeListener = onTabChangeListener;
    }

    public void setSelection(int position) {
        if(position == curIndex){
            return;
        }
        if (curIndex != -1) {
            setTabSelected(tabs[curIndex], false);
        }
        setTabSelected(tabs[position], true);
        preIndex = curIndex;
        curIndex = position;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChange(position);
        }
    }

    private void setTabSelected(ViewGroup tab, boolean isSelected) {
        tab.getChildAt(0).setSelected(isSelected);
        tab.getChildAt(1).setSelected(isSelected);
    }

    public int getPreIndex() {
        return preIndex;
    }

    public static class TabItem {
        public int textId = 0;
        public int iconId = 0;
        public int textColorId = 0;

        public TabItem(int textId, int iconId, int textColorId) {
            this.textId = textId;
            this.iconId = iconId;
            this.textColorId = textColorId;
        }
    }

    public interface onTabChangeListener {
        void onTabChange(int position);
    }

}
