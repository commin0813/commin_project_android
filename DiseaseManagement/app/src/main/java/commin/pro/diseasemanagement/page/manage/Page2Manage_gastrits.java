package commin.pro.diseasemanagement.page.manage;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Gastrits_1;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Gastrits_2;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Gastrits_3;

public class Page2Manage_gastrits extends AppCompatActivity {
    ViewPager vp;
    private ViewPagerIndicator vp_manage_indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_manage_gastrits_layout);
        vp = (ViewPager)findViewById(R.id.vp_manage);
         vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);
        vp_manage_indicator = (ViewPagerIndicator) findViewById(R.id.vp_manage_indicator);
        vp_manage_indicator.setupWithViewPager(vp);
        vp_manage_indicator.addOnPageChangeListener(mOnPageChangeListener);

        ((ImageView)findViewById(R.id.iv_go_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Fregment2Gastrits_1();
                case 1:
                    return new Fregment2Gastrits_2();
                case 2:
                    return new Fregment2Gastrits_3();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }

    @NonNull
    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }
    };


}


