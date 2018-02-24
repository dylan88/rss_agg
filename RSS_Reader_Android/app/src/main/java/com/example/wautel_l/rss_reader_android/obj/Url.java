package com.example.wautel_l.rss_reader_android.obj;

/**
 * Created by wautel_l on 24/02/2018.
 */

public class Url {
    private int id;
    private String name;

    public Url(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setId(int id_tmp)
    {
        this.id = id_tmp;
    }

    public void setName(String name_tmp)
    {
        this.name = name_tmp;
    }

}
