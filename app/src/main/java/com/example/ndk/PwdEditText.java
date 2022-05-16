package com.example.ndk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

public class PwdEditText extends androidx.appcompat.widget.AppCompatEditText {

    private Paint sidePaint , backPaint , textPaint;
    private Context mC;
    private int spzceX ,spzceY;
    private int Wide;
    private int yiInput,weiInput,backColor , textColor;
    private String mText;
    private int textLength;
    private List<RectF> rectFS;
    public PwdEditText(Context context) {
        super(context);
        mC = context;
        init();
    }

    public PwdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mC = context;
        init();
    }

    public PwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mC = context;
        init();
    }

    /**
     * 输入监听
     */
    interface OnTextChangeListeven{
        void onTextChange(String pwd);
    }
    private OnTextChangeListeven onTextChangeListeven;
    public void setOnTextChangeListeven(OnTextChangeListeven onTextChangeListeven){
        this.onTextChangeListeven = onTextChangeListeven;
    }
    public void clearText(){
        setText("");
        setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
    }

    private GradientDrawable gradientDrawable = new GradientDrawable();

    private void init() {
        setTextColor(0xff454952);
        setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
        spzceX = dp2px(4);
        spzceY = dp2px(4);
        Wide  = dp2px(52);
        yiInput = 0xFFFF4081;
        weiInput = 0xffF1F3F6;
        backColor = 0xfff1f1f1;
        textColor = 0xFF424242;
        sidePaint = new Paint();
        backPaint = new Paint();
        textPaint = new Paint();
        rectFS = new ArrayList<>();

        mText = "123" ;
        textLength = 6;

        this.setBackgroundDrawable(null);
        setFocusableInTouchMode(true);
        setLongClickable(false);
        setTextIsSelectable(false);
        setCursorVisible(false);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (mText == null) {return;}
        //如果字数不超过用户设置的总字数，就赋值给成员变量mText；
        // 如果字数大于用户设置的总字数，就只保留用户设置的那几位数字，并把光标制动到最后，让用户可以删除；
        if (text.toString().length() <= textLength){
            mText = text.toString();
        }else{
            setText(mText);
            setSelection(getText().toString().length());  //光标制动到最后
            //setText(mText)之后键盘会还原，再次把键盘设置为数字键盘；
            setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_NORMAL);
        }
        if (onTextChangeListeven != null) onTextChangeListeven.onTextChange(mText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        switch (heightMode){
            case MeasureSpec.EXACTLY:
                heightSize = MeasureSpec.getSize(heightMeasureSpec);
                break;
            case MeasureSpec.AT_MOST:
                heightSize = widthSize/textLength;
                break;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        gradientDrawable.setStroke((int) 1, 0xff454952);
        gradientDrawable.setCornerRadius(6f);
        setBackground(gradientDrawable);
        //边框画笔
        sidePaint.setAntiAlias(true);//消除锯齿
        sidePaint.setStrokeWidth(3);//设置画笔的宽度
        sidePaint.setStyle(Paint.Style.STROKE);//设置绘制轮廓
        sidePaint.setColor(backColor);
        //背景色画笔
        backPaint.setStyle(Paint.Style.FILL);
        backPaint.setColor(backColor);
        //文字的画笔
        textPaint.setTextSize(14);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        for (int i = 0; i < textLength; i++) {
            //区分已输入和未输入的边框颜色
            if (mText.length() >= i){
                sidePaint.setColor(backColor);
            }else {
                sidePaint.setColor(backColor);
            }
            //RectF的参数(left,  top,  right,  bottom); 画出每个矩形框并设置间距，间距其实是增加左边框距离，缩小上下右边框距离；
            RectF rect = new RectF(i * Wide + spzceX, spzceY,i * Wide + Wide - spzceX,Wide - spzceY); //四个值，分别代表4条线，距离起点位置的线
            canvas.drawRoundRect(rect,9,9,backPaint); //绘制背景色
            canvas.drawRoundRect(rect,9,9,sidePaint); //绘制边框；
            rectFS.add(rect);
        }
        //画密码圆点
        for (int j = 0; j < mText.length(); j++) {
            canvas.drawCircle(rectFS.get(j).centerX(),rectFS.get(j).centerY(),dp2px(5),textPaint);
        }
    }

    private int dp2px(float dpValue){
        float scale = mC.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

}