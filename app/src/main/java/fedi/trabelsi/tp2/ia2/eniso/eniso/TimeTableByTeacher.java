package fedi.trabelsi.tp2.ia2.eniso.eniso;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Emploi_Activity;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.TeacherTimeTable;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section.InnovationGroups;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.TimeTable;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Profile.StudentProfile;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Profile.TeacherProfile;
import fedi.trabelsi.tp2.ia2.eniso.eniso.SessionAdaptater.SessionListAdapter;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase.Testing;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.Welcome;

public class TimeTableByTeacher extends AppCompatActivity {
    public RequestQueue mQueue;
    private Context mcontext;
    private ListView list ;
    private EditText sv;
    Map<String,Integer> cleVal ;
    private ArrayAdapter<String> adap;
    SessionListAdapter adapter1;
    android.support.v7.app.ActionBar actionBar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_by_teacher);
        mcontext=getApplicationContext();
        mQueue = Volley.newRequestQueue(getApplicationContext());
        list = (ListView) findViewById(R.id.enseignants_list);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ImageView header= (ImageView)navigationView.getHeaderView(0).findViewById(R.id.profimg);
        TextView name=navigationView.getHeaderView(0).findViewById(R.id.titl);
        name.setText(Login.username);
        TextView desc=navigationView.getHeaderView(0).findViewById(R.id.desc);
        desc.setText(Login.classe);
        Picasso.with(getApplicationContext()).load(Login.iconurl).resize(250,250).error(R.drawable.calendar).into(header);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        switch (id){
                            case R.id.nav_profile :{
                                if (Login.usertype.equals("Etudiants")){
                                    Intent i= new Intent(mcontext,StudentProfile.class);
                                    startActivity(i);
                                }
                                else{
                                    Intent i= new Intent(mcontext,TeacherProfile.class);
                                    startActivity(i);
                                    break;
                                }

                                break;
                            }
                            case R.id.nav_wall : {
                                Intent i= new Intent(mcontext,Welcome.class);
                                startActivity(i);
                                break;
                            }

                            case R.id.nav_timetable : {
                                Intent i= new Intent(mcontext,TimeTable.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_grtimetable : {
                                Intent i= new Intent(mcontext,TimeTableByGroup.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_groups : {
                                Intent i= new Intent(mcontext,InnovationGroups.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_calendar : {
                                Intent i= new Intent(mcontext,Testing.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_teachertimetable : {
                                Intent i= new Intent(mcontext,TimeTableByTeacher.class);
                                startActivity(i);
                                break;
                            }
                            default: ;


                        }




                        mDrawerLayout.closeDrawers();



                        return true;
                    }
                });



        getdata();
    }
    public  void getdata(){
        String url="http://eniso.info/ws/core/wscript?s=Return(bean('core').getPluginsAPI())";
        String url2="http://eniso.info/ws/core/wscript?s=Return(bean(%22academic%22).findActiveTeachersStrict())";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(com.android.volley.Request.Method.GET,url2,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            final ArrayList<String> items= new ArrayList();
                            cleVal = new HashMap <String, Integer>() ;


                            final ArrayList<Session> names = new ArrayList();
                            final ArrayList<Integer> ids = new ArrayList();
                            JSONArray i = response.getJSONArray("$1");
                            JSONObject ob ;
                            String name ;
                            int id ;

                            for (int j = 0; j <i.length() ; j++) {
                                ob = i.getJSONObject(j);
                                name = ob.getString("fullName");
                                id = ob.getInt("id");
                                //names.add(new Session(name,"","",""));
                                items.add(name);
                                ids.add(id);
                                cleVal.put(name,id);

                            }


                            list = (ListView) findViewById(R.id.enseignants_list);


                            //adapter1 = new SessionListAdapter(getApplication(),R.layout.adapter_view_layout,names);


                            sv=(EditText) findViewById(R.id.chercher_en);
                            adap = new ArrayAdapter(getApplication(),R.layout.support_simple_spinner_dropdown_item,items);

                            list.setAdapter(adap);



                            sv.addTextChangedListener(new TextWatcher() {

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    // Call back the Adapter with current character to Filter
                                    adap.getFilter().filter(s.toString());

                                }

                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count,int after) {
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                }
                            });



                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                /*@Override
                                public String getItem(int position) {
                                    return items.get(position);
                                }*/

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String model=  adap.getItem(position);



                                    //int  item = ids.get(position);
                                    //int vid = Integer.parseInt(item);
                                    //Model model=(Model)adap.getItem(position);
                                    //String titlePosition = (String) adap.getItem(position);
                                    // expense = expenseManager.getExpense(titlePosition);


                                    Intent i = new Intent(getApplicationContext(),TeacherTimeTable.class);
                                    i.putExtra("id",cleVal.get(model));
                                    i.putExtra("name",model);
                                    startActivity(i);
                                }
                            });


                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                String m = res1.getString("message");
                                //data.append("\n"+m+"\n"+Login.sessionId+"\n"+Login.login+"\n"+Login.password);
                            } catch (JSONException a) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                        }
                        adap.notifyDataSetChanged();
                        progressDialog.dismiss();

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
                params.put("Cookie", Login.sessionId);
                return params;
            }




        };



        mQueue.add(req);
        mQueue.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enseignant_search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


}
