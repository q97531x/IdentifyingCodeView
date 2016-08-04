package com.example.identifyingcodeview;

import android.text.InputFilter;
import android.text.InputType;
import android.view.ViewGroup;

/**
 * Created by weeboos on 16/7/18.
 * config a yaurself EditText
 */
public class EditTextConfig {
    //style
    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    //输入格式
    int inputType = InputType.TYPE_CLASS_NUMBER;
    //gravity
    int Gravity = android.view.Gravity.CENTER;
    //输入限制
    InputFilter[] filters = new InputFilter[]{};
    //字符大小
    int textSize = 20;
    //字符颜色
    int textColor = R.color.deepgrey;
    //背景资源id
    int resId = R.drawable.dt_shape_rect_border_frame;
    //光标颜色
    int cusorColor = R.color.mainColor;

    /**
     * config Builder
     */
    public static class Builder{
        //LayoutParams
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //inputType
        int inputType = InputType.TYPE_CLASS_NUMBER;
        //gravity
        int Gravity = android.view.Gravity.CENTER;
        //inputLimit
        InputFilter[] filters = new InputFilter[]{};
        //textSize
        int textSize = 20;
        //text color
        int textColor = R.color.deepgrey;
        //Background Resuorse id
        int resId = R.drawable.dt_shape_rect_border_frame;
        //Cusor color
        int cusorColor = R.color.mainColor;

        /**
         * Set LayoutParms
         * @param layoutParams
         * @return
         */
        public Builder setLayoutParams(ViewGroup.LayoutParams layoutParams){
            lp = layoutParams;
            return this;
        }

        /**
         * Set InputType
         * @param type
         * @return
         */
        public Builder setInputType(int type){
            inputType = type;
            return this;
        }

        /**
         * Set gravity
         * @param gravity
         * @return
         */
        public Builder setGravity(int gravity){
            this.Gravity = gravity;
            return this;
        }

        /**
         * Set Input Limit
         * @param count
         * @return
         */
        public Builder setFilters(int count){
            this.filters = new InputFilter[]{new InputFilter.LengthFilter(count)};
            return this;
        }

        /**
         * Set text Size
         * @param size
         * @return
         */
        public Builder setTextSize(int size){
            this.textSize = size;
            return this;
        }

        /**
         * Set Color
         * @param color
         * @return
         */
        public Builder setTextColor(int color){
            this.textColor = color;
            return this;
        }

        /**
         * Set Background
         * @param resId
         * @return
         */
        public Builder setBackGround(int resId){
            this.resId = resId;
            return this;
        }

        /**
         * Set CursorColor
         * @param color
         * @return
         */
        public Builder setCursorColor(int color){
            this.cusorColor = color;
            return this;
        }

        private void applyConfig(EditTextConfig config){
            config.lp = this.lp;
            config.inputType = this.inputType;
            config.Gravity = this.Gravity;
            config.filters = this.filters;
            config.textSize = this.textSize;
            config.textColor = this.textColor;
            config.cusorColor = this.cusorColor;
            config.resId = this.resId;
        }

        public EditTextConfig Build(){
            EditTextConfig config = new EditTextConfig();
            applyConfig(config);
            return config;
        }
    }
}
