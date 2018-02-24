package com.example.wautel_l.rss_reader_android;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import layout.ajout_url;
import layout.list_favoris;
import layout.list_item;
import layout.list_url;

/**
 * Created by wautel_l on 22/02/2018.
 */

public class Activity_all extends AppCompatActivity implements ajout_url.OnFragmentInteractionListener, list_url.OnFragmentInteractionListener, list_item.OnFragmentInteractionListener, list_favoris.OnFragmentInteractionListener{
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private String ip;
    private Integer id_client;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_all);



        Bundle extras = getIntent().getExtras();
        ip = extras.getString("ip");
        id_client  = extras.getInt("id_client");

        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass =  list_item.class;


        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putString("ip", ip);
        bundle.putInt("id_client", id_client);
        fragment.setArguments(bundle);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flContent, fragment).commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                }
        );
    }

    public void selectDrawerItem(MenuItem menuItem)
    {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId())
        {
            case R.id.add_url:
                fragmentClass = ajout_url.class;
                break;
            case R.id.list_url:
                fragmentClass = list_url.class;
                break;
            case R.id.list_article:
                fragmentClass = list_item.class;
                break;
            case R.id.favoris_article:
                fragmentClass = list_favoris.class;
                break;
            default:
                fragmentClass = list_url.class;
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putString("ip", ip);
        bundle.putInt("id_client", id_client);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);

        setTitle(menuItem.getTitle());

        mDrawer.closeDrawers();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String uri) {
        System.out.println(uri);
    }
}
