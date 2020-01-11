package com.wallpad.net.socket;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.Transport;
import com.github.nkzawa.engineio.client.transports.Polling;
import com.github.nkzawa.engineio.client.transports.WebSocket;
import com.github.nkzawa.engineio.parser.Packet;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Manager;
import com.github.nkzawa.socketio.client.Socket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.socketio.Acknowledge;
import com.koushikdutta.async.http.socketio.StringCallback;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by Duong
 * Socket connect to protocol
 * using service to keep socket in background
 */

public class SocketClient extends Service {
    IBinder mBinder;
    Socket socket;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        LogUtils.e("OnBind");
        initSocket();
        return mBinder;
    }

    public interface OnListenResponse {
        void onResponse(String str);
    }

    public class LocalBinder extends Binder {
        public SocketClient getServerInstance() {
            return SocketClient.this;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("OnDestroy");
        if (socket != null)
            this.socket.disconnect();

        Intent broadcastIntent = new Intent("socket.restart");
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        LogUtils.e("OnCreate");
        mBinder = new LocalBinder();
        initSocket();
    }


    public void initSocket() {

//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(SocketConstants.HOST_SOCKET).build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        okhttp3.WebSocket ws = client.newWebSocket(request, listener);
//
//        client.dispatcher().executorService().shutdown();


        try {
            IO.Options os = new IO.Options();

            os.transports = new String[]{WebSocket.NAME, Polling.NAME};
            socket = IO.socket(SocketConstants.HOST_SOCKET);
            //socket.connect();
            socket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Transport transport = (Transport) args[0];
                    transport.on(Transport.EVENT_REQUEST_HEADERS, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Map<String, List<String>> headers = (Map<String, List<String>>) args[0];
                            headers.put("Message Start", Arrays.asList("0xA000"));
                            headers.put("Version", Arrays.asList("0x01"));
                            headers.put("Flag", Arrays.asList("0x00"));
                            headers.put("Lengt", Arrays.asList("1024"));
                            headers.put("Message ID", Arrays.asList("0x00000001"));
                            headers.put("Sequence Number", Arrays.asList("0x00000001"));
                            headers.put("Source ID", Arrays.asList("0x303030313132333435363738"));
                            headers.put("Destination ID", Arrays.asList("0x303030313132333435363738"));
                            headers.put("OP Code", Arrays.asList("0x2111"));
                            headers.put("Transaction ID", Arrays.asList("0x323031392d31322d33305431323a33343a353600"));
                            headers.put("Optional Header", Arrays.asList("0x00"));
                            headers.put("CRC", Arrays.asList("0x00"));
                            headers.put("Message end", Arrays.asList("0x000A"));
                        }
                    });
                    transport.on(Transport.EVENT_RESPONSE_HEADERS, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            @SuppressWarnings("unchecked")
                            Map<String, List<String>> headers = (Map<String, List<String>>) args[0];
                            // access response headers
                            String cookie = headers.get("Set-Cookie").get(0);
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtils.e("Socket Problem", "Try cath", e);
        }

        this.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                LogUtils.e("Connected");
                final Intent intent = new Intent();
                intent.setAction(SocketConstants.EVENT_CONNECTION);
                intent.putExtra(SocketConstants.KEY_STATUS_CONNECTION, "Connected");
                SocketClient.this.sendBroadcast(intent);


            }
        });

        this.socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            public void call(Object... args) {
                Log.i("desc", "error desc");
                Intent intent = new Intent();
                intent.setAction(SocketConstants.EVENT_CONNECTION);
                intent.putExtra(SocketConstants.KEY_STATUS_CONNECTION, "Disconnected");
                SocketClient.this.sendBroadcast(intent);
            }
        });
        this.socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            public void call(Object... args) {
                LogUtils.e("Error", args[0].toString());
                Intent intent = new Intent();
                intent.setAction(SocketConstants.EVENT_CONNECTION);
                intent.putExtra(SocketConstants.KEY_STATUS_CONNECTION, "Connect error");
                // SocketClient.this.sendBroadcast(intent);
            }
        });
        this.socket.on(Packet.ERROR, new Emitter.Listener() {
            public void call(Object... args) {
                LogUtils.e("Error", "Event Error");
                LogUtils.e(args[0].toString());
            }
        });
        this.socket.on(Socket.EVENT_RECONNECTING, new Emitter.Listener() {
            public void call(Object... args) {
                Intent intent = new Intent();
                intent.setAction(SocketConstants.EVENT_CONNECTION);
                intent.putExtra(SocketConstants.KEY_STATUS_CONNECTION, "Reconnecting...");
                SocketClient.this.sendBroadcast(intent);
                LogUtils.e(" reconecting ");
            }
        });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        //create a intent that you want to start again..
        Intent intent = new Intent(getApplicationContext(), SocketClient.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 5000, pendingIntent);
        super.onTaskRemoved(rootIntent);
    }


}

