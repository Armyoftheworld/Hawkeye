//package com.juziwl.commonlibrary.view;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.os.Handler;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.juziwl.commonlibrary.R;
//import com.juziwl.commonlibrary.model.AttendanceInfo;
//import com.juziwl.commonlibrary.utils.CommonTools;
//
//import java.util.ArrayList;
//
///**
// * @author Army
// * @version V_5.0.0
// * @date 2016年03月03日
// * @description 考勤信息view
// */
//public class AttendanceView extends View {
//    private ArrayList<AttendanceInfo> attendanceInfos= new ArrayList<>();
//    private int space= CommonTools.dip2px(getContext(),20);//两个柱形之间的距离
//    private int paddingLR=CommonTools.dip2px(getContext(),10);//柱形两边的距离
//    private int paddingTop=CommonTools.dip2px(getContext(),10);
//    private int paddingBottom=CommonTools.dip2px(getContext(),60);
//    // --Commented out by Inspection (2017/2/10 0010 上午 9:38):private int paddingBottom2=CommonTools.dip2px(getContext(),10);
//    private int marginTop=CommonTools.dip2px(getContext(),12);//文字距离图表的距离
//    private int textSize=CommonTools.sp2px(getContext(),10);
//    private int textSize2=CommonTools.sp2px(getContext(),12);
//    private Paint paint;
//    private int totalCount=0;
//
//    public AttendanceView(Context context) {
//        this(context, null);
//    }
//
//    public AttendanceView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        paint=new Paint();
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setAntiAlias(true);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        super.onDraw(canvas);
//        if(attendanceInfos!=null&&!attendanceInfos.isEmpty()){
//            int height=getHeight();
//            int width=getWidth();
//
//            //画下面的线
//            paint.setColor(getResources().getColor(R.color.common_line_color3));
//            canvas.drawRect(0,height-paddingBottom,width,height-paddingBottom+1,paint);
//
//            int size=attendanceInfos.size();
//            int columnWidth=(width-space*(size-1))/size;
//            int w=columnWidth-paddingLR*2;
//            for(int i=0;i<attendanceInfos.size();i++){
//                AttendanceInfo atten=attendanceInfos.get(i);
//                float per=atten.personCount*1.0f/(totalCount*1.0f);
//                float partyh=  ((height-paddingBottom-paddingTop)*(1-per));
//                paint.setColor(atten.color);
//                int left=paddingLR+(columnWidth+space)*i;
//                canvas.drawRect(left,paddingTop+partyh,left+w,height-paddingBottom,paint);
//
//                paint.setTextSize(textSize);
//                paint.setTextAlign(Paint.Align.CENTER);
//                canvas.drawText(atten.type+atten.personCount+"人",left+w/2.0f,height-paddingBottom+marginTop,paint);
//            }
//            paint.setColor(Color.BLACK);
//            paint.setTextSize(textSize2);
//            canvas.drawText("班级总人数：" + totalCount, width / 2, height - paddingTop, paint);
//
//        }else{
//
//        }
//    }
//
//    public void setAttendanceInfos(ArrayList<AttendanceInfo> attendanceInfos, int total) {
//        this.attendanceInfos = attendanceInfos;
//        totalCount=total;
//         new Handler().postDelayed(new Runnable() {
//             public void run() {
//                 //execute the task
//                 invalidate();
//             }
//         },200);
//
//    }
//}
