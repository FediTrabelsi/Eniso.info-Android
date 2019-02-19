package fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

import static fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section.SerialQueue.getSerialRequestQueue;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFrag extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spineeradd;
    AdapterProp mAdapter;
    private ArrayList<GroupModel> list ;
    private RequestQueue mQueue;
    private RecyclerView rv;
    private TextView tv;


    public AddFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        mQueue= getSerialRequestQueue(getActivity().getApplicationContext());

        rv= (RecyclerView) v.findViewById(R.id.rv2);
        spineeradd=(Spinner) v.findViewById(R.id.spinneradd);
        spineeradd.setSelection(Integer.parseInt(Login.login));
        if(Login.usertype.equals("Etudiants")){
            spineeradd.setEnabled(false);
        }
        spineeradd.setOnItemSelectedListener(this);


        getProposedProjects(Login.password);
        return v;
    }


    public void getProposedProjects(String session){
        list= new ArrayList<>();
        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27apbl%27).findProjectNodes("+session+"))";

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray alldata = response.getJSONArray("$1");
                            for (int i=0;i<alldata.length();i++) {

                                JSONObject project = alldata.getJSONObject(i);
                                JSONObject proj= project.getJSONObject("project");
                                JSONObject title=proj.getJSONObject("session");

                                String allowed= title.getString("memberProfiles");





                                    String tvtitle= title.getString("name");




                                    String code = "["+proj.getString("code")+"] ";
                                    String name= proj.getString("name");
                                    JSONObject owner=proj.getJSONObject("owner");
                                    String responsable=owner.getString("fullTitle");
                                    String description=proj.getString("description");



                                list.add(new GroupModel(code+name,"Responsable: "+responsable,"Descruption :","\n"+description+"\n","a","b"));
                                mAdapter = new AdapterProp(getContext(), list);
                                rv.setAdapter(mAdapter);


                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                rv.setLayoutManager(layoutManager);


                                mAdapter.notifyDataSetChanged();






                            }










                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                //String m = res1.getString("message");




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
            case "Projets proposés pour 1ères": {getProposedProjects("13");break;}
            case "Projets proposés pour 2émes": {getProposedProjects("14");break;}
            case "Projets proposés pour 3émes": {getProposedProjects("15");break;}
            default:Toast.makeText(parent.getContext(), "Default" , Toast.LENGTH_LONG).show();
        }
        Toast.makeText(parent.getContext(), "Chargement des " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
