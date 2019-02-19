package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
public class FormationFrag extends Fragment {
    Button mise ;
    AdapterContact contact_adapter,contact,contact1;
    RecyclerView rv,rv1,rv2;
    Context Context_contact;
    public TextView t1,t2,t3;
    private RequestQueue mQueue;
    private ArrayList<IdentityModel> list,list1,list2;

    public FormationFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_formation, container, false);
        Context_contact = getActivity().getApplicationContext();
        mQueue= Volley.newRequestQueue(Context_contact);
        rv= v.findViewById(R.id.ing);
        rv2= v.findViewById(R.id.bac);
        rv1= v.findViewById(R.id.prep);
        t1 = v.findViewById(R.id.textView);
        t2= v.findViewById(R.id.textView2);
        t3= v.findViewById(R.id.textView3);
        t1.setPaintFlags(t1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        t2.setPaintFlags(t2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        t3.setPaintFlags(t3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        getFormationData();


        // Inflate the layout for this fragment
        return v;
    }


    public void getFormationData(){


        list=new ArrayList<>();
        list2=new ArrayList<>();
        list1=new ArrayList<>();

        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27academicProfile%27).getCurrentStudentCV())";

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String nameLast,choix1,choix2,choix3,namePrepa ,namefiliere,typebac,gouvbac, rang_affecté, moyenne,rang,rangMax,rangMaj,rangAff,moybac;

                            JSONObject alldonné = response.getJSONObject("$1");
                            JSONObject student = alldonné.getJSONObject("student");
                            JSONObject lastClass1= student.getJSONObject("lastClass1");


                            if(lastClass1.has("name")){
                                nameLast = lastClass1.getString("name");
                            }
                            else{
                                nameLast="??????";
                            }

                            if(student.has("preClassChoice")){
                                rang_affecté =student.getString("preClassChoice");
                            }
                            else {
                                rang_affecté="??????";
                            }
                            if(student.has("preClassChoice1Other")){
                                choix1 =student.getString("preClassChoice1Other");
                            }
                            else {
                                choix1="??????";
                            }
                            if(student.has("preClassChoice2Other")){
                                choix2 =student.getString("preClassChoice2Other");
                            }
                            else {
                                choix2="??????";
                            }
                            if(student.has("preClassChoice3Other")){
                                choix3 =student.getString("preClassChoice3Other");
                            }
                            else {
                                choix3="??????";
                            }
                           JSONObject prepa= student.getJSONObject("preClass");
                            if(prepa.has("name")){
                                namePrepa=prepa.getString("name");
                            }
                            else {
                                namePrepa="??????";
                            }
                            JSONObject filiere= student.getJSONObject("preClassType");
                            if(filiere.has("name")){
                                namefiliere=prepa.getString("name");
                            }
                            else {
                                namefiliere="??????";
                            }
                            if(student.has("preClassScore")){
                                moyenne=student.getString("preClassScore");
                            }
                            else {
                                moyenne="??????";
                            }
                            if(student.has("preClassRank")){
                                rang=student.getString("preClassRank");
                            }
                            else {
                                rang="??????";
                            }
                            if(student.has("preClassRankMax")){
                                rangMax=student.getString("preClassRankMax");
                            }
                            else {
                                rangMax="??????";
                            }
                            if(student.has("preClassRank2")){
                                rangMaj=student.getString("preClassRank2");
                            }
                            else {
                                rangMaj="??????";
                            }
                            if(student.has("preClassRankByProgram")){
                                rangAff=student.getString("preClassRankByProgram");
                            }
                            else {
                                rangAff="??????";
                            }

                            if (student.has("baccalaureateClass")) {

                                JSONObject bac = student.getJSONObject("baccalaureateClass");
                                if (bac.has("name")) {
                                    typebac = bac.getString("name");
                                } else {
                                    typebac = "??????";
                                }
                            }

                            else{
                                typebac="??????";

                            }
                            if (student.has("baccalaureateScore")) {
                                moybac = student.getString("baccalaureateScore");
                            } else {
                                moybac = "??????";
                            }

                            if(student.has("baccalaureateGovernorate")) {
                                JSONObject bacGouv = student.getJSONObject("baccalaureateGovernorate");
                                if (bacGouv.has("name")) {
                                    gouvbac = bacGouv.getString("name");
                                } else {
                                    gouvbac = "??????";
                                }
                            }
                            else{
                                gouvbac="??????";

                            }






                            list.add(new IdentityModel( "classe_ing",nameLast));
                            list.add(new IdentityModel( "affectation",rang_affecté));
                            list.add(new IdentityModel( "choix1",choix1));
                            list.add(new IdentityModel( "choix2",choix2));
                            list.add(new IdentityModel( "choix3",choix3));


                            list1.add(new IdentityModel( "Prepa",namePrepa));
                            list1.add(new IdentityModel( "Prepa_filiere",namefiliere));
                            list1.add(new IdentityModel( "moyenne_annuelle",moyenne));
                            list1.add(new IdentityModel( "rang",rang));
                            list1.add(new IdentityModel( "rangmax",rangMax));
                            list1.add(new IdentityModel( "rangmajor",rangMaj));
                            list1.add(new IdentityModel( "rangaff",rangAff));


                            list2.add(new IdentityModel("bac",typebac));
                            list2.add(new IdentityModel("Moy_bac",moybac));
                            list2.add(new IdentityModel("Gouv_bac",gouvbac));







                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

                            contact_adapter = new AdapterContact(getContext(),list);
                            rv.setAdapter(contact_adapter);



                            rv.setLayoutManager(layoutManager);
                            contact_adapter.notifyDataSetChanged();

                            RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity());



                          contact = new AdapterContact(getContext(),list1);
                            rv1.setAdapter(contact);



                            rv1.setLayoutManager(mlayoutManager);
                            contact.notifyDataSetChanged();


                           RecyclerView.LayoutManager nlayoutManager = new LinearLayoutManager(getActivity());

                            contact1 = new AdapterContact(getContext(),list2);
                            rv2.setAdapter(contact1);



                            rv2.setLayoutManager(nlayoutManager);
                            contact1.notifyDataSetChanged();


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
