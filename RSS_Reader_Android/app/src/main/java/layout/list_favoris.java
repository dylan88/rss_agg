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
import android.widget.TextView;

import com.example.wautel_l.rss_reader_android.GetMethodDemo;
import com.example.wautel_l.rss_reader_android.LocalService;
import com.example.wautel_l.rss_reader_android.R;
import com.example.wautel_l.rss_reader_android.obj.Item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_favoris.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_favoris#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_favoris extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String ip;
    private int id_client;
    private View view;
    private ArrayList<Item> article_list;
    private ArrayList<String>  art_string_list;
    private LocalService mService;
    private ListView lv;
    private Intent intent;
    private boolean mBound = false;

    private OnFragmentInteractionListener mListener;

    public list_favoris() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment list_favoris.
     */
    // TODO: Rename and change types and number of parameters
    public static list_favoris newInstance(String param1, String param2) {
        list_favoris fragment = new list_favoris();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_favoris, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
   /*     intent = new Intent(getActivity(), LocalService.class);
        getActivity().getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {*/
                store_article();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, art_string_list);
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

    public void store_article()
    {
        article_list = new ArrayList<>();
        art_string_list = new ArrayList<>();
        ip = this.getArguments().getString("ip");
        id_client = this.getArguments().getInt("id_client");
        try {
                GetMethodDemo getMethodDemo = new GetMethodDemo();
                getMethodDemo.setContext(this.getContext());
                getMethodDemo.setIp(ip);
                getMethodDemo.seturl(ip + "/favoris?user_id="+id_client);
                String url_tmp = getMethodDemo.execute().get();
           // String url_tmp = mService.do_action("favlist_" + id_client);
            JSONArray listArray = new JSONArray(url_tmp);

            JSONObject oneObject;
            int i;
            for (i = 0; i < listArray.length(); i++) {
                oneObject = new JSONObject(listArray.getString(i));
                article_list.add(new Item(oneObject.getInt("item_id"), oneObject.getInt("feed_id"), oneObject.getString("title"), oneObject.getString("link"), oneObject.getInt("guid"), oneObject.getString("description"), oneObject.getInt("categorie_id"), oneObject.getInt("read")));
                art_string_list.add(oneObject.getString("title"));
            }
        }
        catch(Exception e)
        {
            Log.e("error", e.toString());
            e.printStackTrace();
        }
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
