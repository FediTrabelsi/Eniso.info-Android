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
public class ContactFrag extends Fragment {
    AdapterContact contact_adapter;
    RecyclerView rv;
    Context Context_contact;
    RequestQueue mQueue;
    private Button mise;
    private ArrayList<IdentityModel> list;




    public ContactFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact,container,false);

        rv= v.findViewById(R.id.rvcontact);
        Context_contact = getActivity().getApplicationContext();
        mQueue= Volley.newRequestQueue(Context_contact);
        mise= v.findViewById(R.id.maji);

       getcontact();
        return v;
    }


    public void getcontact(){
        list= new ArrayList<>();
        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27academicProfile%27).getCurrentStudentCV())";

        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String mail1,mail2,phone1,phone2,phone3,siteweb,twitter,google,linkedin,github;
                            JSONObject alldonné = response.getJSONObject("$1");


                            JSONObject student = alldonné.getJSONObject("student");
                            JSONObject user = student.getJSONObject("user");

                            if(user.has("email")){
                             mail1 = user.getString("email");
                            }
                            else{
                                mail1="??????";
                            }
                            if(user.has("email2")){
                                mail2 = user.getString("email2");
                            }
                            else {
                                mail2="??????";
                            }

                            if(user.has("phone1")){
                                phone1 = user.getString("phone1");
                            }
                            else{
                                phone1="??????";
                            }
                            if(user.has("phone2")){
                                 phone2 = user.getString("phone2");
                            }
                            else {
                                phone2="??????";
                            }
                            if(user.has("phone3")){
                                phone3 = user.getString("phone3");
                            }
                            else{
                                phone3="??????";
                            }

                            if(alldonné.has("wwwURL")){
                                siteweb= alldonné.getString("wwwURL");
                            }
                            else{
                                siteweb="??????";
                            }
                            if(alldonné.has("socialURL1")){
                                twitter=alldonné.getString("socialURL1");
                            }
                            else{
                                twitter="??????";
                            }
                            if(alldonné.has("socialURL2")){
                                google=alldonné.getString("socialURL2");
                            }
                            else {
                                google="??????";
                            }
                            if(alldonné.has("socialURL3")){
                                linkedin=alldonné.getString("socialURL3");
                            }
                            else {
                                linkedin="??????";
                            }
                            if(alldonné.has("socialURL4")){
                                github="hi "+ alldonné.getString("socialURL4");

                            }
                            else {
                                github="??????";

                            }





                            list.add(new IdentityModel( "Email1",mail1));
                            list.add(new IdentityModel( "Email2",mail2));
                            list.add(new IdentityModel( "Tel 1",phone1));
                            list.add(new IdentityModel( "Tel 2",phone2));
                            list.add(new IdentityModel( "Tel 3",phone3));
                            list.add(new IdentityModel( "Site Web",siteweb));
                            list.add(new IdentityModel( "Twitter",twitter));
                            list.add(new IdentityModel( "Google+",google));
                            list.add(new IdentityModel( "Linkedin",linkedin));
                            list.add(new IdentityModel( "Github",github));




                            contact_adapter = new AdapterContact(getContext(),list);
                            rv.setAdapter(contact_adapter);


                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            rv.setLayoutManager(layoutManager);
                            contact_adapter.notifyDataSetChanged();



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
