package com.whoshungry.stevenzhang.whoshungry;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;


public class InviteNumFriendsView extends View {

    public InviteNumFriendsView(Context context) {
        this(context, null);
    }

    public InviteNumFriendsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.InviteNumFriendsView,
                0, 0);

        a.recycle();
    }


}