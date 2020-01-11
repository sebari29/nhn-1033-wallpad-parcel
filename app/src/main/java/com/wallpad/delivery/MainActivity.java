package com.wallpad.delivery;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;

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
