package layout;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wautel_l.rss_reader_android.GetMethodDemo;
import com.example.wautel_l.rss_reader_android.LocalService;
import com.example.wautel_l.rss_reader_android.R;
import com.example.wautel_l.rss_reader_android.obj.Url;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_url.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_url#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_url extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> utl_name;
    private ArrayList<Url> url_list;
    private LocalService mService;
    private boolean mBound = false;

    // TODO: Rename and change types of parameters
    private String ip;
    private int id_client;
    private Intent intent;
    private View view;
    private ListView lv;

    private OnFragmentInteractionListener mListener;

    public list_url() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment list_url.
     */
    // TODO: Rename and change types and number of parameters
    public static list_url newInstance(String param1, String param2) {
        list_url fragment = new list_url();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ip = getArguments().getString("ip");
            id_client = getArguments().getInt("id_client");
        }

    }

    public void store_url()
    {
        url_list = new ArrayList<>();
        utl_name = new ArrayList<>();
        ip = this.getArguments().getString("ip");
        id_client = this.getArguments().getInt("id_client");
        try {
            GetMethodDemo getMethodDemo = new GetMethodDemo();
            getMethodDemo.setIp(ip);
            getMethodDemo.setContext(this.getContext());
            getMethodDemo.seturl(ip + "/feed/all?user_id="+id_client);
            String url_tmp = getMethodDemo.execute().get();
                    // mService.connect(ip, "5000");
           // String url_tmp = mService.do_action("urllist_" + id_client);
            if (!url_tmp.equals("network error")) {
                JSONArray listArray = new JSONArray(url_tmp);

                JSONObject oneObject;
                int i;
                for (i = 0; i < listArray.length(); i++) {
                    oneObject = new JSONObject(listArray.getString(i));
                    url_list.add(new Url(oneObject.getInt("feed_id"), oneObject.getString("url")));
                    utl_name.add(oneObject.getString("url"));
                }
            }
        }
        catch(Exception e)
            {
                Log.e("error", e.toString());
                e.printStackTrace();
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_url, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
    /*    intent = new Intent(getActivity(), LocalService.class);
        getActivity().getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                store_url();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, utl_name);
                lv.setAdapter(adapter);
         /*   }
        }, 1000);*/

        return  view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(String uri);

    }
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

}
