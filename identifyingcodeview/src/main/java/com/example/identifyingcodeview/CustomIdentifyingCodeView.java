package com.example.identifyingcodeview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by weeboos on 16/7/15.
 * CustomIdentifyingCodeView
 */
public class CustomIdentifyingCodeView extends LinearLayout implements View.OnKeyListener, TextWatcher {
    private int childCount;
    private CompleteListener completeListener;

    public CustomIdentifyingCodeView(Context context) {
        super(context);
        init();
    }

    public CustomIdentifyingCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomIdentifyingCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public void setChildCount(int count, EditTextConfig config) throws Throwable {
        if(count<1){
            count = 1;
        }
        childCount = count;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    EditText editText = initEdit(config);
                    int weight = (config.lp).width;
                    int height = (config.lp).height;
                    LayoutParams lp = new LayoutParams(weight,height);
                    lp.setMargins(0, 0, 0, 0);
                    editText.setLayoutParams(lp);
                    addView(editText, i);
                } else {
                    EditText editText = initEdit(config);
                    editText.setLayoutParams(config.lp);
                    addView(editText, i);
                }
                getChildAt(i).setOnKeyListener(this);
                ((EditText) getChildAt(i)).addTextChangedListener(this);
            }
            getChildAt(0).requestFocus();
            getChildAt(0).setFocusable(true);
            getChildAt(0).setFocusableInTouchMode(true);
        } else {
            throw (new Throwable("setChildCount can't is zero"));
        }

    }

    public EditText initEdit(EditTextConfig config) {
        EditText edit = new EditText(getContext());
//        edit.setLayoutParams(config.lp);
        edit.setInputType(config.inputType);
        edit.setGravity(config.Gravity);
        //限制每个输入框输入字符长度
        edit.setFilters(config.filters);
        edit.setTextSize(config.textSize);
        edit.setBackgroundResource(config.resId);
        edit.setTextColor(getResources().getColor(config.textColor));
        return edit;
    }

    /**
     * clear all editText's text
     */
    public void clear() {
        for(int i = childCount-1;i>-1;i--){
            ((EditText)getChildAt(i)).setText("");
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        EditText childView = (EditText) getFocusedChild();
        //点击退格键
        if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
            childView.setText("");
            for (int i = 0; i < childCount; i++) {
                if (childView.equals(getChildAt(i))) {
                    if (i > 0) {
                        childView.setText("");
                        getChildAt(i - 1).requestFocus();
                    } else {
                        childView.setText("");
                    }
                }
            }
            return true;
        }
        return false;
    }

    public interface CompleteListener {
        void complete();
    }

    public void setCompleteListener(CompleteListener completeListener) {
        this.completeListener = completeListener;
    }

    /**
     * get view's code text
     * @return
     */
    public String getCode() {
        String s = "";
        for (int i = 0; i < childCount; i++) {
            s = s + ((EditText) getChildAt(i)).getText().toString();
        }
        return s;
    }

    /**
     * exchange dp to px depends phone's resolution ratio
     */
    public static int dpToPx(int dpValue,Context context) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!s.toString().equals("")) {
            EditText childView = (EditText) getFocusedChild();
            for (int i = 0; i < childCount; i++) {
                if (childView.equals(getChildAt(i))) {
                    if (i == childCount - 1) {
                        //hide corsor
                        childView.setCursorVisible(false);
                        completeListener.complete();
                    } else {
                        //jump to next editText
                        getChildAt(i + 1).requestFocus();
                    }
                }
            }
        }
    }
}
