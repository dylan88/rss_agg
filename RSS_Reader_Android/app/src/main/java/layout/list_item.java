package layout;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wautel_l.rss_reader_android.LocalService;
import com.example.wautel_l.rss_reader_android.R;
import com.example.wautel_l.rss_reader_android.Detail_item;
import com.example.wautel_l.rss_reader_android.obj.Item;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_item.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_item#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_item extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
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

    public list_item() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment list_item.
     */
    // TODO: Rename and change types and number of parameters
    public static list_item newInstance(String param1, String param2) {
        list_item fragment = new list_item();
        return fragment;
    }

    public void store_article()
    {
        article_list = new ArrayList<>();
        art_string_list = new ArrayList<>();
        ip = this.getArguments().getString("ip");
        id_client = this.getArguments().getInt("id_client");
        try {
            mService.connect(ip, "5000");
            String url_tmp = mService.do_action("articlelist_" + id_client);
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_url, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
        intent = new Intent(getActivity(), LocalService.class);
        getActivity().getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                store_article();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, art_string_list);
                lv.setAdapter(adapter);
            }
        }, 1000);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item itemtmp = article_list.get(i);
                Intent intent = new Intent(view.getContext(), Detail_item.class);
                intent.putExtra("ip", ip);
                intent.putExtra("title", itemtmp.getTitle());
                intent.putExtra("description", itemtmp.getDescription());
                intent.putExtra("url", itemtmp.getLink());
                startActivity(intent);
            }
        });
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event


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
