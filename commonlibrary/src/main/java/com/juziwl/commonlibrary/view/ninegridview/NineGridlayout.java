package com.juziwl.commonlibrary.view.ninegridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.juziwl.commonlibrary.model.ImageSize;
import com.juziwl.commonlibrary.utils.DisplayUtils;
import com.juziwl.commonlibrary.utils.LoadingImgUtil;
import com.juziwl.commonlibrary.view.RectImageView;

import java.lang.ref.SoftReference;
import java.util.List;


/**
 * Created by Pan_ on 2015/2/2.
 */
public class NineGridlayout extends ViewGroup {

    /**
     * 图片之间的间隔
     */
    private int gap = 8;
    private int columns;//
    private int rows;//
    private List listData;
    private int totalWidth = 0;

    public NineGridlayout(Context context) {
        super(context);
    }

    public NineGridlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        ScreenTools screenTools=ScreenTools.instance(getContext());
//        totalWidth=screenTools.getScreenWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        totalWidth = getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        totalWidth = r - l;

    }

    private void layoutChildrenView() {
        if (totalWidth == 0) {
            return;
        }
        int childrenCount = listData.size();

        int singleWidth = (totalWidth - gap * (3 - 1)) / 3;
        int singleHeight = singleWidth;

        //根据子view数量确定高度
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = singleHeight * rows + gap * (rows - 1);
        setLayoutParams(params);

        for (int i = 0; i < childrenCount; i++) {
            RectImageView childrenView = (RectImageView) getChildAt(i);
            SoftReference<RectImageView> softReference = new SoftReference<>(childrenView);
            softReference.get().setOnClickListener(new MyClickListener(i));
            Image image = (Image) listData.get(i);
//            softReference.get().setImageUrl(image.getUrl(), image.getWidth(), image.getHeight());


            if (image.getWidth() == 0 || image.getHeight() == 0) {
//                if (image.getUrl().equals(Global.baseURL)) {
//                    softReference.get().setImageResource(R.mipmap.common_falseimg);
//                } else {
                    LoadingImgUtil.loadimg(image.getUrl(), softReference.get(), null, false);
//                }
            } else {
//                if (image.getUrl().equals(Global.baseURL)) {
//                    softReference.get().setImageResource(R.mipmap.common_falseimg);
//                } else {
                    LoadingImgUtil.displayImageWithImageSize(image.getUrl(), softReference.get(), new ImageSize(image.getWidth(), image.getHeight()), null, false);
//                }
            }


            int[] position = findPosition(i);
            int left = (singleWidth + gap) * position[1];
            int top = (singleHeight + gap) * position[0];
            int right = left + singleWidth;
            int bottom = top + singleHeight;

            softReference.get().layout(left, top, right, bottom);
        }

    }


    private int[] findPosition(int childNum) {
        int[] position = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i * columns + j) == childNum) {
                    position[0] = i;//行
                    position[1] = j;//列
                    break;
                }
            }
        }
        return position;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public void setTotalWidth(int width) {
        totalWidth = width;
        gap = DisplayUtils.dip2px(8); // 间距8dp
    }

    public void setImagesData(List<Image> lists) {
        if (lists == null || lists.isEmpty()) {
            return;
        }
        //初始化布局
        generateChildrenLayout(lists.size());
        //这里做一个重用view的处理
        if (listData == null) {
            int i = 0;
            while (i < lists.size()) {
                ImageView iv = generateImageView(i);
                addView(iv, generateDefaultLayoutParams());
                i++;
            }
        } else {
            int oldViewCount = listData.size();
            int newViewCount = lists.size();
            if (oldViewCount > newViewCount) {
                removeViews(newViewCount - 1, oldViewCount - newViewCount);
            } else if (oldViewCount < newViewCount) {
                for (int i = 0; i < newViewCount - oldViewCount; i++) {
                    ImageView iv = generateImageView(i);
                    addView(iv, generateDefaultLayoutParams());
                }
            }
        }
        listData = lists;
        layoutChildrenView();
    }


    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     *
     * @param length
     */
    private void generateChildrenLayout(int length) {
        if (length <= 3) {
            rows = 1;
            columns = length;
        } else if (length <= 6) {
            rows = 2;
            columns = 3;
            if (length == 4) {
                columns = 2;
            }
        } else {
            rows = 3;
            columns = 3;
        }
    }

    private ImageView generateImageView(final int position) {
        // 圆角设置 换成圆角的
//        CustomImageView iv = new CustomImageView(getContext());

        RectImageView iv = new RectImageView(getContext());
        //4dp
        iv.setBorderRadius(8);


        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);


//        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return iv;
    }

    private onNineGirdItemClickListener listener;

    public interface onNineGirdItemClickListener {
        public void onItemClick(int position);
    }

    public void setonNineGirdItemClickListener(onNineGirdItemClickListener listener) {
        this.listener = listener;
    }

    class MyClickListener implements OnClickListener {
        public int position = 0;

        public MyClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(position);
            }
        }


    }


}
