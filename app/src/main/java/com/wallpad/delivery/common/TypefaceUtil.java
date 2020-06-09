package com.wallpad.delivery.common;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class TypefaceUtil {
     public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
      try {
          final Typeface customFontTypeface = Typeface.createFromFile("/system/fonts/" + customFontFileNameInAssets);


          final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
          defaultFontTypefaceField.setAccessible(true);
          defaultFontTypefaceField.set(null, customFontTypeface);
      } catch (Exception e) {
          e.printStackTrace();
   //       LogGsmart.e(Constant.KEY_LOG_SYSTEM, "Error","Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
      }
  }
}
