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
public class IdentityFrag extends Fragment {
    Button mise;
    RequestQueue mQueue;
    Context mcontext;
    Adapter mAdapter;
    RecyclerView rv;

    private ArrayList<IdentityModel> list;



    public IdentityFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_identity,container,false);
        rv= v.findViewById(R.id.rvident);
        mcontext= getActivity().getApplicationContext();
        mQueue= Volley.newRequestQueue(mcontext);
        mise= v.findViewById(R.id.maji);


        getprofileidentity();



        mise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    public void getprofileidentity(){
        list=new ArrayList<>();

        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%22academicProfile%22).getCurrentStudentCV())";

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String prénom,nom,prénomar,nomar,cin,inscription,date_naissance,lieu_naissance,nameciv,genre,positionTitle1,positionTitle2,positionTitle3;

                            JSONObject alldonné = response.getJSONObject("$1");
                            JSONObject student = alldonné.getJSONObject("student");
                            JSONObject user = student.getJSONObject("user");
                            if(user.has("firstName")){
                                prénom = user.getString("firstName");
                            }
                            else {
                                prénom="??????";
                            }
                            if(user.has("lastName")){
                                nom = user.getString("lastName");
                            }
                            else{
                                nom="??????";
                            }
                            if (user.has("firstName2")){
                                prénomar = user.getString("firstName2");
                            }
                            else{
                                prénomar = "??????";
                            }
                            if(user.has("lastName2")){
                                nomar = user.getString("lastName2");
                            }
                            else{
                                nomar="??????";
                            }
                            if (user.has("nin")){
                                cin = user.getString("nin");
                            }
                            else {
                                cin="??????";
                            }
                            if(student.has("subscriptionNumber")) {
                                inscription = student.getString("subscriptionNumber");
                            }
                            else{
                                inscription="??????";
                            }
                            if(user.has("birthDate")){
                                date_naissance =user.getString("birthDate");
                            }
                            else{
                                date_naissance ="??????";
                            }
                            if(user.has("birthLocation")){
                                lieu_naissance =user.getString("birthLocation");
                            }
                            else {
                                lieu_naissance="??????";
                            }

                            JSONObject civility = user.getJSONObject("civility");
                            if(civility.has("name")) {
                                nameciv = civility.getString("name");
                            }
                            else{
                                nameciv="??????";
                            }
                            JSONObject gender =user.getJSONObject("gender");
                            if(gender.has("name")) {
                                genre = gender.getString("name");
                            }
                            else{
                                genre="??????";
                            }
                            if(user.has("positionTitle1")){
                                positionTitle1 =user.getString("positionTitle1");
                            }
                            else {
                                positionTitle1 ="??????";

                            }
                            if(user.has("positionTitle2")){
                                positionTitle2 =user.getString("positionTitle2");
                            }
                            else{
                                positionTitle2="??????";
                            }
                            if(user.has("positionTitle1")) {
                                positionTitle3 = user.getString("positionTitle1");
                            }
                            else {
                                positionTitle3="??????";
                            }


                            list.add(new IdentityModel( "CIN",cin));
                            list.add(new IdentityModel( "N.inscription",inscription));
                            list.add(new IdentityModel( "Nom",nom));
                            list.add(new IdentityModel( "Prénom",prénom));
                            list.add(new IdentityModel( "NomAr",nomar));
                            list.add(new IdentityModel( "PrénomAr",prénomar));
                            list.add(new IdentityModel( "Date de naissance",date_naissance));
                            list.add(new IdentityModel( "Lieu de naissance",lieu_naissance));

                            list.add(new IdentityModel( "Genre",genre));
                            list.add(new IdentityModel( "Civilité",nameciv));
                            list.add(new IdentityModel( "Titre1",positionTitle1));
                            list.add(new IdentityModel( "Titre2",positionTitle2));
                            list.add(new IdentityModel( "Titre3",positionTitle3));


                            mAdapter= new Adapter(getContext(),list);
                            rv.setAdapter(mAdapter);


                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            rv.setLayoutManager(layoutManager);
                            mAdapter.notifyDataSetChanged();



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
