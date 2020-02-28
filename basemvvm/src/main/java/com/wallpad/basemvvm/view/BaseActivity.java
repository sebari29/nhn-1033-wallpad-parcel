package com.wallpad.basemvvm.view;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressLint("Registered")
public abstract class BaseActivity extends ViewModelActivity {
    protected abstract int initLayoutRes();

    protected abstract void initView();

    protected abstract void initAction();

    protected abstract void initData();

    protected  void adjustDisplayScale(){
        Configuration configuration = getResources().getConfiguration();
        if (configuration != null) {
            int densityDpi = getResources().getDisplayMetrics().densityDpi;
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            int screenLayoutSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

            switch (screenLayoutSize) {
                case Configuration.SCREENLAYOUT_SIZE_SMALL:
                    // TODO: Handle small size
//                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
//                        str = "small-xxhdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
//                        str = "small-xxhdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
//                        str = "small-xhdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
//                        str = "small-hdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
//                        str = "small-tvdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
//                        str = "small-mdpi";
//                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
//                        str = "small-low";
//                    }
                    break;
                case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
                        configuration.densityDpi = 320;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
                        if (configuration.densityDpi >= 480) {
                            if (getResources().getDisplayMetrics().widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else if (getResources().getDisplayMetrics().widthPixels >= 2000)
                                configuration.densityDpi = 320;
                            else if (getResources().getDisplayMetrics().widthPixels >= 1900)
                                configuration.densityDpi = 320;
                            else
                                configuration.densityDpi = 300;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
                        if (configuration.densityDpi >= 426)
                            configuration.densityDpi = 300;
                        else
                            configuration.densityDpi = 240;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
                        if (configuration.densityDpi >= 284)
                            configuration.densityDpi = 200;
                        else
                            configuration.densityDpi = 160; // mdpi_xlarge
                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
                        // TODO: Handle TV size if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
                        // TODO: Handle density medium  if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
                        // TODO: Handle density low  if need
                    }
                    break;
                case Configuration.SCREENLAYOUT_SIZE_LARGE:
                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
                        if (configuration.densityDpi >= 480)
                            configuration.densityDpi = 400;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
                        if (configuration.densityDpi >= 426) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else if (widthPixels >= 2000)
                                configuration.densityDpi = 320;
                            else configuration.densityDpi = 320;
                        } else if (configuration.densityDpi >= 372) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else if (widthPixels >= 2000)
                                configuration.densityDpi = 320;
                            else
                                configuration.densityDpi = 300;
                        } else if (configuration.densityDpi >= 300) {
                            configuration.densityDpi = 300;
                        } else {
                            configuration.densityDpi = 240;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
                        if (configuration.densityDpi >= 272)
                            configuration.densityDpi = 300;
                        else if (configuration.densityDpi >= 248)
                            configuration.densityDpi = 200;
                        else if (configuration.densityDpi >= 240)
                            configuration.densityDpi = 300;
                        else
                            configuration.densityDpi = 320;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
                        configuration.densityDpi = 200;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
                        if (configuration.densityDpi >= 180)
                            configuration.densityDpi = 200;
                        else
                            configuration.densityDpi = 160;
                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
                        configuration.densityDpi = 160;
                    }

                    break;
                case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                    if (densityDpi >= DisplayMetrics.DENSITY_XXXHIGH) {
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XXHIGH) {
                    } else if (densityDpi >= DisplayMetrics.DENSITY_XHIGH) {
                        if (configuration.densityDpi >= 320) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else
                                configuration.densityDpi = 320;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_HIGH) {
                        if (configuration.densityDpi >= 272) {
                            if (widthPixels >= 2050)
                                configuration.densityDpi = 400;
                            else
                                configuration.densityDpi = 320;
                        }
                    } else if (densityDpi >= DisplayMetrics.DENSITY_TV) {
                        // TODO: Handle TV size if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_MEDIUM) {
                        // TODO: Handle density medium  if need
                    } else if (densityDpi >= DisplayMetrics.DENSITY_LOW) {
                        configuration.densityDpi = 240;
                    }
                    break;

                default:
                    break;
            }

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            float systemScale = Settings.System.getFloat(getApplicationContext().getContentResolver(), Settings.System.FONT_SCALE, 1f);
            if (systemScale == 0.85f) {
                configuration.fontScale = 0.95f;
            } else if (systemScale == 1f) {
                configuration.fontScale = 1f;
            } else if (systemScale == 1.15f) {
                configuration.fontScale = 1.05f;
            } else if (systemScale == 1.3f) {
                configuration.fontScale = 1.1f;
            }
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            if (display != null) {
                display.getMetrics(metrics);
            }
            this.getResources().updateConfiguration(configuration, null);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adjustDisplayScale();
        setContentView(initLayoutRes());
        initView();
        initData();
        initAction();
    }

    protected void addFragment(Fragment fragment, int resID) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
            fragmentTransaction.add(resID, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected void replaceFragment(Fragment fragment, int resID) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
            fragmentTransaction.replace(resID, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }


    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }


    @Override
    public void setTheme(int resId) {
        super.setTheme(resId);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
