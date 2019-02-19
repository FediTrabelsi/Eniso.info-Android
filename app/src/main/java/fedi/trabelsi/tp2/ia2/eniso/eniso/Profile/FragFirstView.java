package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Active;
import fedi.trabelsi.tp2.ia2.eniso.eniso.ActiveListAdapter;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragFirstView extends Fragment {
    public RequestQueue mQueue;
    private Context context;
    private ListView activeNowList;
    public ActiveAdapter mAdapter;
    public RecyclerView rv;
    private ActiveListAdapter adapter1;
    TextView tv;
    public ArrayList<Active> mlist;


    public FragFirstView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_first_view, container, false);
       // activeNowList = (ListView) v.findViewById(R.id.active_list);
        rv= v.findViewById(R.id.active_list);
        context=getActivity().getApplicationContext();
        mQueue = Volley.newRequestQueue(context);
        tv= v.findViewById(R.id.textView7);
   /*     ImageView profimage= v.findViewById(R.id.pb);
        Picasso.with(context).load(Login.iconurl).error(R.drawable.background).into(profimage);*/
        getdata();
        return v;
    }
    public  void getdata(){
        String url="http://eniso.info/ws/core/wscript?s=Return(bean('core').getPluginsAPI())";
        String url2="http://eniso.info/ws/core/wscript?s=Return(bean(%22core%22).getActiveSessions(true,true,false))";
        /*final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();*/
        mlist= new ArrayList<>();



        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET,url2,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                         /*   Intent i = new Intent(getApplicationContext(),Groupes.class);
                            startActivity(i);*/
                            // JSONObject res =  response.getJSONObject("$1");
                            ArrayList<Active> ActiveList = new ArrayList();
                            JSONArray i = response.getJSONArray("$1");

                            JSONObject s1 ;
                            String  userFullTitle;
                            String iconURL ;
                            String userTypeName ;
                            for (int j = 0; j <i.length() ; j++) {
                                s1 = i.getJSONObject(j);
                                if(s1.has("userTypeName")) {
                                    userTypeName = s1.getString("userTypeName");

                                }
                                else{
                                    userTypeName = "";
                                }
                                iconURL = "http://eniso.info/fs"+s1.getString("iconURL");
                                userFullTitle = s1.getString("userFullTitle");

                                ActiveList.add(new Active(userFullTitle,iconURL,userTypeName));
                                mlist.add(new Active(userFullTitle,iconURL,userTypeName));
                              //  adapter1 = new ActiveListAdapter(context,R.layout.activity_active_now_adapter,ActiveList);
                                mAdapter=new ActiveAdapter(context,mlist);
                                rv.setAdapter(mAdapter);

                            }


                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                            rv.setLayoutManager(layoutManager);
                            mAdapter.notifyDataSetChanged();








                           /* activeNowList.setAdapter(adapter1);
                            adapter1.notifyDataSetChanged();*/




                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                String m = res1.getString("message");
                            } catch (JSONException a) {
                                /*e.printStackTrace();
                                progressDialog.dismiss();*/
                            }
                        }
                        /*adapter1.notifyDataSetChanged();
                        progressDialog.dismiss();*/
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error

            }
        })
        {



            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie",  Login.sessionId);
                return params;
            }



        };



        mQueue.add(req);


    }
}
