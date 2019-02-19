package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import java.util.Map;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class ProjFragTeacher extends Fragment {
    Button mise;
    RequestQueue mQueue;
    Context contex;
    AdapterTeacher Adapt;
    RecyclerView rv;
    private ArrayList<TeacherModel> list_proj;

    public ProjFragTeacher(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
       View v = inflater.inflate(R.layout.frag_proj_teacher,container,false);
        rv= v.findViewById(R.id.rvproj);
        contex= getActivity().getApplicationContext();
        mQueue= Volley.newRequestQueue(contex);
        mise= v.findViewById(R.id.majicontact);

        getExpTeacher();

        return v;
    }

    private void getExpTeacher() {
        list_proj=new ArrayList<>();
        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27academicProfile%27).findTeacherCvItemsBySection("+ActivityTeacher.idteacher+",3))";

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String title ,details,key,etab;

                            JSONArray all = response.getJSONArray("$1");
                            for (int i=0;i<all.length();i++){
                                JSONObject var =all.getJSONObject(i);
                                if(var.has("title")){
                                    title=var.getString("title");
                                }
                                else{
                                    title="??????";
                                }


                                if(var.has("company")) {
                                    JSONObject company = var.getJSONObject("company");
                                    if (company.has("name")) {
                                        etab = company.getString("name");

                                    } else {
                                        etab = "??????";
                                    }
                                }
                                else{
                                    etab="non spécifié";
                                }

                                if(var.has("details")){
                                    details=var.getString("details");
                                }
                                else {
                                    details="??????";
                                }
                                if(var.has("keywords")){
                                    key=var.getString("keywords");
                                }
                                else {
                                    key="??????";
                                }
                                list_proj.add(new TeacherModel("Titre",title,"Etablissement",etab,"Détails",details,"Mots_Clés",key));

                            }












                            Adapt=new AdapterTeacher(contex,list_proj);
                            rv.setAdapter(Adapt);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contex);
                            rv.setLayoutManager(layoutManager);
                            Adapt.notifyDataSetChanged();


                        } catch (JSONException e){
                            try {

                                JSONObject res = response.getJSONObject("$error");
                                String m = res.getString("message");



                            } catch (JSONException e1){


                            }

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {// TODO: Handle error
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie", Login.sessionId);


                return params;
            }
        };
        mQueue.add(req);

    }
}
