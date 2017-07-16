package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.juziwl.commonlibrary.R;


/**
 * @author 郑建
 * 
 * @date 2014年12月22日
 * 
 * @version V_3.0.0
 * 
 * @description 自定义View
 * 
 */
public class CollapsibleTextView extends LinearLayout implements
		OnClickListener {

	/** default text show max lines */
	private static final int DEFAULT_MAX_LINE_COUNT = 4;

	private static final int COLLAPSIBLE_STATE_NONE = 0;
	private static final int COLLAPSIBLE_STATE_SHRINKUP = 1;
	private static final int COLLAPSIBLE_STATE_SPREAD = 2;

	private TextView desc;
	private TextView descOp;

	private String shrinkup;
	private String spread;
	private int mState;
	private boolean flag;

	public CollapsibleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		shrinkup = context.getString(R.string.common_desc_shrinkup);//收起
		spread = context.getString(R.string.common_desc_spread);//显示更多
		View view = inflate(context, R.layout.common_collapsible_textview, this);
		view.setPadding(0, -1, 0, 0);
		desc = (TextView) view.findViewById(R.id.desc_tv);
		descOp = (TextView) view.findViewById(R.id.desc_op_tv);
		descOp.setOnClickListener(this);
	}

	public CollapsibleTextView(Context context) {
		this(context, null);
	}

	public final void setDesc(CharSequence charSequence, BufferType bufferType) {
		desc.setText(charSequence, bufferType);
		mState = COLLAPSIBLE_STATE_SPREAD;
		flag = false;
		descOp.setVisibility(View.GONE);
		requestLayout();
		
	}

	@Override
	public void onClick(View v) {
		flag = false;
		requestLayout();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (!flag) {
			flag = true;
			if (desc.getLineCount() <= DEFAULT_MAX_LINE_COUNT) {
				mState = COLLAPSIBLE_STATE_NONE;
				descOp.setVisibility(View.GONE);
				desc.setMaxLines(DEFAULT_MAX_LINE_COUNT + 1);
			} else {
				post(new InnerRunnable());
			}
		}else{
		}
	}

	class InnerRunnable implements Runnable {
		@Override
		public void run() {
			if (mState == COLLAPSIBLE_STATE_SPREAD) {
				desc.setMaxLines(DEFAULT_MAX_LINE_COUNT);
				descOp.setVisibility(View.VISIBLE);
				descOp.setText(spread);
				mState = COLLAPSIBLE_STATE_SHRINKUP;
			} else if (mState == COLLAPSIBLE_STATE_SHRINKUP) {
				desc.setMaxLines(Integer.MAX_VALUE);
				descOp.setVisibility(View.VISIBLE);
				descOp.setText(shrinkup);
				mState = COLLAPSIBLE_STATE_SPREAD;
			}
		}
	}
}
