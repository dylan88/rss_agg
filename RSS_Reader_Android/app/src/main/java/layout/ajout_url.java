package layout;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wautel_l.rss_reader_android.Activity_all;
import com.example.wautel_l.rss_reader_android.GetMethodDemo;
import com.example.wautel_l.rss_reader_android.LocalService;
import com.example.wautel_l.rss_reader_android.LoginActivity;
import com.example.wautel_l.rss_reader_android.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ajout_url.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ajout_url#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ajout_url extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String ip;
    private Integer id_client;
    private Button save;
    private LocalService mService;
    private View view;
    private EditText uri;
    private boolean mBound = false;

    private OnFragmentInteractionListener mListener;

    public ajout_url() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ajouter_url.
     */
    // TODO: Rename and change types and number of parameters
    public static ajout_url newInstance(String param1, String param2) {
        ajout_url fragment = new ajout_url();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ip = this.getArguments().getString("ip");
        id_client = this.getArguments().getInt("id_client");



        view = inflater.inflate(R.layout.fragment_ajouter_url, container, false);
        uri = (EditText) view.findViewById(R.id.uri);
        save = (Button) view.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(getActivity(), LocalService.class);
                getActivity().getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {*/
                        ajout_url();
                 /*   }
                }, 1000);*/

            }
        });
        return view;
    }

    public void ajout_url()
    {
     //   if (mBound) {
        try {
        GetMethodDemo getMethodDemo = new GetMethodDemo();
        getMethodDemo.setIp(ip);
        getMethodDemo.setContext(this.getContext());
        getMethodDemo.seturl(ip + "/add?user_id="+id_client+"&url="+uri.getText().toString());
        String response = getMethodDemo.execute().get();
            if (!response.equals("network error")) {
                //    mService.connect(ip, "5000");
                //   String response = mService.do_action("add_" + id_client + "_" + uri.getText().toString());
                // Log.e("response", response + "tr");
                if (response.contains("true")) {
                    Toast popup = Toast.makeText(view.getContext(), "Ajout effectué avec succés", Toast.LENGTH_LONG);
                    popup.show();
                } else {
                    Toast popup = Toast.makeText(view.getContext(), "Erreur durant le processus d'ajout", Toast.LENGTH_LONG);
                    popup.show();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {

        public void onFragmentInteraction(String uri);

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


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.e("er", "tr");
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
