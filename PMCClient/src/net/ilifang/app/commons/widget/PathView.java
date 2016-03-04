package net.ilifang.app.commons.widget;

import net.ilifang.app.pmc.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class PathView extends RelativeLayout {
    private boolean isExpand = false;
    private View[] items;
    private View startMenu;
    private View bgView;
    public int DURATION = 300;
    public int MARGIN_RIGHT = 16;
    public int MARGIN_TOP = 16;
    public int MARGIN_BOTTOM = 16;

    public PathView(Context context) {
        super(context);
        initContentView();
    }

    public PathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initContentView();

    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContentView();
    }

    private void initContentView() {
        bgView = new View(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(bgView, params);
        bgView.setVisibility(View.INVISIBLE);
        bgView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    shrink(DURATION);
                    bgView.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 设置菜单
     * 
     * @param view
     */
    public void setStartMenu(View view) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // 设置对齐方式
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        // 设置菜单各个边距
        params.rightMargin = MARGIN_RIGHT;
        params.bottomMargin = MARGIN_BOTTOM;
        params.topMargin = MARGIN_TOP;
        addView(view, params);
        this.startMenu = view;
        // 设置Id方便后面布局使用
        startMenu.setId(100);
        startMenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isExpand) {
                    shrink(DURATION);
                } else {
                    expand(DURATION);
                }
            }
        });
    }

    /**
     * 展开菜单
     * 
     * @param duration
     */
    @SuppressLint("NewApi")
    public void expand(int duration) {
        bgView.setVisibility(View.VISIBLE);
        this.setBackgroundColor(getResources().getColor(R.color.headColorTransparent));
        for (int i = 0; i < items.length; i++) {
            final View view = items[i];
            view.setEnabled(false);
            float dy = startMenu.getY() - view.getY();
            TranslateAnimation translateAnim = new TranslateAnimation(-startMenu.getWidth() / 2, 0, dy, 0);
            translateAnim.setDuration(duration);
            ScaleAnimation scaleAnim = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnim.setDuration(duration);
            AnimationSet set = new AnimationSet(true);
            set.addAnimation(scaleAnim);
            set.addAnimation(translateAnim);
            set.setInterpolator(getContext(), android.R.anim.linear_interpolator);
            set.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isExpand = true;
                    view.setEnabled(true);
                }
            });
            roate(0, -135, DURATION);
            view.startAnimation(set);
        }

    }

    /**
     * 收起菜单
     * 
     * @param duration
     */
    @SuppressLint("NewApi")
    public void shrink(int duration) {
        for (int i = 0; i < items.length; i++) {
            final View view = items[i];
            view.setEnabled(false);
            float dy = startMenu.getY() - view.getY();
            TranslateAnimation translateAnim = new TranslateAnimation(0, -startMenu.getWidth() / 2, 0, dy);
            translateAnim.setDuration(duration);
            ScaleAnimation scaleAnim = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnim.setDuration(duration);
            AnimationSet set = new AnimationSet(true);
            set.addAnimation(scaleAnim);
            set.addAnimation(translateAnim);
            set.setInterpolator(getContext(), android.R.anim.linear_interpolator);
            set.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setEnabled(true);
                    view.setVisibility(View.GONE);
                    isExpand = false;
                }
            });
            bgView.setVisibility(View.GONE);
            roate(-135, 0, DURATION);
            view.startAnimation(set);
        }
        this.setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * 旋转动画
     * 
     * @param fromDegree
     * @param toDegree
     * @param duration
     */
    private void roate(int fromDegree, int toDegree, int duration) {
        RotateAnimation anim = new RotateAnimation(fromDegree, toDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(duration);
        anim.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                startMenu.setEnabled(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMenu.setEnabled(true);
            }
        });
        startMenu.startAnimation(anim);
    }

    /**
     * 设置各个子菜单
     * 
     * @param items
     */
    public void setItems(View[] items) {
        for (int i = 0; i < items.length; i++) {
            View view = items[i];
            view.setId(i + 1);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            // 设置子菜单依次向上排列
            if (i == 0) {
                params.addRule(RelativeLayout.ABOVE, startMenu.getId());
            } else {
                params.addRule(RelativeLayout.ABOVE, items[i - 1].getId());
            }
            // params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_RIGHT, startMenu.getId());
            // params.rightMargin = MARGIN_RIGHT;
            addView(view, params);
            view.setVisibility(View.INVISIBLE);
        }
        this.items = items;
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        for (int i = 0; i < items.length; i++) {
            final View view = items[i];
            final int position = i;
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (v.getVisibility() == View.VISIBLE) {
                        shrink(DURATION);
                        fadeOut(view, DURATION);
                        listener.onItemClick(view, position);
                    }
                }
            });
        }
    }

    /**
     * 子菜单淡出动画
     * 
     * @param view
     * @param duration
     */
    private void fadeOut(final View view, int duration) {
        AlphaAnimation alphaAnim = new AlphaAnimation(1, 0.0f);
        alphaAnim.setDuration(duration);
        ScaleAnimation scaleAnim = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(duration);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(scaleAnim);
        set.addAnimation(alphaAnim);
        set.setInterpolator(getContext(), android.R.anim.accelerate_interpolator);
        set.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
        });
        view.startAnimation(set);

    }

    public boolean isExpand() {
        return isExpand;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}