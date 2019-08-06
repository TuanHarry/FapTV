package com.example.tuantran.faptv.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;

public class TuanTranViewpager extends ViewPager {
    public TuanTranViewpager(@NonNull Context context) {
        super(context);
    }

    public TuanTranViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        return super.canScroll(v, checkV, dx, x, y) || (checkV && customCanScroll(v));
    }

    protected boolean customCanScroll(View v) {
        if (v instanceof Switch) {
            return true;
        }
        return false;
    }
}
