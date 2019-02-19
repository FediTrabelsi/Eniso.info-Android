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

public class CordonFragTeacher extends Fragment {
    Button mise;
    RequestQueue mQueue;
    Context Tcontext;
    Adapter TAdapter;
    RecyclerView rv;
    Button btn;
    private ArrayList<IdentityModel> list_cord;

    public CordonFragTeacher(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag_cordon_teacher,container,false);
        rv= v.findViewById(R.id.rvcordon);
        Tcontext= getActivity().getApplicationContext();
        mQueue= Volley.newRequestQueue(Tcontext);


        mise= v.findViewById(R.id.maji);

        getIdentityTeacher();

        return v;
    }

    public void getIdentityTeacher(){
        list_cord=new ArrayList<>();
        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27academicProfile%27).getCurrentTeacherCV())";
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String fulname,email1,email2,phone1,phone2,phone3,numbureau,phonebureau,siteweb,google,github,twitter,linkedin,facebook;

                            JSONObject alldonné = response.getJSONObject("$1");
                            JSONObject teacher = alldonné.getJSONObject("teacher");
                            JSONObject user= teacher.getJSONObject("user");

                            if(user.has("fullName")){
                                fulname = user.getString("fullName");
                            }
                            else {
                                fulname="??????";
                            }

                            if(user.has("email")){
                                email1 = user.getString("email");
                            }
                            else {
                                email1="??????";
                            }

                            if(user.has("email2")){
                                email2 = user.getString("email2");
                            }
                            else {
                                email2="??????";
                            }
                            if(user.has("phone1")){
                                phone1=user.getString("phone1");
                            }
                            else {
                                phone1="??????";
                            }
                            if(user.has("phone2")){
                                phone2=user.getString("phone2");
                            }
                            else {
                                phone2="??????";
                            }
                            if(user.has("phone3")){
                                phone3=user.getString("phone3");
                            }
                            else {
                                phone3="??????";
                            }

                            if(user.has("officeLocationNumber")){
                                numbureau=user.getString("officeLocationNumber");
                            }
                            else {
                                numbureau="??????";
                            }

                            if(user.has("officePhoneNumber")){
                                phonebureau=user.getString("officePhoneNumber");
                            }
                            else{
                                phonebureau="??????";
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
                                github=alldonné.getString("socialURL4");

                            }
                            else {
                                github="??????";

                            }
                            if(alldonné.has("socialURL5")){
                                facebook=alldonné.getString("socialURL5");

                            }
                            else {
                                facebook="??????";

                            }



                            list_cord.add(new IdentityModel("Nom Prénom",fulname));
                            list_cord.add(new IdentityModel("Email1",email1));
                            list_cord.add(new IdentityModel("Email2",email2));
                            list_cord.add(new IdentityModel("tel1",phone1));
                            list_cord.add(new IdentityModel("tel2",phone2));
                            list_cord.add(new IdentityModel("tel3",phone3));
                            list_cord.add(new IdentityModel("Numéro du bureau",numbureau));
                            list_cord.add(new IdentityModel(" tel du bureau",phonebureau));
                            list_cord.add(new IdentityModel("Site Web",siteweb));
                            list_cord.add(new IdentityModel("Twitter",twitter));
                            list_cord.add(new IdentityModel("Google+ URL",google));
                            list_cord.add(new IdentityModel("Linkedin URL",linkedin));
                            list_cord.add(new IdentityModel("GitHub URL",github));
                            list_cord.add(new IdentityModel("Facebook URL",facebook));





                            TAdapter=new Adapter(Tcontext,list_cord);
                            rv.setAdapter(TAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Tcontext);
                            rv.setLayoutManager(layoutManager);
                            TAdapter.notifyDataSetChanged();



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
