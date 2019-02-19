package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

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
import java.util.Timer;
import java.util.TimerTask;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.ArticleModel;

public class Testing extends AppCompatActivity {
    public RequestQueue mQueue;
    private RecyclerView rv,rv1;
    private ArrayList<Feacher> mlist;
    private CustomPagerAdapter mAdapter;
    private TbsAdapter mAdapt;
    private ArrayList<ArticleModel> mliste;
    TextView tv;
    int i=1;
    Handler handler;
    Timer timer;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    private Context mcontext;
    RecyclerView rvactivities;
    CustomAdapter Adaptergrid;
    ArrayList<Activitie> act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        rvactivities= findViewById(R.id.gridactivities);



        mcontext=getApplicationContext();
        mQueue= Volley.newRequestQueue(mcontext);
        rv= findViewById(R.id.rvfeacher);
        rv1=findViewById(R.id.rvtbs);
        tv =findViewById(R.id.textViewtest);



        initInstancesDrawer();

        getdata();
        getTBSdata();
        getArticles();



        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      rv.smoothScrollBy(50,0);
                                  }

                              },

                2000,

                100);

        Timer t1 = new Timer();

        t1.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      rv1.smoothScrollToPosition(i);
                                      i++;
                                  }

                              },

                2000,

                3000);





    }

    private void initInstancesDrawer() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("About Eniso");

    }

    private Runnable runnableCode2 = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            rv1.scrollToPosition(i);
            i++;
        }
    };

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            rv.smoothScrollBy(80,0);
        }
    };

    public void getdata() {
        final String url1="http://eniso.info/ws/core/wscript?s=Return(bean(%27core%27).findFullArticlesByDisposition(1,true,%22Showcase%22))";
        mlist = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, url1, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray res = response.getJSONArray("$1");





                            for (int i =0;i<res.length(); i++) {
                                JSONObject obj = res.getJSONObject(i);
                                JSONObject article=obj.getJSONObject("article");
                                String sujet=article.getString("subject");
                                String imgurl=article.getString("imageURL");
                                String fullurl="http://www.eniso.info/fs"+imgurl;


                                mlist.add(new Feacher(sujet,fullurl));
                                mAdapter=new CustomPagerAdapter(mcontext,mlist);
                                rv.setAdapter(mAdapter);
                                rv.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false));

                                mAdapter.notifyDataSetChanged();










                            }







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

    public void getTBSdata() {
        final String url1="http://eniso.info/ws/core/wscript?s=Return(bean(%27core%27).findFullArticlesByDisposition(1,true,%22People.Heading%22))";
        mliste = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, url1, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray res = response.getJSONArray("$1");





                            for (int i =0;i<res.length(); i++) {
                                JSONObject obj = res.getJSONObject(i);
                                JSONObject article=obj.getJSONObject("article");
                                String sujet=article.getString("subject");
                                String extra=article.getString("content");
                                String imgurl=article.getString("imageURL");
                                String fullurl="http://www.eniso.info/fs"+imgurl;
                                extra= Html.fromHtml(extra).toString();


                                mliste.add(new ArticleModel(sujet,extra,fullurl,""));
                                mAdapt=new TbsAdapter(mcontext,mliste);
                                rv1.setAdapter(mAdapt);
                                rv1.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false));

                                mAdapt.notifyDataSetChanged();










                            }







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

    public void getArticles(){
        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27core%27).findFullArticlesByDisposition(1,true,%22Activities%22))";
        act=new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray res = response.getJSONArray("$1");





                            for (int i =0;i<6; i++) {
                                JSONObject obj = res.getJSONObject(i);
                                JSONObject article=obj.getJSONObject("article");
                                String sujet=article.getString("subject");
                                String extra=article.getString("content");

                                extra= Html.fromHtml(extra).toString();


                                act.add(new Activitie("x",sujet,extra));
                                Adaptergrid= new CustomAdapter(mcontext,act);
                                rvactivities.setAdapter(Adaptergrid);
                                GridLayoutManager manager = new GridLayoutManager(mcontext, 2, GridLayoutManager.VERTICAL, false);
                                rvactivities.setLayoutManager(manager);
                                Adaptergrid.notifyDataSetChanged();










                            }







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
