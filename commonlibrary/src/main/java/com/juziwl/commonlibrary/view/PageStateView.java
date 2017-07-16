package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.juziwl.commonlibrary.R;

/**
 * @author 王晓清
 * @version V_1.0.0
 * @date 2017/6/29
 * @description 页面状态封装
 *
 */

public class PageStateView extends FrameLayout {
    public static final int NoMessage = 1;    //内容为空
    public static final int NoNETWORK = 2;   //BIUBIU...  正在请求地球网络
    public static final int NOSUBSCRIBE = 3; //还没有订阅内容  空
    public static final int NoTONGXUN = 4;   //你还没有添加通讯录 空
    public static final int ERRORPAGE = 5;  //轻触屏幕，重新加载  一定要轻轻的...
    private RelativeLayout rl_page;
    private ImageView iv_pic;
    private TextView tv_detail, tv_detail2;
    public PageStateView(Context context) {
        super(context);
        initView(context);
    }


    public PageStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PageStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        //加载布局
        View view = View.inflate(context, R.layout.common_page_state_view, this);
        rl_page = (RelativeLayout) view.findViewById(R.id.rl_page);
        iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
        tv_detail = (TextView) view.findViewById(R.id.tv_detail);
        tv_detail2 = (TextView) view.findViewById(R.id.tv_detail2);
    }


    //show展示

    public void showStatePage(int state) {
        rl_page.setVisibility(VISIBLE);
        if (state == 1) {
            iv_pic.setImageResource(R.mipmap.common_state_1);
            tv_detail.setText("内容为空");
            tv_detail2.setText("");

        } else if (state == 2) {
            iv_pic.setImageResource(R.mipmap.common_state_2);
            tv_detail.setText("BIUBIU... ");
            tv_detail2.setText("正在请求地球网络");
        } else if (state == 3) {
            iv_pic.setImageResource(R.mipmap.common_state_3);
            tv_detail.setText("还没有订阅内容");
            tv_detail2.setText("");
        } else if (state == 4) {
            iv_pic.setImageResource(R.mipmap.common_state_4);
            tv_detail.setText("你还没有添加通讯录");
            tv_detail2.setText("");
        } else if (state == 5) {
            iv_pic.setImageResource(R.mipmap.common_state_5);
            tv_detail.setText("轻触屏幕，重新加载");
            tv_detail2.setText("一定要轻轻的...");
        }


    }


    public void hidStatePage() {
        rl_page.setVisibility(GONE);
    }




}
