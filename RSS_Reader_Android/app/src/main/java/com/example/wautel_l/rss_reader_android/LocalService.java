package com.example.wautel_l.rss_reader_android;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;

import static java.lang.Thread.activeCount;
import static java.lang.Thread.sleep;

/**
 * Created by wautel_l on 20/06/2017.
 */

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    protected boolean isCo = false;
    protected Socket socket;
    public  String SERVER_IP = "192.168.1.20";
    public int SERVER_PORT = 9999;
    protected InetAddress serverAddr;
    private MyCo local_co = new MyCo();
    protected String response;
    protected PrintWriter out;
    protected BufferedReader in;
    private boolean test;
    protected Context context;

    public class LocalBinder extends Binder {
       public  LocalService getService()
        {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }


    public void connect(String ip, String port)
    {

        SERVER_IP = ip;
        SERVER_PORT = Integer.parseInt(port);
    }

    public void connect(String ip, String port, Context context)
    {
        SERVER_IP = ip;
        SERVER_PORT = Integer.parseInt(port);
        this.context = context;

    }

    public String do_action(String action, Boolean waiting_response)
    {
        response = null;
        new MyCo().execute(action);
        if (waiting_response)
        {
            while (response == null)
                ;
            return response;

        }
        return null;
    }

    public String do_action(String action)
    {
    //context = c;
        response = null;
        test = false;
          new MyCo().execute(action);
        while (response == null);
        Log.e("re", response);
        parse_display(action);
        return response;
    }


    private void parse_display(String cmd)
    {
        if (cmd.contains("login"))
        {
            if (response != null && !response.isEmpty() && !response.trim().isEmpty()) {
                LoginActivity login = (LoginActivity) context;
                login.access_next(response);
            }
        }
      /*  switch(cmd) {
            case "login":
                if (response != null && !response.isEmpty() && !response.trim().isEmpty()) {
                    LoginActivity login = (LoginActivity) context;
                        login.access_next(response);
                }
                break;
        }
            case "listMob":
                if (response != null && !response.isEmpty() && !response.trim().isEmpty()) {
                    Choix_mob mob = (Choix_mob) context;
                    if (response.equals("error")) {
                        isCo = false;
                        mob.display_error();
                    }
                }
                break;
            case "map":
                if (response != null && !response.isEmpty() && !response.trim().isEmpty())
                {
                    Map map = (Map) context;
                    if (response.equals("error")) {
                        isCo = false;
                        map.display_error();
                    }
                    else{
                        map.usecoord(response);
                    }
                }
                break;

        }
        if (cmd.contains("mobup_move") ||cmd.contains("mobup_camera"))
        {
                if (response != null && !response.isEmpty() && !response.trim().isEmpty()) {
                    stream_page stream = (stream_page) context;
                    if (response.equals("error")) {
                        isCo = false;
                        stream.display_error();
                    }
                }
        }
        else if (cmd.contains("item_"))
        {
            if (response != null && !response.isEmpty() && !response.trim().isEmpty()) {
                item_page item = (item_page) context;
                if (response.equals("error")) {
                    isCo = false;
                    item.display_error();
                }
            }
        }*/
    }

    class MyCo extends AsyncTask<String, Void, Void>{
        String cmd;
        @Override
        protected Void doInBackground(String... params) {
            try {

                    if (!isCo) {
                         serverAddr = InetAddress.getByName(SERVER_IP);
                        socket = new Socket(serverAddr, SERVER_PORT);
                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        isCo = true;
                        socket.setSoTimeout(5000);
                    }

                    Log.e("socket", socket.toString());
                    cmd = params[0];
                     out.println(params[0]);
                    while (!in.ready()) ;
                    response = readBuffer();
                    Log.e("response", response);


                }
                catch (Exception ec)
                {
                    Log.e("exce", ec.toString());
                    ec.printStackTrace();
                    try {
                        socket.close();
                    }
                    catch (Exception exc)
                    {
                        exc.printStackTrace();
                    }
                    response = "error";
                }



            return null;
        }



        private String readBuffer() throws IOException
        {
            String data = "";
            while (in.ready())
            {
                data = data + (char) in.read();
            }
            if (data.indexOf("SNX_COM> ") != -1) return data.substring(0, data.indexOf("SNX_COM> "));
            else return data;
        }
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }



}
