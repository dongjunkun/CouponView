package yyydjk.com.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/18 0018.
 */

public class CouponViewHelper {

    private static final int DEFAULT_SEMICIRCLE_GAP = 4;
    private static final int DEFAULT_DASH_LINE_LENGTH = 10;
    private static final int DEFAULT_SEMICIRCLE_RADIUS = 4;
    private static final int DEFAULT_SEMICIRCLE_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_DASH_LINE_HEIGHT = 1;
    private static final int DEFAULT_DASH_LINE_GAP = 5;
    private static final int DEFAULT_DASH_LINE_COLOR = 0xFFFFFFFF;

    private Context context;

    private View view;

    //半圆画笔
    private Paint semicirclePaint;

    //虚线画笔
    private Paint dashLinePaint;

    //半圆之间间距
    private float semicircleGap = DEFAULT_SEMICIRCLE_GAP;

    //半圆半径
    private float semicircleRadius = DEFAULT_SEMICIRCLE_RADIUS;

    //半圆颜色
    private int semicircleColor = DEFAULT_SEMICIRCLE_COLOR;

    //半圆数量X
    private int semicircleNumX;

    //半圆数量Y
    private int semicircleNumY;

    //绘制半圆曲线后X轴剩余距离
    private int remindSemicircleX;

    //绘制半圆曲线后Y轴剩余距离
    private int remindSemicircleY;

    //虚线的长度
    private float dashLineLength = DEFAULT_DASH_LINE_LENGTH;

    //虚线的高度
    private float dashLineHeight = DEFAULT_DASH_LINE_HEIGHT;

    //虚线的间距
    private float dashLineGap = DEFAULT_DASH_LINE_GAP;

    //虚线的颜色
    private int dashLineColor = DEFAULT_DASH_LINE_COLOR;

    //绘制虚线后X轴剩余距离
    private int remindDashLineX;

    //绘制虚线后Y轴剩余距离
    private int remindDashLineY;

    //虚线数量X
    private int dashLineNumX;

    //半圆数量Y
    private int dashLineNumY;

    //开启顶部半圆曲线
    private boolean isTopSemicircle = true;

    //开启底部半圆曲线
    private boolean isBottomSemicircle = true;

    //开启左边半圆曲线
    private boolean isLeftSemicircle = false;

    //开启右边半圆曲线
    private boolean isRightSemicircle = false;

    //开启顶部虚线
    private boolean isTopDashLine = false;

    //开启底部虚线
    private boolean isBottomDashLine = false;

    //开启左边虚线
    private boolean isLeftDashLine = true;

    //开启左边虚线
    private boolean isRightDashLine = true;


    public CouponViewHelper(View view, Context context, AttributeSet attrs, int defStyle) {
        this.context = context;
        this.view = view;
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CouponView, defStyle, 0);
        semicircleRadius = a.getDimensionPixelSize(R.styleable.CouponView_cv_semicircle_radius, dp2Px(DEFAULT_SEMICIRCLE_RADIUS));
        semicircleGap = a.getDimensionPixelSize(R.styleable.CouponView_cv_semicircle_gap, dp2Px(DEFAULT_SEMICIRCLE_GAP));
        semicircleColor = a.getColor(R.styleable.CouponView_cv_semicircle_color, DEFAULT_SEMICIRCLE_COLOR);
        dashLineGap = a.getDimensionPixelSize(R.styleable.CouponView_cv_dash_line_gap, dp2Px(DEFAULT_DASH_LINE_GAP));
        dashLineHeight = a.getDimensionPixelSize(R.styleable.CouponView_cv_dash_line_height, dp2Px(DEFAULT_DASH_LINE_HEIGHT));
        dashLineLength = a.getDimensionPixelSize(R.styleable.CouponView_cv_dash_line_length, dp2Px(DEFAULT_DASH_LINE_LENGTH));
        dashLineColor = a.getColor(R.styleable.CouponView_cv_dash_line_color, DEFAULT_DASH_LINE_COLOR);
        isTopSemicircle = a.getBoolean(R.styleable.CouponView_cv_is_top_semicircle, isTopSemicircle);
        isBottomSemicircle = a.getBoolean(R.styleable.CouponView_cv_is_bottom_semicircle, isBottomSemicircle);
        isLeftSemicircle = a.getBoolean(R.styleable.CouponView_cv_is_left_semicircle, isLeftSemicircle);
        isRightSemicircle = a.getBoolean(R.styleable.CouponView_cv_is_right_semicircle, isRightSemicircle);
        isTopDashLine = a.getBoolean(R.styleable.CouponView_cv_is_top_dash_line, isTopDashLine);
        isBottomDashLine = a.getBoolean(R.styleable.CouponView_cv_is_bottom_dash_line, isBottomDashLine);
        isLeftDashLine = a.getBoolean(R.styleable.CouponView_cv_is_left_dash_line, isLeftDashLine);
        isRightDashLine = a.getBoolean(R.styleable.CouponView_cv_is_right_dash_line, isRightDashLine);

