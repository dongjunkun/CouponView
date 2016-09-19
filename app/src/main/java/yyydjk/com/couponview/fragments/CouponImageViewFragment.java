package yyydjk.com.couponview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yyydjk.com.couponview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CouponImageViewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coupon_image_view, container, false);
    }

}
