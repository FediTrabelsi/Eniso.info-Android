package fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

import static android.widget.Toast.LENGTH_LONG;
import static fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section.SerialQueue.getSerialRequestQueue;
import static java.lang.Thread.sleep;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFrag extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Adapter mAdapter;
    RecyclerView rv;
    TextView tv;
    RequestQueue mQueue;
    Bundle bundle;
    private ArrayList<GroupModel> list ;
    private static ArrayList<String> idds= new ArrayList<String>();

    private static ArrayList<String> tms= new ArrayList<String>();


    public ViewFrag() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_view, container, false);
       // mQueue = Volley.newRequestQueue( getActivity().getApplicationContext());
        mQueue= getSerialRequestQueue(getActivity().getApplicationContext());


        rv= (RecyclerView) v.findViewById(R.id.rv);
        spinner=(Spinner) v.findViewById(R.id.spinner);
        spinner.setSelection(Integer.parseInt(Login.login));
        if(Login.usertype.equals("Etudiants")){
            spinner.setEnabled(false);
        }
        spinner.setOnItemSelectedListener(this);





        getteams(Login.password);








        return v;
    }





    public  void getteams(String session){

        list= new ArrayList<>();
        String url2="http://eniso.info/ws/core/wscript?s=Return(bean(%27apbl%27).findTeamsBySession("+session+"))";


            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url2, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {


                              JSONArray i = response.getJSONArray("$1");

                              for(int x=0;x<i.length();x++){

                                  JSONObject res =  i.getJSONObject(x);
                                  String ind= (res.getInt("id")+"");
                                  String gpname="[T"+ind+"] "+res.getString("name");
                                  idds.add(ind);
                                  tms.add(gpname);
                                  JSONObject session= res.getJSONObject("session");
                                  String title = session.getString("name");
                                 // tv.setText(title);
                                  JSONObject owner=res.optJSONObject("owner");
                                  String obj=owner.getString("fullTitle");
                                  String desc= res.getString("description");



                                  getcoachs(gpname,ind,x,i.length(),obj,desc);


                                //  sleep(20);



                              }







                            } catch (JSONException e) {
                                try {
                                    JSONObject res1 = response.getJSONObject("$error");
                                    String m = res1.getString("type");
                                  //  tv.setText("erreur");
                                    Toast.makeText(getContext(),m,Toast.LENGTH_SHORT).show();
                                } catch (JSONException a) {

                                }
                            }                         }

                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(),"no res",Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Cookie", Login.sessionId);


                    return headers;
                }

                ;





            };


            mQueue.add(req);




    }

    public  void getcoachs(final String gpname,final String stringid, final int pos,final int max,final String obj,final String desc){




            String url2 = "http://eniso.info/ws/core/wscript?s=Return(bean(%27apbl%27).findTeamCoaches("+stringid+"))";



            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url2, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                //
                                JSONArray i = response.getJSONArray("$1");

                                JSONObject res = i.getJSONObject(0);
                                JSONObject teacher = res.getJSONObject("teacher");
                                JSONObject user = teacher.getJSONObject("user");
                                String nom= user.getString("fullTitle");
                                getmembers(gpname,stringid,nom,pos,max,obj,desc);










                            } catch (JSONException e) {
                                try {
                                    JSONObject res1 = response.getJSONObject("$error");
                                    String m = res1.getString("message");

                                } catch (JSONException a) {

                                }
                            }
                        }

                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // handle error


                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Cookie", Login.sessionId);


                    return headers;
                }

                ;




            };


            mQueue.add(req);

        }


    public  void getmembers(final String gp ,String stringid, final String stringcoach, final int pos,final int max,final String obj,final String desc){




        String url2 = "http://eniso.info/ws/core/wscript?s=Return(bean(%27apbl%27).findTeamMemberStudents("+stringid+"))";



        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //
                            String name="\n";
                            JSONArray i = response.getJSONArray("$1");

                            for(int m=0;m<i.length();m++){
                                JSONObject student=i.getJSONObject(m);
                                JSONObject userinfo=student.getJSONObject("user");
                                name=name+userinfo.getString("fullTitle")+"\n";
                            }
                            list.add(new GroupModel(gp,"Coach: "+stringcoach,"Members",name,obj,desc));

                            mAdapter = new Adapter(getContext(), list);
                            rv.setAdapter(mAdapter);



                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                rv.setLayoutManager(layoutManager);
                                mAdapter.notifyDataSetChanged();









                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                String m = res1.getString("message");

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

            ;




        };


        mQueue.add(req);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

        // Showing selected spinner item
        switch(item){
            case "Projets Innovation 2018-2019-S1-1ères": {
                list=new ArrayList<>();
                getteams("13");
                Toast.makeText(parent.getContext(), "Chargement des " + item, Toast.LENGTH_LONG).show();
                break;}
            case "Projets Innovation 2018-2019-S1-2émes": {
                list=new ArrayList<>();
                getteams("14");
                Toast.makeText(parent.getContext(), "Chargement des " + item, Toast.LENGTH_LONG).show();
                break;}
            case "Projets Innovation 2018-2019-S1-3émes": {
                list=new ArrayList<>();
                getteams("15");
                Toast.makeText(parent.getContext(), "Chargement des " + item, Toast.LENGTH_LONG).show();
                break;}
            default:Toast.makeText(parent.getContext(), "Default" , Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}




