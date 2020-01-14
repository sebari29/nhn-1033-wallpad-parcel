package com.wallpad.delivery;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.LogUtils;
import com.wallpad.net.socket.SocketClient;
import com.wallpad.net.socket.TcpClient;
import com.wallpad.basemvvm.view.BaseActivity;
import com.wallpad.delivery.view.delivery.DeliveryFragment;

public class MainActivity extends BaseActivity {
    private TcpClient mTcpClient;
    protected SocketClient mSocket;
    private boolean mBounded;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBounded = true;
            SocketClient.LocalBinder mLocalBinder = (SocketClient.LocalBinder) service;
            mSocket = mLocalBinder.getServerInstance();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBounded = false;
            mSocket = null;
        }
    };

    public SocketClient getmSocket() {
        return mSocket;
    }

    @Override
    protected void adjustDisplayScale() {
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
    }

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
        );
        fragmentTransaction.replace(R.id.main_frame_root, DeliveryFragment.newInstance());
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        new ConnectTask().execute("");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new SendMessageTask().execute("a0000101000002c40000000100000001303030303031303930313035303031313037303030303030303030303030303030303166303230302111323031362d30312d30315430303a30303a303800000022d300000000000000000000000000000000000a<HNML><ControlRequest TransID=\"WP20160101000008070\"><FunctionID>11070001</FunctionID><FunctionCategory>Control</FunctionCategory><InputList size=\"1\"><Input size=\"10\" name=\"LoginInfo\"><Data name=\"IPAddress\">10.9.1.3</Data><Data name=\"PhysicalAddress\">F0:15:A0:01:38:6E</Data><Data name=\"Port\">8800</Data><Data name=\"SoftwareType\">HN</Data><Data name=\"SoftwareVersion\">1.0</Data><Data name=\"UpgradeStatus\">SUCCESS</Data><Data name=\"ModelName\">UHN-1010VE</Data><Data name=\"UserPassword\"></Data><Data name=\"LobbyPassword\">0000</Data><Data name=\"RemotePassword\"></Data></Input></InputList></ControlRequest></HNML>");
            }
        }, 3000);
    }

    private void start() {

    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, SocketClient.class);
        startService(mIntent);
        bindService(mIntent, this.mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mBounded) {
            unbindService(this.mConnection);
            this.mBounded = false;
        }
        new DisconnectTask().execute();
    }


    /**
     * Sends a message using a background task to avoid doing long/network operations on the UI thread
     */
    public class SendMessageTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            // send the message
            mTcpClient.sendMessage(params[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void nothing) {
            super.onPostExecute(nothing);
            // clear the data set

        }
    }

    /**
     * Disconnects using a background task to avoid doing long/network operations on the UI thread
     */
    public class DisconnectTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            // disconnect
            mTcpClient.stopClient();
            mTcpClient = null;

            return null;
        }

        @Override
        protected void onPostExecute(Void nothing) {
            super.onPostExecute(nothing);
            // clear the data set

        }
    }

    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object and
            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //in the arrayList we add the messaged received from server
            LogUtils.e(values[0]);
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
        }
    }
}
