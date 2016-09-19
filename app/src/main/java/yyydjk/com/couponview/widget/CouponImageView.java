package yyydjk.com.couponview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import yyydjk.com.library.CouponViewHelper;

/**
 * Created by dongjunkun on 2016/9/19.
 */

public class CouponImageView extends ImageView {
    private CouponViewHelper helper;

    public CouponImageView(Context context) {
        this(context, null);
    }

    public CouponImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        helper = new CouponViewHelper(this, context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        helper.onSizeChanged(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        helper.onDraw(canvas);
    }

    public float getSemicircleGap() {
        return helper.getSemicircleGap();
    }

    public void setSemicircleGap(float semicircleGap) {
        helper.setSemicircleGap(semicircleGap);
    }

    public float getSemicircleRadius() {
        return helper.getSemicircleRadius();
    }

    public void setSemicircleRadius(float semicircleRadius) {
        helper.setSemicircleRadius(semicircleRadius);
    }

    public int getSemicircleColor() {
        return helper.getSemicircleColor();
    }

    public void setSemicircleColor(int semicircleColor) {
        helper.setSemicircleColor(semicircleColor);
    }

    public float getDashLineLength() {
        return helper.getDashLineLength();
    }

    public void setDashLineLength(float dashLineLength) {
        helper.setDashLineLength(dashLineLength);
    }

    public float getDashLineHeight() {
        return helper.getDashLineHeight();
    }

    public void setDashLineHeight(float dashLineHeight) {
        helper.setDashLineHeight(dashLineHeight);
    }

    public float getDashLineGap() {
        return helper.getDashLineGap();
    }

    public void setDashLineGap(float dashLineGap) {
        helper.setDashLineGap(dashLineGap);
    }

    public int getDashLineColor() {
        return helper.getDashLineColor();
    }

    public void setDashLineColor(int dashLineColor) {
        helper.setDashLineColor(dashLineColor);
    }

    public boolean isTopSemicircle() {
        return helper.isTopSemicircle();
    }

    public void setTopSemicircle(boolean topSemicircle) {
        helper.setTopSemicircle(topSemicircle);
    }

    public boolean isBottomSemicircle() {
        return helper.isBottomSemicircle();
    }

    public void setBottomSemicircle(boolean bottomSemicircle) {
        helper.setBottomSemicircle(bottomSemicircle);
    }

    public boolean isLeftSemicircle() {
        return helper.isLeftSemicircle();
    }

    public void setLeftSemicircle(boolean leftSemicircle) {
        helper.setLeftSemicircle(leftSemicircle);
    }

    public boolean isRightSemicircle() {
        return helper.isRightSemicircle();
    }

    public void setRightSemicircle(boolean rightSemicircle) {
        helper.setRightSemicircle(rightSemicircle);
    }

    public boolean isTopDashLine() {
        return helper.isTopDashLine();
    }

    public void setTopDashLine(boolean topDashLine) {
        helper.setTopDashLine(topDashLine);
    }

    public boolean isBottomDashLine() {
        return helper.isBottomDashLine();
    }

    public void setBottomDashLine(boolean bottomDashLine) {
        helper.setBottomDashLine(bottomDashLine);
    }

    public boolean isLeftDashLine() {
        return helper.isLeftDashLine();
    }

    public void setLeftDashLine(boolean leftDashLine) {
        helper.setLeftDashLine(leftDashLine);
    }

    public boolean isRightDashLine() {
        return helper.isRightDashLine();
    }

    public void setRightDashLine(boolean rightDashLine) {
        helper.setRightDashLine(rightDashLine);
    }
}