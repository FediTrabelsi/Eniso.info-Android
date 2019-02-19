package fedi.trabelsi.tp2.ia2.eniso.eniso.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section.ViewFrag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.Welcome;

public class Login extends AppCompatActivity {
    public static String sessionId;
    public static String usertype;
    public static String id;
    public static String login;
    public static String iconurl;
    public static String username,classe;
    public   static String password;
    public static boolean online = false;
    ActionBar actionBar ;






    public static void login(final String u , final String p, RequestQueue mQueue, final TextView t, final Button btn, final Context context){
        final String url1 = "http://eniso.info/ws/core/login/"+u+"?password="+p+"";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                         /*   Intent i = new Intent(getApplicationContext(),Groupes.class);
                            startActivity(i);*/
                            JSONObject res = response.getJSONObject("$1");
                            String ustype = res.getString("userTypeName");
                            String ustid = res.getString("userId");
                            id=ustid;
                            usertype =ustype;
                            String Name=res.getString("userFullName");
                            username=Name;
                            online = true;
                            String usft = res.getString("userFullTitle");
                            String profileimg=res.getString("iconURL");
                            iconurl="http://eniso.info/fs"+profileimg;
                            String clas= usft.substring(usft.length()-6,usft.length()-2);
                            login=clas.trim();
                            String sessioniD = res.getString("sessionId");
                            classe=ustype+" "+usft.substring(usft.indexOf("-"),usft.length());
                            sessionId="JSESSIONID="+sessioniD;
                            if(login.contains("1")){
                                password="13";
                                login="0";
                            }
                            else if (login.contains("2")){
                                password="14";
                                login="1";
                            }
                            else if(login.contains("3")){
                                password="15";
                                login="2";
                            }
                            else{
                                password="13";
                                login="0";
                            }
                            t.setText("Welcome"+u);
                            btn.setEnabled(true);
                            Intent intent= new Intent(context, Welcome.class);
                            context.startActivity(intent);





                        } catch (JSONException e) {
                            try {
                                JSONObject res1 = response.getJSONObject("$error");
                                String m = res1.getString("message");
                                t.setText(m);

                            } catch (JSONException a) {


                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t.setText("No Internet connection");            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {

                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    online = false;
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException | JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie", "JSESSIONID="+sessionId);
                return params;
            }
        };





        mQueue.add(request);

    }
}
