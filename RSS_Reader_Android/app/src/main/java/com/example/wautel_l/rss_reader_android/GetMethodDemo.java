package com.example.wautel_l.rss_reader_android;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by wautel_l on 24/02/2018.
 */
public class GetMethodDemo extends AsyncTask<String , Void ,String> {
    String server_response;
    String url;
    String ip;
    JSONObject arg;
    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip =  ip + ":8080";
    }

    public void seturl(String uri)
    {
        this.url = uri;
    }

    public void setArg(JSONObject a)
    {
        this.arg = a;
    }

    public String getUrl()
    {
        return this.url;
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;

/*        try{
            if (!InetAddress.getByName(this.ip).isReachable(30))
                return "network error";
        } catch (UnknownHostException e)
        {
            return "network error";
        } catch(IOException e)
        {
            return "network error";
        }*/

        try {
            Log.e("test", "tes");
            url = new URL(getUrl());
            Log.e("url", url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("charset", "ISO-8859-1");
            urlConnection.setRequestProperty("accept", "text/html");


            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            Log.e("code", Integer.toString(responseCode));

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
                Log.e("CatalogClient", server_response);
            }

        } catch (MalformedURLException exc) {
            Log.e("exc","toto", exc);
            exc.printStackTrace();
        } catch (IOException exc) {
            Log.e("exc", "toto", exc);
            exc.printStackTrace();
        }

        return server_response;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);


    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