        a.recycle();
        init();

    }

    private void init() {
        semicirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        semicirclePaint.setDither(true);
        semicirclePaint.setColor(semicircleColor);
        semicirclePaint.setStyle(Paint.Style.FILL);

        dashLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashLinePaint.setDither(true);
        dashLinePaint.setColor(dashLineColor);
        dashLinePaint.setStyle(Paint.Style.FILL);
    }

    public void onSizeChanged(int w, int h) {
        if (isTopSemicircle && remindSemicircleX == 0 || isBottomSemicircle && remindSemicircleX == 0) {
            remindSemicircleX = (int) ((w - semicircleGap) % (2 * semicircleRadius + semicircleGap));
            semicircleNumX = (int) ((w - semicircleGap) / (2 * semicircleRadius + semicircleGap));
        }

        if (isLeftSemicircle && remindSemicircleY == 0 || isRightSemicircle && remindSemicircleY == 0) {
            remindSemicircleY = (int) ((h - semicircleGap) % (2 * semicircleRadius + semicircleGap));
            semicircleNumY = (int) ((h - semicircleGap) / (2 * semicircleRadius + semicircleGap));
        }

        if (isTopDashLine && remindDashLineX == 0 || isBottomDashLine && remindDashLineX == 0) {
            remindDashLineX = (int) ((w + dashLineGap - view.getPaddingLeft() - view.getPaddingRight()) % (dashLineLength + dashLineGap));
            dashLineNumX = (int) ((w + dashLineGap - view.getPaddingLeft() - view.getPaddingRight()) / (dashLineLength + dashLineGap));
        }

        if (isLeftDashLine && remindDashLineY == 0 || isRightDashLine && remindDashLineY == 0) {
            remindDashLineY = (int) ((h + dashLineGap - view.getPaddingTop() - view.getPaddingBottom()) % (dashLineLength + dashLineGap));
            dashLineNumY = (int) ((h + dashLineGap - view.getPaddingTop() - view.getPaddingBottom()) / (dashLineLength + dashLineGap));
        }

    }

    public void onDraw(Canvas canvas) {
        if (isTopSemicircle)
            for (int i = 0; i < semicircleNumX; i++) {
                float x = semicircleGap + semicircleRadius + remindSemicircleX / 2 + (semicircleGap + semicircleRadius * 2) * i;
                canvas.drawCircle(x, 0, semicircleRadius, semicirclePaint);
            }
        if (isBottomSemicircle)
            for (int i = 0; i < semicircleNumX; i++) {
                float x = semicircleGap + semicircleRadius + remindSemicircleX / 2 + (semicircleGap + semicircleRadius * 2) * i;
                canvas.drawCircle(x, view.getHeight(), semicircleRadius, semicirclePaint);
            }
        if (isLeftSemicircle)
            for (int i = 0; i < semicircleNumY; i++) {
                float y = semicircleGap + semicircleRadius + remindSemicircleY / 2 + (semicircleGap + semicircleRadius * 2) * i;
                canvas.drawCircle(0, y, semicircleRadius, semicirclePaint);
            }
        if (isRightSemicircle)
            for (int i = 0; i < semicircleNumY; i++) {
                float y = semicircleGap + semicircleRadius + remindSemicircleY / 2 + (semicircleGap + semicircleRadius * 2) * i;
                canvas.drawCircle(view.getWidth(), y, semicircleRadius, semicirclePaint);
            }
        if (isTopDashLine)
            for (int i = 0; i < dashLineNumX; i++) {
                float x = view.getPaddingLeft() + remindDashLineX / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(x, view.getPaddingTop(), x + dashLineLength, view.getPaddingTop() + dashLineHeight, dashLinePaint);
            }
        if (isBottomDashLine)
            for (int i = 0; i < dashLineNumX; i++) {
                float x = view.getPaddingLeft() + remindDashLineX / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(x, view.getHeight() - dashLineHeight - view.getPaddingBottom(), x + dashLineLength, view.getHeight() - view.getPaddingBottom(), dashLinePaint);
            }
        if (isLeftDashLine)
            for (int i = 0; i < dashLineNumY; i++) {
                float y = view.getPaddingTop() + remindDashLineY / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(view.getPaddingLeft(), y, view.getPaddingLeft() + dashLineHeight, y + dashLineLength, dashLinePaint);
            }
        if (isRightDashLine)
            for (int i = 0; i < dashLineNumY; i++) {
                float y = view.getPaddingTop() + remindDashLineY / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(view.getWidth() - view.getPaddingRight() - dashLineHeight, y, view.getWidth() - view.getPaddingRight(), y + dashLineLength, dashLinePaint);
            }
    }

    private int dp2Px(float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private int px2Dp(float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public float getSemicircleGap() {
        return semicircleGap;
    }

    public void setSemicircleGap(float semicircleGap) {
        this.semicircleGap = semicircleGap;
        view.invalidate();
    }

    public float getSemicircleRadius() {
        return semicircleRadius;
    }

    public void setSemicircleRadius(float semicircleRadius) {
        this.semicircleRadius = semicircleRadius;
        view.invalidate();
    }

    public int getSemicircleColor() {
        return semicircleColor;
    }

    public void setSemicircleColor(int semicircleColor) {
        if (this.semicircleColor != semicircleColor) {
            this.semicircleColor = semicircleColor;
            view.invalidate();
        }
    }

    public float getDashLineLength() {
        return dashLineLength;
    }

    public void setDashLineLength(float dashLineLength) {
        this.dashLineLength = dashLineLength;
        view.invalidate();
    }

    public float getDashLineHeight() {
        return dashLineHeight;
    }

    public void setDashLineHeight(float dashLineHeight) {
        this.dashLineHeight = dashLineHeight;
        view.invalidate();
    }

    public float getDashLineGap() {
        return dashLineGap;
    }

    public void setDashLineGap(float dashLineGap) {
        this.dashLineGap = dashLineGap;
        view.invalidate();
    }

    public int getDashLineColor() {
        return dashLineColor;
    }

    public void setDashLineColor(int dashLineColor) {
        this.dashLineColor = dashLineColor;
    }

    public boolean isTopSemicircle() {
        return isTopSemicircle;
    }

    public void setTopSemicircle(boolean topSemicircle) {
        isTopSemicircle = topSemicircle;
    }

    public boolean isBottomSemicircle() {
        return isBottomSemicircle;
    }

    public void setBottomSemicircle(boolean bottomSemicircle) {
        isBottomSemicircle = bottomSemicircle;
    }

    public boolean isLeftSemicircle() {
        return isLeftSemicircle;
    }

    public void setLeftSemicircle(boolean leftSemicircle) {
        isLeftSemicircle = leftSemicircle;
    }

    public boolean isRightSemicircle() {
        return isRightSemicircle;
    }

    public void setRightSemicircle(boolean rightSemicircle) {
        isRightSemicircle = rightSemicircle;
    }

    public boolean isTopDashLine() {
        return isTopDashLine;
    }

    public void setTopDashLine(boolean topDashLine) {
        isTopDashLine = topDashLine;
    }

    public boolean isBottomDashLine() {
        return isBottomDashLine;
    }

    public void setBottomDashLine(boolean bottomDashLine) {
        isBottomDashLine = bottomDashLine;
    }

    public boolean isLeftDashLine() {
        return isLeftDashLine;
    }

    public void setLeftDashLine(boolean leftDashLine) {
        isLeftDashLine = leftDashLine;
    }

    public boolean isRightDashLine() {
        return isRightDashLine;
    }

    public void setRightDashLine(boolean rightDashLine) {
        isRightDashLine = rightDashLine;
    }
}
