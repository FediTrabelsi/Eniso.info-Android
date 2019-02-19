package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.PressAdapter;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.PressModel;

public class Showcase extends AppCompatActivity {
    public RequestQueue mQueue;
    private GridView gridView;
    private ArrayList<Feacher> mlist;
    private FeacherAdapter mAdapter;
    TextView tv;
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);

        mcontext=getApplicationContext();
        mQueue= Volley.newRequestQueue(mcontext);
        gridView=findViewById(R.id.gridview);


        getdata();
    }

    public void getdata() {
        final String url1="http://eniso.info/ws/core/wscript?s=Return(bean(%27core%27).findFullArticlesByDisposition(1,true,%22Showcase%22))";
        mlist = new ArrayList<>();


        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, url1, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray res = response.getJSONArray("$1");





                            for (int i = 0; i < res.length(); i++) {
                                JSONObject obj = res.getJSONObject(i);
                                JSONObject article=obj.getJSONObject("article");
                                String sujet=article.getString("subject");
                                String imgurl=article.getString("imageURL");
                                String fullurl="http://www.eniso.info/fs"+imgurl;


                                mlist.add(new Feacher(sujet,fullurl));
                                mAdapter=new FeacherAdapter(mcontext,mlist);
                                gridView.setAdapter(mAdapter);







                            }


                            mAdapter.notifyDataSetChanged();





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
