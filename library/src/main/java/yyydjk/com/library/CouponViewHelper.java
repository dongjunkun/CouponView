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
    private static final int DEFAULT_DASH_LINE_MARGIN = 10;

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

    //view宽度
    private int viewWidth;

    //view的高度
    private int viewHeight;

    //顶部虚线距离View顶部的距离
    private float topDashLineMargin = DEFAULT_DASH_LINE_MARGIN;

    //底部虚线距离View底部的距离
    private float bottomDashLineMargin = DEFAULT_DASH_LINE_MARGIN;

    //左侧虚线距离View左侧的距离
    private float leftDashLineMargin = DEFAULT_DASH_LINE_MARGIN;

    //右侧虚线距离View右侧的距离
    private float rightDashLineMargin = DEFAULT_DASH_LINE_MARGIN;


    public CouponViewHelper(View view, Context context, AttributeSet attrs, int defStyle) {
        this.context = context;
        this.view = view;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CouponView, defStyle, 0);
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

        topDashLineMargin = a.getDimensionPixelSize(R.styleable.CouponView_cv_top_dash_line_margin, dp2Px(DEFAULT_DASH_LINE_MARGIN));
        bottomDashLineMargin = a.getDimensionPixelSize(R.styleable.CouponView_cv_bottom_dash_line_margin, dp2Px(DEFAULT_DASH_LINE_MARGIN));
        leftDashLineMargin = a.getDimensionPixelSize(R.styleable.CouponView_cv_left_dash_line_margin, dp2Px(DEFAULT_DASH_LINE_MARGIN));
        rightDashLineMargin = a.getDimensionPixelSize(R.styleable.CouponView_cv_right_dash_line_margin, dp2Px(DEFAULT_DASH_LINE_MARGIN));

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
        viewWidth = w;
        viewHeight = h;
        calculate();
    }

    private void calculate() {
        if (isTopSemicircle || isBottomSemicircle) {
            remindSemicircleX = (int) ((viewWidth - semicircleGap) % (2 * semicircleRadius + semicircleGap));
            semicircleNumX = (int) ((viewWidth - semicircleGap) / (2 * semicircleRadius + semicircleGap));
        }

        if (isLeftSemicircle || isRightSemicircle) {
            remindSemicircleY = (int) ((viewHeight - semicircleGap) % (2 * semicircleRadius + semicircleGap));
            semicircleNumY = (int) ((viewHeight - semicircleGap) / (2 * semicircleRadius + semicircleGap));
        }

        if (isTopDashLine || isBottomDashLine && remindDashLineX == 0) {
            remindDashLineX = (int) ((viewWidth + dashLineGap - leftDashLineMargin - rightDashLineMargin) % (dashLineLength + dashLineGap));
            dashLineNumX = (int) ((viewWidth + dashLineGap - leftDashLineMargin - rightDashLineMargin) / (dashLineLength + dashLineGap));
        }

        if (isLeftDashLine || isRightDashLine) {
            remindDashLineY = (int) ((viewHeight + dashLineGap - topDashLineMargin - bottomDashLineMargin) % (dashLineLength + dashLineGap));
            dashLineNumY = (int) ((viewHeight + dashLineGap - topDashLineMargin - bottomDashLineMargin) / (dashLineLength + dashLineGap));
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
                canvas.drawCircle(x, viewHeight, semicircleRadius, semicirclePaint);
            }
        if (isLeftSemicircle)
            for (int i = 0; i < semicircleNumY; i++) {
                float y = semicircleGap + semicircleRadius + remindSemicircleY / 2 + (semicircleGap + semicircleRadius * 2) * i;
                canvas.drawCircle(0, y, semicircleRadius, semicirclePaint);
            }
        if (isRightSemicircle)
            for (int i = 0; i < semicircleNumY; i++) {
                float y = semicircleGap + semicircleRadius + remindSemicircleY / 2 + (semicircleGap + semicircleRadius * 2) * i;
                canvas.drawCircle(viewWidth, y, semicircleRadius, semicirclePaint);
            }
        if (isTopDashLine)
            for (int i = 0; i < dashLineNumX; i++) {
                float x = leftDashLineMargin + remindDashLineX / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(x, topDashLineMargin, x + dashLineLength, topDashLineMargin + dashLineHeight, dashLinePaint);
            }
        if (isBottomDashLine)
            for (int i = 0; i < dashLineNumX; i++) {
                float x = leftDashLineMargin + remindDashLineX / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(x, viewHeight - dashLineHeight - bottomDashLineMargin, x + dashLineLength, viewHeight - bottomDashLineMargin, dashLinePaint);
            }
        if (isLeftDashLine)
            for (int i = 0; i < dashLineNumY; i++) {
                float y = topDashLineMargin + remindDashLineY / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(leftDashLineMargin, y, leftDashLineMargin + dashLineHeight, y + dashLineLength, dashLinePaint);
            }
        if (isRightDashLine)
            for (int i = 0; i < dashLineNumY; i++) {
                float y = topDashLineMargin + remindDashLineY / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(viewWidth - rightDashLineMargin - dashLineHeight, y, viewWidth - rightDashLineMargin, y + dashLineLength, dashLinePaint);
            }
    }

    private int dp2Px(float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private int px2Dp(float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public float getSemicircleGap() {
        return px2Dp(semicircleGap);
    }

    public void setSemicircleGap(float semicircleGap) {
        if (this.semicircleGap != semicircleGap) {
            this.semicircleGap = semicircleGap;
            calculate();
            view.invalidate();
        }
    }

    public float getSemicircleRadius() {
        return px2Dp(semicircleRadius);
    }

    public void setSemicircleRadius(float semicircleRadius) {
        if (this.semicircleRadius != semicircleRadius) {
            this.semicircleRadius = semicircleRadius;
            calculate();
            view.invalidate();
        }
    }

    public int getSemicircleColor() {
        return semicircleColor;
    }

    public void setSemicircleColor(int semicircleColor) {
        if (this.semicircleColor != semicircleColor) {
            this.semicircleColor = semicircleColor;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineLength() {
        return px2Dp(dashLineLength);
    }

    public void setDashLineLength(float dashLineLength) {
        if (this.dashLineLength != dashLineLength) {
            this.dashLineLength = dashLineLength;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineHeight() {
        return px2Dp(dashLineHeight);
    }

    public void setDashLineHeight(float dashLineHeight) {
        if (this.dashLineHeight != dashLineHeight) {
            this.dashLineHeight = dashLineHeight;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineGap() {
        return px2Dp(dashLineGap);
    }

    public void setDashLineGap(float dashLineGap) {
        if (this.dashLineGap != dashLineGap) {
            this.dashLineGap = dashLineGap;
            calculate();
            view.invalidate();
        }
    }

    public int getDashLineColor() {
        return dashLineColor;
    }

    public void setDashLineColor(int dashLineColor) {
        if (this.dashLineColor != dashLineColor) {
            this.dashLineColor = dashLineColor;
            calculate();
            view.invalidate();
        }
    }

    public boolean isTopSemicircle() {
        return isTopSemicircle;
    }

    public void setTopSemicircle(boolean topSemicircle) {
        if (this.isTopSemicircle != topSemicircle) {
            isTopSemicircle = topSemicircle;
            calculate();
            view.invalidate();
        }
    }

    public boolean isBottomSemicircle() {
        return isBottomSemicircle;
    }

    public void setBottomSemicircle(boolean bottomSemicircle) {
        if (isBottomSemicircle != bottomSemicircle) {
            isBottomSemicircle = bottomSemicircle;
            calculate();
            view.invalidate();
        }
    }

    public boolean isLeftSemicircle() {
        return isLeftSemicircle;
    }

    public void setLeftSemicircle(boolean leftSemicircle) {
        if (isLeftSemicircle != leftSemicircle) {
            isLeftSemicircle = leftSemicircle;
            calculate();
            view.invalidate();
        }
    }

    public boolean isRightSemicircle() {
        return isRightSemicircle;
    }

    public void setRightSemicircle(boolean rightSemicircle) {
        if (isRightSemicircle != rightSemicircle) {
            isRightSemicircle = rightSemicircle;
            calculate();
            view.invalidate();
        }
    }

    public boolean isTopDashLine() {
        return isTopDashLine;
    }

    public void setTopDashLine(boolean topDashLine) {
        if (isTopDashLine != topDashLine) {
            isTopDashLine = topDashLine;
            calculate();
            view.invalidate();
        }
    }

    public boolean isBottomDashLine() {
        return isBottomDashLine;
    }

    public void setBottomDashLine(boolean bottomDashLine) {
        if (isBottomDashLine != bottomDashLine) {
            isBottomDashLine = bottomDashLine;
            calculate();
            view.invalidate();
        }
    }

    public boolean isLeftDashLine() {
        return isLeftDashLine;
    }

    public void setLeftDashLine(boolean leftDashLine) {
        if (isLeftDashLine != leftDashLine) {
            isLeftDashLine = leftDashLine;
            calculate();
            view.invalidate();
        }
    }

    public boolean isRightDashLine() {
        return isRightDashLine;
    }

    public void setRightDashLine(boolean rightDashLine) {
        if (isRightDashLine != rightDashLine) {
            isRightDashLine = rightDashLine;
            calculate();
            view.invalidate();
        }
    }

    public float getTopDashLineMargin() {
        return px2Dp(topDashLineMargin);
    }

    public void setTopDashLineMargin(float topDashLineMargin) {
        if (this.topDashLineMargin != topDashLineMargin) {
            this.topDashLineMargin = topDashLineMargin;
            calculate();
            view.invalidate();
        }
    }

    public float getBottomDashLineMargin() {
        return px2Dp(bottomDashLineMargin);
    }

    public void setBottomDashLineMargin(float bottomDashLineMargin) {
        if (this.bottomDashLineMargin != bottomDashLineMargin) {
            this.bottomDashLineMargin = bottomDashLineMargin;
            calculate();
            view.invalidate();
        }
    }

    public float getLeftDashLineMargin() {
        return px2Dp(leftDashLineMargin);
    }

    public void setLeftDashLineMargin(float leftDashLineMargin) {
        if (this.leftDashLineMargin != leftDashLineMargin) {
            this.leftDashLineMargin = leftDashLineMargin;
            calculate();
            view.invalidate();
        }
    }

    public float getRightDashLineMargin() {
        return px2Dp(rightDashLineMargin);
    }

    public void setRightDashLineMargin(float rightDashLineMargin) {
        if (this.rightDashLineMargin != rightDashLineMargin) {
            this.rightDashLineMargin = rightDashLineMargin;
            calculate();
            view.invalidate();
        }
    }
}
