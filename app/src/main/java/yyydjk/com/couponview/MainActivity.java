package yyydjk.com.couponview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import yyydjk.com.couponview.fragments.CouponGridFragment;
import yyydjk.com.couponview.fragments.CouponListFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tab) TabLayout mTab;
    @Bind(R.id.pager) ViewPager mPager;

    private List<String> titles = Arrays.asList("列表","自定义","卡片");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }
    public class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new CouponListFragment();
                case 1:
                    return new CouponGridFragment();
                default:
                    return new CouponListFragment();
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }
    }


}
