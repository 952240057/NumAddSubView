package com.gh.numaddsubview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.renderscript.Sampler;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.gh.numaddsubview.R.id.sub_btn;

/**
 * Created by 95224 on 2016/9/26.
 */
public class NumAddSubView extends LinearLayout implements View.OnClickListener {
    private LayoutInflater mIfalater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTV;

    private int value;
    private int minValue;
    private int maxValue;

    private OnButtonClickListener mOnButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.mOnButtonClickListener = onButtonClickListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn) {//点击+
            numAdd();
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onButtononAddClick(v, value);
            }
        } else if (v.getId() == R.id.sub_btn) {//点击-
            numSub();
            if (mOnButtonClickListener != null) {
                mOnButtonClickListener.onButtononAddClick(v, value);
            }
        }
    }

    private void numAdd() {
        if (value < maxValue) {
            value++;
        }
        mNumTV.setText(value + "");
    }

    private void numSub() {
        if (value > minValue) {
            value--;
        }
        mNumTV.setText(value + "");
    }

    public void setAddBtnBackground(Drawable drawable) {
        this.mAddBtn.setBackground(drawable);
    }

    public void setSubBtnBackground(Drawable drawable) {
        this.mSubBtn.setBackground(drawable);
    }

    public void setNumTvBackgorund(int drawableId) {
        this.mNumTV.setBackgroundResource(drawableId);
    }

    public interface OnButtonClickListener {
        void onButtononAddClick(View view, int value);

        void onButtononSubClick(View view, int value);

    }

    public LayoutInflater getmIfalater() {
        return mIfalater;
    }

    public void setmIfalater(LayoutInflater mIfalater) {
        this.mIfalater = mIfalater;
    }

    public int getValue() {
        String val = mNumTV.getText().toString();
        if (val != null && !"".equals(val)) {
            this.value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTV.setText(value + "");
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public NumAddSubView(Context context) {
        this(context, null);
    }

    public NumAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIfalater = LayoutInflater.from(context);

        initView();
        if (attrs != null) {
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(context,
                    attrs, R.styleable.NumAddSubView, defStyleAttr, 0);
            int val = tta.getInt(R.styleable.NumAddSubView_value, 0);
            setValue(val);
            int minValue = tta.getInt(R.styleable.NumAddSubView_minvalue, 0);
            setMinValue(minValue);
            int maxValue = tta.getInt(R.styleable.NumAddSubView_maxvalue, 0);
            setMaxValue(maxValue);

            Drawable addBtnDrawable = tta.getDrawable(
                    R.styleable.NumAddSubView_addBtnBG);
            setAddBtnBackground(addBtnDrawable);
            Drawable subBtnDrawable = tta.getDrawable(
                    R.styleable.NumAddSubView_subBtnBG);
            setSubBtnBackground(subBtnDrawable);

            int numTvDrawable = tta.getResourceId(R.styleable.NumAddSubView_numTvBG,
                    android.R.color.transparent);
            setNumTvBackgorund(numTvDrawable);
            tta.recycle();//回收
        }

    }

    private void initView() {
        View view = mIfalater.inflate(R.layout.wieght_number_add_sub, this, true);
        mAddBtn = (Button) findViewById(R.id.add_btn);
        mSubBtn = (Button) findViewById(R.id.sub_btn);
        mNumTV = (TextView) findViewById(R.id.num_tv);

        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }
}
