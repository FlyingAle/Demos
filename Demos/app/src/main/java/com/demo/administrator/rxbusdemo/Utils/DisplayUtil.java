package com.demo.administrator.rxbusdemo.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import java.util.HashMap;
import java.util.Map;

public class DisplayUtil {
    private static Map<ScreenEnum,Integer> screenMap = new HashMap<>();
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue, Context context) {
      return (int) (pxValue / getScreenMsg(context).get(ScreenEnum.Density) + 0.5f);
    }
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @return
     */
    public static int dip2px(float dipValue, Context context) {
      return (int) (dipValue * getScreenMsg(context).get(ScreenEnum.Density) + 0.5f);
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @return
     */
    public static int px2sp(float pxValue, Context context) {
      return (int) (pxValue / getScreenMsg(context).get(ScreenEnum.ScaledDensity) + 0.5f);
    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     * @return
     */
    public static int sp2px(float spValue, Context context) {
      return (int) (spValue * getScreenMsg(context).get(ScreenEnum.ScaledDensity) + 0.5f);
    }
    /**
     * 获取屏幕尺寸等信息
     */
    public static Map<ScreenEnum,Integer> getScreenMsg(Context context){
      DisplayMetrics metric = context.getResources().getDisplayMetrics();
      int width = metric.widthPixels;
      int height = metric.heightPixels;
      float density = metric.density;///屏幕密度（0.75, 1.0 . 1.5）
      int densityDpi = metric.densityDpi;///屏幕密度DPI（120/160/240/320/480）
      float scaledDensity = metric.scaledDensity;
      if (screenMap==null) screenMap = new HashMap<>();

      screenMap.clear();
      screenMap.put(ScreenEnum.Width,width);
      screenMap.put(ScreenEnum.Height,height);
      screenMap.put(ScreenEnum.Density,(int)density);
      screenMap.put(ScreenEnum.DendityDpi,densityDpi);
      screenMap.put(ScreenEnum.ScaledDensity, (int)scaledDensity);
      return screenMap;
    }
    enum ScreenEnum{
      Width,Height,Density,DendityDpi,ScaledDensity
    }

/*  作者：mimimomo
  链接：https://www.jianshu.com/p/10ab0154c507
  來源：简书
  著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
