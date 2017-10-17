package commin.pro.diseasemanagement.page.manage_highblood;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import commin.pro.diseasemanagement.R;

public class Page2Manage_Highblood extends Activity {
    private ViewPagerIndicator vp_highblood_manage_indicator;
    private ViewPager vp_highblood_manage;
    private FrameLayout fl_highblood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_manage__highblood_layout);
        init();

    }

    private void init() {
        vp_highblood_manage = (ViewPager) findViewById(R.id.vp_highblood_manage);
        vp_highblood_manage_indicator = (ViewPagerIndicator) findViewById(R.id.vp_highblood_manage_indicator);
        vp_highblood_manage.setAdapter(new MyPagerAdapter());
        vp_highblood_manage_indicator.setupWithViewPager(vp_highblood_manage);
        vp_highblood_manage_indicator.addOnPageChangeListener(mOnPageChangeListener);

    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.page_one;
                    break;
                case 1:
                    resId = R.id.page_two;
                    break;
                case 2:
                    resId = R.id.page_one;
                    break;
            }
            return findViewById(resId);
        }

        @Override
        public boolean isViewFromObject(final View arg0, final Object arg1) {
            return arg0 == ((View) arg1);
        }

        @Override
        public void destroyItem(final ViewGroup container, final int position, final Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(final int position) {
            return String.valueOf(position);
        }
    }

    @NonNull
    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {
            Toast.makeText(Page2Manage_Highblood.this, "Page selected " + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }
    };
}
