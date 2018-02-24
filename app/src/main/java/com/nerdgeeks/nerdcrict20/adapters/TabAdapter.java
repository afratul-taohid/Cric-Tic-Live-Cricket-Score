package com.nerdgeeks.nerdcrict20.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nerdgeeks.nerdcrict20.fragments.BattingFragment;
import com.nerdgeeks.nerdcrict20.fragments.BowlingFragment;
import com.nerdgeeks.nerdcrict20.fragments.InterFragment;
import com.nerdgeeks.nerdcrict20.fragments.OthersFragment;

/**
 * Created by hp on 5/7/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private Context context;

    public TabAdapter(FragmentManager fm, int tabCount, Context context) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BattingFragment();
            case 1:
                return new BowlingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Batting";
            case 1:
                return "Bowling";
        }
        return null;
//        Drawable image = context.getResources().getDrawable(imageResId[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
    }
}
