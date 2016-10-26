package com.android.qdhhh.securitycodedemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.android.qdhhh.securitycodedemo.R;


/**
 * Created by qdhhh on 2016/10/26.
 */

public class SecurityCode extends View {

    private int textSize;
    private Rect mBound;


    public SecurityCode(Context context) {
        this(context, null);
    }


    public SecurityCode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SearchView, defStyleAttr, 0);
        int n = a.getIndexCount();
        int attr = a.getIndex(0);
        textSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        mBound = new Rect();
        mPaint.getTextBounds("00000000", 0, "00000000".length(), mBound);
        setOnClickListener();
    }

    public SecurityCode(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private void setOnClickListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCode = getNewCode();
                postInvalidate();
            }
        });
    }


    /**
     * 字符库
     */
    private static final char[] CHARS = {
            '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 当前的验证码
     */
    private String currentCode = getNewCode();

    /**
     * 验证码的个数
     */
    private static final int CODE_LENTH = 4;

    //默认线条的条数
    private static final int LINE_NUMBER = 5;


    private int padding_left, padding_top;

    private int xRange, yRange;

    private Paint mPaint;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(textSize);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }


        setMeasuredDimension(width, height);

    }


    public String getCurrentCode() {
        return currentCode;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint = new Paint();

        xRange = getMeasuredWidth() / (CODE_LENTH * 2);
        yRange = getMeasuredHeight() / 4;

        Log.i("haha", xRange + "+++" + yRange);


        mPaint.setColor(randomColor());
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        padding_left = xRange + randomInt(xRange);
        padding_top = getMeasuredHeight() / 2 + randomInt(2 * yRange) - yRange;

        mPaint.setTextSize(getWidth() / 4);

        for (int i = 0; i < CODE_LENTH; i++) {
            mPaint.setColor(randomColor());
            randomTextStyle(mPaint);
            canvas.drawText(currentCode.charAt(i) + "", padding_left, padding_top, mPaint);
            Log.i("haha", padding_left + "---" + padding_top);
            randomPadding();
        }
        for (int i = 0; i < LINE_NUMBER; i++) {
            int color = randomColor();
            int startX = randomInt(getWidth());
            int startY = randomInt(getHeight());
            int stopX = randomInt(getWidth());
            int stopY = randomInt(getHeight());
            mPaint.setStrokeWidth(randomInt(5));
            mPaint.setColor(color);
            canvas.drawLine(startX, startY, stopX, stopY, mPaint);
        }
        super.onDraw(canvas);
    }

    /**
     * 获取新的验证码
     *
     * @return
     */
    private String getNewCode() {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < CODE_LENTH; i++) {
            sb.append(CHARS[(int) (CHARS.length * Math.random())]);
        }
        return sb.toString();
    }

    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(randomBoolean());  //true为粗体，false为非粗体
        float skewX = (float) (Math.random() - 0.5);
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
    }

    //随机生成padding值
    private void randomPadding() {
        padding_left = padding_left + xRange + randomInt(xRange);
        padding_top = getMeasuredHeight() / 2 + randomInt(2 * yRange) - yRange;
    }


    private int randomColor() {
        int red = randomInt(256);
        int green = randomInt(256);
        int blue = randomInt(256);
        return Color.rgb(red, green, blue);
    }

    private boolean randomBoolean() {
        return Math.random() > 0.5 ? true : false;
    }

    private int randomInt(int totle) {
        return (int) (Math.random() * totle);
    }

}
