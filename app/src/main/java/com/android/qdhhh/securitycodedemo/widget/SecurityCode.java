package com.android.qdhhh.securitycodedemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by qdhhh on 2016/10/26.
 */

public class SecurityCode extends ImageView {

    private Code code;


    public SecurityCode(Context context) {
        this(context, null);
    }

    public SecurityCode(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecurityCode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        code = Code.getInstance();

        setImageBitmap(code.createBitmap());

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageBitmap(code.createBitmap());
            }
        });
    }

    public String getCode() {
        return code.getCode();
    }


}
