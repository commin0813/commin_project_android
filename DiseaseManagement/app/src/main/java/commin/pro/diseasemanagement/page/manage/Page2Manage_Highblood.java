package commin.pro.diseasemanagement.page.manage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Diabetes_1;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Diabetes_2;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Diabetes_3;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Highblood_1;
import commin.pro.diseasemanagement.page.manage.fragment.Fregment2Highblood_2;

public class Page2Manage_Highblood extends AppCompatActivity {
    private ViewPagerIndicator vp_manage_indicator;
    private ViewPager vp_manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_manage__highblood_layout);
        init();

    }

    private void init() {
        vp_manage = (ViewPager)findViewById(R.id.vp_manage);
        vp_manage.setAdapter(new Page2Manage_Highblood.pagerAdapter(getSupportFragmentManager()));
        vp_manage.setCurrentItem(0);
        vp_manage_indicator = (ViewPagerIndicator) findViewById(R.id.vp_manage_indicator);
        vp_manage_indicator.setupWithViewPager(vp_manage);
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
            vp_manage.setCurrentItem(tag);
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
                    return new Fregment2Highblood_1();
                case 1:
                    return new Fregment2Highblood_2();

                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 2;
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
