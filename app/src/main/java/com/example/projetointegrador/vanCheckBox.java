package com.example.projetointegrador;

import android.content.Context;
import android.util.AttributeSet;

public class vanCheckBox extends androidx.appcompat.widget.AppCompatCheckBox {

    String user;
    String code;

    public vanCheckBox(Context context) {
        super(context);
    }

    public vanCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public vanCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
