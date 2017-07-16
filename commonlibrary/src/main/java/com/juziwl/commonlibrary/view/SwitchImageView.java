package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.juziwl.commonlibrary.R;


public class SwitchImageView extends ImageView implements OnClickListener {

    private boolean state;

    private OnChangeListener listener;

    public SwitchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(this);
        this.setImageResource(R.mipmap.common_open);
    }

    public SwitchImageView(Context context) {
        this(context, null);
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.listener = listener;
    }

    public interface OnChangeListener {
        public void onChange(boolean state);
    }

    @Override
    public void onClick(View v) {
        if (state) {
            this.setImageResource(R.mipmap.common_open);
        } else {
            this.setImageResource(R.mipmap.common_close);
        }
        state = !state;
        if (listener != null) {
            listener.onChange(state);
        }
    }

    public void setSelected(boolean selected) {
        if (selected) {
            this.setImageResource(R.mipmap.common_open);
        } else {
            this.setImageResource(R.mipmap.common_close);
        }
        state = !state;
    }

    public boolean isState() {
        return state;
    }
}
