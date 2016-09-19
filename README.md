##介绍
最近项目中刚好需要做优惠券效果，其他的都不难，关键在一个半圆锯齿和虚线边框的绘制，当然可以使用png图片作为背景来实现，这样很简单，但这样做会拉低整个App的档次，效果不好，修改也麻烦，之前看过网上有人用代码实现了这个效果，看了下原理，但始终用起来问题比较多，自己就稍微总结了下，整理一个可以简单自定义效果的库，可以先看看效果图

![couponView.png](http://upload-images.jianshu.io/upload_images/697635-b612818452d2d91c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我自己遇到的场景是第三种，上面是锯齿，其他三侧均为虚线，当然，还有更多的可以自定义选项，稍后介绍。

##项目导入
在android工程根目录的build.gradle添加
~~~
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
~~~

然后在当前模块的build.gradle添加依赖
~~~
dependencies {
	        compile 'com.github.dongjunkun:CouponView:1.0'
	}
~~~

##自定义属性说明
| 自定义属性 |格式|说明|
| :---:|:---:|:---: |
|cv_dash_line_color|color|虚线的颜色|
|cv_dash_line_gap|dimension|虚线的间隔|
|cv_dash_line_height|dimension|虚线的高度|
|cv_dash_line_length|dimension|虚线的长度|
|cv_semicircle_color|color|半圆的颜色，一般需要和背景色一致|
|cv_semicircle_gap|dimension|半圆之前的间隔|
|cv_semicircle_radius|dimension|半圆的半径|
|cv_is_top_semicircle|boolean|是否绘制顶部半圆锯齿|
|cv_is_bottom_semicircle|boolean|是否绘制底部半圆锯齿|
|cv_is_left_semicircle|boolean|是否绘制左侧半圆锯齿|
|cv_is_right_semicircle|boolean|是否绘制右侧半圆锯齿|
|cv_is_top_dash_line|boolean|是否绘制顶部虚线|
|cv_is_bottom_dash_line|boolean|是否绘制底部虚线|
|cv_is_left_dash_line|boolean|是否绘制左侧虚线|
|cv_is_right_dash_line|boolean|是否绘制右侧虚线|

使用
~~~
<yyydjk.com.library.CouponView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/couponView"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:background="@color/colorAccent"
    android:orientation="vertical"
    android:padding="10dp" /*虚线离边缘的距离*/
    app:cv_dash_line_color="@android:color/white"
    app:cv_dash_line_gap="5dp"
    app:cv_dash_line_height="2dp"
    app:cv_dash_line_length="10dp"
    app:cv_is_bottom_dash_line="true"
    app:cv_is_bottom_semicircle="false"
    app:cv_is_left_dash_line="true"
    app:cv_is_left_semicircle="false"
    app:cv_is_right_dash_line="true"
    app:cv_is_right_semicircle="false"
    app:cv_is_top_dash_line="true"
    app:cv_is_top_semicircle="false"
    app:cv_semicircle_color="@android:color/white"
    app:cv_semicircle_gap="8dp"
    app:cv_semicircle_radius="4dp">
</yyydjk.com.library.CouponView>
~~~

##注意事项
 - 可以通过设置padding属性来控制虚线离边缘的距离
 - CouponView是继承于FrameLayout，除了边缘锯齿和虚线边框外，和普通的FrameLayout没有区别

##定制自己的View
可以通过CouponViewHelper这个代理类来给其他View（比如LinearLayout,ImageView,TextView）添加锯齿背景，只需要继承其他View然后添加以下代码就可以了
~~~
public class CustomView extends YourView{

    private CouponViewHelper helper;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
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

~~~
可以轻松的让一个ImageView变成这样，效果图如下

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/697635-8aecc468ca89ccac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


