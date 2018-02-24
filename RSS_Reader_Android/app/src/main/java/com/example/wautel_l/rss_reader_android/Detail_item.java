package com.example.wautel_l.rss_reader_android;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wautel_l.rss_reader_android.LocalService;
import com.example.wautel_l.rss_reader_android.R;

/**
 * Created by wautel_l on 24/02/2018.
 */

public class Detail_item extends AppCompatActivity{
    private LocalService mService;
    private boolean mBound = false;
    private TextView title;
    private TextView desc;
    private String titletv;
    private String desctv;
    private String url;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            mBound = false;
        }
    };


    public void redirect()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);


        Bundle extras = getIntent().getExtras();
        titletv = extras.getString("title");
        desctv = extras.getString("description");
        url = extras.getString("url");

        title = (TextView) findViewById(R.id.tvtitle);
        desc = (TextView) findViewById(R.id.tvdesc);

        title.setText(titletv);
        desc.setText(desctv);
        Button mSee = (Button) findViewById(R.id.button_see);
        mSee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(url));
                startActivity(viewIntent);
            }
        });


    }
}
