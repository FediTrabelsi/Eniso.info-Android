package fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Press extends Fragment {
    public RequestQueue mQueue;
    private RecyclerView rv;
    private ArrayList<PressModel> mlist;
    private PressAdapter mAdapter;
    private Context mcontext;


    public Press() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_press, container, false);
        mcontext=getActivity().getApplicationContext();
        mQueue= Volley.newRequestQueue(mcontext);
        rv=view.findViewById(R.id.recyclepres);

        getPress();

        return view;
    }

    public void getPress() {
        final String url1 = "http://eniso.info/ws/core/wscript?s=Return(bean(%27core%27).findFullArticlesByDisposition(1,true,%22News%22))";
        mlist = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, url1, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray res = response.getJSONArray("$1");



                            for (int i = 0; i < res.length(); i++) {
                                JSONObject obj = res.getJSONObject(i);
                                JSONObject article = obj.getJSONObject("article");

                                String body = article.getString("content");
                                body= Html.fromHtml(body).toString();

                                String sujet = article.getString("subject");
                                sujet=Html.fromHtml(sujet).toString();



                                JSONObject sender = article.getJSONObject("sender");

                                String name = sender.getString("fullName");
                                name=Html.fromHtml(name).toString();

                                String time = article.getString("sendTime");
                                int marker=time.indexOf(":");
                                time=time.substring(0,marker-2);
                                time=Html.fromHtml(time).toString();




                                mlist.add(new PressModel(sujet,body,"\n\nécrit par : " + name + " -(le " + time + ")"));


                                mAdapter = new PressAdapter(mcontext, mlist);
                                rv.setAdapter(mAdapter);




                            }
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mcontext);
                            rv.setLayoutManager(layoutManager);
                            mAdapter.notifyDataSetChanged();

                            mAdapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                //  String m = res1.getString("message");


                            } catch (JSONException a) {

                            }
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Cookie", Login.sessionId);


                return headers;

            }
        };



        mQueue.add(request);
    }

}
