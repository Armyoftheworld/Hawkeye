package com.juziwl.commonlibrary.view.ninegridview;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.utils.CommonTools;
import com.juziwl.commonlibrary.utils.LoadingImgUtil;
import com.juziwl.commonlibrary.view.RectImageView;

import java.util.ArrayList;


/**
 * @author wxq
 * @version V_5.0.0
 * @date 2017/2/17 0017
 * @description
 */
public class NewNineGridlayout extends LinearLayout {

    private Context mcontext;
    private NineGridlayout classPic;
    private RectImageView image1, image2;

    public NewNineGridlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NewNineGridlayout(Context context) {
        this(context, null, 0);
    }

    public NewNineGridlayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    // 初始化控件
    private void init(Context context) {
        View view = View.inflate(context, R.layout.common_new_nine_grid, this);
        image1 = (RectImageView) view.findViewById(R.id.image1);
        image2 = (RectImageView) view.findViewById(R.id.image2);
        classPic = (NineGridlayout) view.findViewById(R.id.gridView);
        mcontext = context;
    }

    private int imageHeight = 0;
    //设置图片宽度以及监听等等！！
    public void showPic(int nineGridViewWidth, final String imgUrl) {
        //单张图片高度
        imageHeight = (nineGridViewWidth - CommonTools.dip2px(mcontext, 8)) / 2;
        image1.getLayoutParams().height = imageHeight;
        image2.getLayoutParams().height = imageHeight;
        String[] picUrlStr2 = imgUrl.split(";");
        if (picUrlStr2.length == 1) {
            image1.setVisibility(View.VISIBLE);
            image1.getLayoutParams().height=CommonTools.dip2px(mcontext,185);//390
            image2.setVisibility(View.GONE);
            classPic.setVisibility(View.GONE);
            LoadingImgUtil.displayLongImageSize(Global.baseURL + picUrlStr2[0], image1, null);
            image1.setOnClickListener(new PicDetailClick(imgUrl));
        } else if (picUrlStr2.length == 2) {
            image2.setVisibility(View.VISIBLE);
            image1.setVisibility(View.VISIBLE);
            classPic.setVisibility(View.GONE);
            LoadingImgUtil.loadimg(Global.baseURL + picUrlStr2[0], image1, null, false);
            LoadingImgUtil.loadimg(Global.baseURL + picUrlStr2[1], image2, null, false);
            image1.setOnClickListener(new PicDetailClick(imgUrl));
            image2.setOnClickListener(new PicDetailClick(imgUrl));
        } else {
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.GONE);
            classPic.setVisibility(View.VISIBLE);
            ArrayList<Image> itemList = new ArrayList<>();
            for (int i = 0; i < picUrlStr2.length; i++) {
                Image image = new Image(Global.baseURL + picUrlStr2[i], 100, 100);
                itemList.add(image);
            }
            classPic.setTotalWidth(nineGridViewWidth);
            classPic.setImagesData(itemList);
            classPic.setonNineGirdItemClickListener(new NineGridlayout.onNineGirdItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Bundle b = new Bundle();
                    b.putInt("ID", position);
                    b.putString("pics", imgUrl);
                  //  CommonTools.startActivity(mcontext, ClazzPhotoActivity.class, b);
                }
            });
        }

    }

    class PicDetailClick implements View.OnClickListener {
        String pics = "";
        Bundle bundle = new Bundle();

        public PicDetailClick(String images) {
            pics = images;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.image1:
//                    bundle.putInt("ID", 0);
//                    bundle.putString("pics", pics);
//                    break;
//                case R.id.image2:
//                    bundle.putInt("ID", 1);
//                    bundle.putString("pics", pics);
//                    break;
            }
          //  CommonTools.startActivity(mcontext, ClazzPhotoActivity.class, bundle);
        }
    }
}
