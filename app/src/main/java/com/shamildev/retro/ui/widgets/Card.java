package com.shamildev.retro.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;

/**
 * Created by Shamil Lazar on 24.02.2018.
 */

public class Card extends RelativeLayout {
    public Card(Context context) {
        super(context);
        init();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.card,this);



    }

}
