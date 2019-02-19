package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;



import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nightonke.boommenu.Eases.Linear;
import com.squareup.picasso.Picasso;

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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    public ArrayList<String> imgurls=new ArrayList<>();
    public RequestQueue mQueue;
    public PopupWindow popupWindow;
    public ArrayList<ArticleModel> alllist;
    public DisplayAdapter displayAdapter;
    public RecyclerView myrv;
    public  Button closepopup;
    TextView atitle,adesc,aextra;
    int[] ind ={0};
    RecyclerView imges;

    ArrayList<Activitie> mlist;
    Context mcontext;
    String[] bg={
            "#09A9FF",
            "#3E51B1",
            "#673BB7",
            "#4BAA50",
            "#F94336",
            "#0A9B88"
    };
    int[] imageId={
            R.drawable.iconcamp,
            R.drawable.iconforum,
            R.drawable.iconrocket,
            R.drawable.iconproxym,
            R.drawable.iconotce,
            R.drawable.iconruh
    };

    public CustomAdapter(Context context,ArrayList<Activitie> list){
        mcontext=context;
        mlist=list;

    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.activities_structure, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.ViewHolder holder, final int position) {
        Activitie activitie = mlist.get(position);
        TextView t =holder.acttitle;
        TextView d= holder.actdesc;
        final ImageView[] i = {holder.img};
        LinearLayout l= holder.relativeLayout;
       // l.setBackgroundColor(Color.parseColor(bg[position]));
        Button openfull=holder.open;
        t.setText(activitie.getActivitietitle());
        d.setText(activitie.getActivitiedesc());
        i[0].setImageResource(imageId[position]);



        openfull.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activities_full_structure,null);

                atitle=customView.findViewById(R.id.actftitle);
                adesc=customView.findViewById(R.id.actfuldesc);
                aextra=customView.findViewById(R.id.actextra);
                imges=customView.findViewById(R.id.galery);
                mQueue= Volley.newRequestQueue(mcontext);

                closepopup=customView.findViewById(R.id.fullclose);

                getArticles(position);

                Timer t1 = new Timer();

                t1.scheduleAtFixedRate(new TimerTask() {

                                           @Override
                                           public void run() {
                                               imges.smoothScrollToPosition(ind[0]);
                                               ind[0]++;
                                           }

                                       },

                        2000,

                        3000);

                LinearLayout relativeLayout=holder.relativeLayout;
                popupWindow = new PopupWindow(customView, 1000, 1400);
                popupWindow.setBackgroundDrawable(new ColorDrawable(
                        android.graphics.Color.TRANSPARENT));



                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
                closepopup.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView acttitle,actdesc;
        LinearLayout relativeLayout;
        Button open,close;
        LinearLayout linear;
        ImageView img;
        public ViewHolder(final View itemView) {
            super(itemView);
            relativeLayout =  itemView.findViewById(R.id.linact);
            actdesc= itemView.findViewById(R.id.griddesc);
            acttitle=itemView.findViewById(R.id.gridtitle);
            img=itemView.findViewById(R.id.gridimg);
            linear=itemView.findViewById(R.id.lin);
            open=itemView.findViewById(R.id.openfull);

            LinearLayout relativeLayout = itemView.findViewById(R.id.linact);




        }
    }
    public void getArticles(final int pos){
        String url="http://eniso.info/ws/core/wscript?s=Return(bean(%27core%27).findFullArticlesByDisposition(1,true,%22Activities%22))";
        alllist=new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST, url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray res = response.getJSONArray("$1");






                                JSONObject obj = res.getJSONObject(pos);
                                JSONObject article=obj.getJSONObject("article");
                                JSONObject sender=article.getJSONObject("sender");
                                String sendername=sender.getString("fullName");
                                String sujet=article.getString("subject");
                                String extra=article.getString("content");
                                String visits=article.getString("visitCount");

                                String date=article.getString("sendTime");
                                date=date.substring(0,date.indexOf(",")+6);
                                String allsend="By : "+sendername+",le "+date+" visit count : "+visits;

                                extra= Html.fromHtml(extra).toString();

                                atitle.setText(sujet.toString());
                                adesc.setText(extra);
                                aextra.setText(allsend);


                                JSONArray att=obj.getJSONArray("attachments");
                                for(int k=0;k<=att.length();k++){
                                    JSONObject im=att.getJSONObject(k);
                                    JSONObject gal=im.getJSONObject("file");
                                    alllist.add( new ArticleModel("http://www.eniso.info/fs"+gal.getString("path"),
                                            "http://www.eniso.info/fs"+gal.getString("path"),
                                            "http://www.eniso.info/fs"+gal.getString("path"),
                                            "http://www.eniso.info/fs"+gal.getString("path")));

                                    displayAdapter=new DisplayAdapter(mcontext,alllist);
                                    imges.setAdapter(displayAdapter);
                                    imges.setLayoutManager(new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false));

                                    displayAdapter.notifyDataSetChanged();


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
