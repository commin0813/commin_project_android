package com.commin.pro.comminproject.page.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.commin.pro.comminproject.page.ApplicationProperty;

/**
 * Created by user on 2017-07-25.
 */
public class Adapter2Pager extends FragmentStatePagerAdapter{
    public Adapter2Pager(android.support.v4.app.FragmentManager fm){super(fm);}

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return new Frag2Content();
        }else if(position == 1){
            return new Frag2Manage();
        } else{
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
