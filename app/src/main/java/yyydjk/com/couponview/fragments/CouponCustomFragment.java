package yyydjk.com.couponview.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import yyydjk.com.couponview.R;
import yyydjk.com.library.CouponView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponCustomFragment extends Fragment {


    @Bind(R.id.couponView) CouponView mCouponView;
    @Bind(R.id.semicircle_top) CheckBox mSemicircleTop;
    @Bind(R.id.semicircle_bottom) CheckBox mSemicircleBottom;
    @Bind(R.id.semicircle_left) CheckBox mSemicircleLeft;
    @Bind(R.id.semicircle_right) CheckBox mSemicircleRight;
    @Bind(R.id.dash_line_top) CheckBox mDashLineTop;
    @Bind(R.id.dash_line_bottom) CheckBox mDashLineBottom;
    @Bind(R.id.dash_line_left) CheckBox mDashLineLeft;
    @Bind(R.id.dash_line_right) CheckBox mDashLineRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon_custom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSemicircleTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setTopSemicircle(isChecked);
            }
        });
        mSemicircleBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setBottomSemicircle(isChecked);
            }
        });
        mSemicircleLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setLeftSemicircle(isChecked);
            }
        });
        mSemicircleRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setRightSemicircle(isChecked);
            }
        });

        mDashLineTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setTopDashLine(isChecked);
            }
        });
        mDashLineBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setBottomDashLine(isChecked);
            }
        });
        mDashLineLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setLeftDashLine(isChecked);
            }
        });
        mDashLineRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setRightDashLine(isChecked);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
