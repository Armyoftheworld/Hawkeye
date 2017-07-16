//package com.juziwl.commonlibrary.view;
//
//
//import android.content.Context;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.juziwl.commonlibrary.R;
//import com.juziwl.commonlibrary.model.ClassComment;
//import com.juziwl.commonlibrary.utils.SmileyParser;
//import com.juziwl.commonlibrary.view.expandabletextview.MTextView;
//
//
//
//public class ClassInfoCommentView{
//
//    private MTextView content;
//    private TextView tv_name;
//    private ImageView iv_pingbi;
//    private SmileyParser smileyParser;
//    private View view;
//    private Context mcontext;
//    public ClassInfoCommentView(Context context) {
//        smileyParser = new SmileyParser(context);
//        mcontext=context;
//        initView(context);
//    }
//
//    /**
//     * 初始化
//     *
//     * @param context
//     */
//    private void initView(Context context) {
//        view = View.inflate(context, R.layout.common_classinfocomment, null);
//        iv_pingbi=(ImageView) view.findViewById(R.id.iv_pingbi);
//        content = (MTextView) view.findViewById(R.id.content);
//        tv_name= (TextView) view.findViewById(R.id.tv_name);
//
//    }
//
//    public void setDataAndShow(ClassComment comment) {
////        content.setMText(smileyParser.replace(comment.s_content , content),
////                0, 0, 0, content.getResources().getColor(R.color.ljqdno));
//        content.setLineSpacingDP(2);
//        content.setMText(smileyParser.replace(comment.s_critic_name+ ":" + comment.s_content.trim(), content),
//                0, 0, comment.s_critic_name.length() + 1, mcontext.getResources().getColor(R.color.common_light_black1));
//        tv_name.setText("");
//
//    }
//
//    public void setPingbiVisiable(boolean isvisiable){
//        if(isvisiable){
//            iv_pingbi.setVisibility(View.VISIBLE);
//        }else{
//            iv_pingbi.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    public View getView(){
//        return view;
//    }
//
//}
