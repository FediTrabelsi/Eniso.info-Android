package fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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

import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe.Gr_days.Gr_Jeudi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe.Gr_days.Gr_Lundi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe.Gr_days.Gr_Mardi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe.Gr_days.Gr_Mercredi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe.Gr_days.Gr_Samedi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Groupe.Gr_days.Gr_Vendredi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.PageAdaptater.SectionPageAdapter;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Session;

public class Gr_Activity extends AppCompatActivity {
    public ArrayList<String> ids = new ArrayList<>();
    public ArrayList<String> names = new ArrayList<>();
    public RequestQueue mQueue;
    public static String name  ;
    private Context context;
    private static final String TAG = "Gr_Activity";
    private SectionPageAdapter Gr_SectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gr_);

        Gr_SectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.gr_container);
        setupViewPager(mViewPager);




        mQueue = Volley.newRequestQueue(getApplicationContext());
        Bundle extra = getIntent().getExtras();
        name = extra.getString("name");
        //getdata();



    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Gr_Lundi() , "LUN");
        adapter.addFragment(new Gr_Mardi() , "MAR");
        adapter.addFragment(new Gr_Mercredi() , "MER");
        adapter.addFragment(new Gr_Jeudi() , "JEU");
        adapter.addFragment(new Gr_Vendredi() , "VEN");
        adapter.addFragment(new Gr_Samedi() , "SAM");
        viewPager.setAdapter(adapter);
    }



    public  void getdata(){
        String url="http://eniso.info/ws/core/wscript?s=Return(bean('core').getPluginsAPI())";
        String url2="http://eniso.info/ws/core/wscript?s=Return(bean(%22academicPlanning%22).loadStudentPlanningListNames())";




        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET,url2,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                         /*   Intent i = new Intent(getApplicationContext(),Groupes.class);
                            startActivity(i);*/
                            // JSONObject res =  response.getJSONObject("$1");
                            ArrayList<Session> sesssionList = new ArrayList();
                            JSONArray i = response.getJSONArray("$1");
                            JSONObject ob = i.getJSONObject(5);
                            String id = ob.getString("id");
                            String name = ob.getString("name");

                            /*for (int j = 0; j <i.length() ; j++) {
                                ob = i.getJSONObject(j);
                                id = ob.getString("id");
                                name = ob.getString("name");
                                ids.add(id);
                                names.add(name);


                            }*/



                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                String m = res1.getString("message");
                                //data.append("\n"+m+"\n"+Login.sessionId+"\n"+Login.login+"\n"+Login.password);
                            } catch (JSONException a) {

                            }
                        }
                    }

                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handle error

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie", "JSESSIONID="+ Login.sessionId);
                return params;
            }




        };



        mQueue.add(req);
        mQueue.start();

    }



}
