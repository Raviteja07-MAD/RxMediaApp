package com.rxmediaapp.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by android-4 on 8/14/2017.
 */

public class CustomBoldTextView extends TextView {

    public CustomBoldTextView(Context context) {
        super(context);
        init();
    }

    public CustomBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public CustomBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {

        if (!isInEditMode()) {

            Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"Gotham Rounded Medium.otf");
            setTypeface(tf);

        }

    }

}
