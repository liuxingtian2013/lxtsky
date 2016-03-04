package net.ilifang.app.commons.utils;

import java.math.BigDecimal;

import net.ilifang.app.commons.widget.CustomProgressDialog;
import net.ilifang.app.pmc.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 界面表现层工具类
 *
 * @Description Activity工具类
 */
public class ViewUtils {

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int convertDpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    /**
     * 计算组件自身的高度和宽度
     *
     * @param view
     * @return
     */
    public static int[] measureSpec(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
//        Log.i(AnnouncementAdapter.TAG, "measuer result : {width:" + width + ",height:" + height + "}");
        return new int[] { width, height };
    }

    public static String getStringByKey(Context context, String key) {
        if (key != null && key.contains(".")) {
            key = key.replaceAll("\\.", "_");
        }
        int labelKey = context.getResources().getIdentifier(key, "string", context.getPackageName());
        if (labelKey <= 0) {
            return key;
            // return "";
        }
        String label = context.getResources().getString(labelKey);
        return label;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 根据手机的分辨率从 dp的单位转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素)的单位转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 默认字体<br/>
     * {label:value} <br/>
     * 获取value是否能有一行显示完整
     *
     * @param value
     * @return
     */
    public static boolean isValueSingleLine(Context context, int columnMaxWidth, String value) {
        Activity activity = (Activity) context;
        int shouldShowLength = getScreenWidth(activity) - columnMaxWidth;
        int valueWidth = getStringWidthPixel(value, activity);
        if (valueWidth <= shouldShowLength) {
            return true;
        }
        return false;
    }

    /**
     * 默认字体<br/>
     * {label:value} <br/>
     * 获取value是否能有一行显示完整
     *
     * @param value
     * @return
     */
    public static boolean isValueSingleLine(Context context, int columnMaxWidth, float textSize, String value) {
        Activity activity = (Activity) context;
        int shouldShowLength = getScreenWidth(activity) - columnMaxWidth;
        int valueWidth = getStringWidthPixel(value, textSize, activity); // getStringWidthPixel(value,
                                                                         // activity);
        if (valueWidth <= shouldShowLength) {
            return true;
        }
        return false;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 根据文字和padding值来计算textview的宽度
     *
     * @param textView
     * @return
     */
    public static int getTextWidth(TextView textView) {
        float textSize = textView.getPaint().getTextSize();
        int count = textView.getText().length();
        BigDecimal bw = new BigDecimal((Math.floor(textSize * count + textView.getCompoundPaddingLeft() + textView.getCompoundPaddingRight())) + "").setScale(
                0, BigDecimal.ROUND_FLOOR);
        return bw.intValue();
    }

    /**
     * 得到字符串的宽度(像素)
     *
     * @param str
     * @return
     */
    public static int getStringWidthPixel(String str, float textSize, Activity activity) {
        int result = 0;
        textSize = (textSize > 0) ? (textSize + 1) : getDefaultTextSize(activity);
        if (StringUtils.isNotBlank(str)) {
            Paint pFont = new Paint();
            pFont.setTextSize(textSize);
            result = (int) pFont.measureText(str);
        }
        return result;
    }

    /**
     * 得到字符串的宽度(像素)
     *
     * @param str
     * @return
     */
    public static int getStringWidthPixel(String str, Activity activity) {
        return getStringWidthPixel(str, getDefaultTextSize(activity), activity);
    }

    /**
     * 获取默认字体大小
     *
     * @return
     */
    public static int getDefaultTextSize(Activity activity) {
        int defaultTextSize = 18;
        if (getScreenWidth(activity) > 240) {
            defaultTextSize = 25;
        }
        return defaultTextSize;
    }

    /**
     * 获取屏幕分辨率
     *
     * @return
     */
    public static String getResolution(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }

    /**
     * 设置图像移动动画效果
     *
     * @param v
     * @param startX
     * @param toX
     * @param startY
     * @param toY
     */
    public static void setImageSlide(View v, float startX, float toX, float startY, float toY) {
        TranslateAnimation anim = new TranslateAnimation(startX, toX, startY, toY);
        anim.setDuration(100);
        anim.setFillAfter(true);
        v.startAnimation(anim);
    }

    /**
     * 分享
     *
     * @param context
     * @param title
     * @param text
     */
    public static void shareText(Context context, String title, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static void dismissProgressDialog(Context context, CustomProgressDialog progressDialog, String msg) {
        if (null != progressDialog) {
            progressDialog.dismiss();
        }
        if (StringUtils.isNotBlank(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 视图转发
     */
    public static void forward(Context context, Class<?> cls, boolean finishActivity) {
        Intent intent = new Intent(context, cls);
        Activity activity = (Activity) context;
        activity.overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
        context.startActivity(intent);
        if (finishActivity) {
            activity.finish();
        }
    }

    /**
     * 将View转换成Bitmap的方法
     *
     * @param view
     * @return
     */
    public static Bitmap getBitmapFromView(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * 退出
     */
    public static void quit(final Activity activity) {
        new AlertDialog.Builder(activity).setTitle("系统提示").setMessage("确定要退出吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
                // 或者下面这种方式
                // android.os.Process.killProcess(android.os.Process.myPid());
            }
        }).setNegativeButton("取消", null).show();
    }
}
