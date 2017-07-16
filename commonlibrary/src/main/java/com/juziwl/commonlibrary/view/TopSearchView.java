package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.utils.CommonTools;


/**
 * @author 王晓清
 * @version V_5.0.0
 * @date 2017年06月21日
 * @description 顶部搜索控件封装
 */

public class TopSearchView extends LinearLayout implements View.OnClickListener {

    ImageView ivDelete;
    //    外部的输入框
    EditText etCcontent;

    int totalNumber;

    int maxPic;

    public TopSearchView(Context context) {
        super(context);
        initView(context);
    }

    public TopSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TopSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    // 初始化相关控件
    private void initView(Context context) {
        //加载布局
        View view = View.inflate(context, R.layout.common_top_search_edit_layout
                , this);
        ivDelete=(ImageView)view.findViewById(R.id.iv_delete);
        etCcontent=(EditText)view.findViewById(R.id.et_content);
        etCcontent.addTextChangedListener(new MyTextWatcher());
        etCcontent.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // 先隐藏键盘
                    CommonTools.hideInput(etCcontent);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    if(searchListener!=null)
                        searchListener.search(etCcontent.getText().toString());
                }
                return false;
            }
        });
        ivDelete.setOnClickListener(this);
    }

    private void search(Editable text) {
        
        
        
    }


//    private class MyOnTouchListener implements OnTouchListener {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (emojiRelative.getVisibility() != View.GONE) {
//                    emojiRelative.setVisibility(View.GONE);
//                    CommonTools.showInput(outEditText);
//                }
//            }
//            return false;
//        }
//    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if(etCcontent.getText().length()==0){
                ivDelete.setVisibility(GONE);
            }else{
                ivDelete.setVisibility(VISIBLE);

            }


            if (searchListener!=null){
                searchListener.textChange(etCcontent.getText().toString());

            }



        }
    }


    private void initEmoji() {


    }



    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_delete:
            etCcontent.setText("");

//                break;
//        }
    }

//
    public SearchClickAndChangeListener getSearchClickAndChangeListener() {
        return searchListener;
    }

    public void setSearchClickAndChangeListener(SearchClickAndChangeListener SearchClickAndChangeListener) {
        this.searchListener = SearchClickAndChangeListener;
    }

    public SearchClickAndChangeListener searchListener;

    public  interface SearchClickAndChangeListener{


        void search(String value);


        void textChange(String value);



    }




}
