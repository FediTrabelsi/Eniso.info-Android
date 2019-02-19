package fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class WallAdapter extends RecyclerView.Adapter<WallAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<ArticleModel> mlist;


    public WallAdapter(Context context, ArrayList<ArticleModel> list){
        mcontext=context;
        mlist=list;
    }
    @NonNull
    @Override
    public WallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mcontext);

        View view= layoutInflater.inflate(R.layout.article_structure,parent,false);
        WallAdapter.ViewHolder viewHolder= new WallAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WallAdapter.ViewHolder holder, int position) {
        ArticleModel listmodel= mlist.get(position);
        TextView articlecontent=holder.arti;
        TextView extracontent=holder.extra;
        TextView bodycontent=holder.body;
        TextView writercontent= holder.writer;
        extracontent.setText(listmodel.getExtra());
        bodycontent.setText(listmodel.getBody());
        writercontent.setText(listmodel.getWriter());
        articlecontent.setText(listmodel.getArticle());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView arti,extra,body,writer;
        public ViewHolder(View itemView) {
            super(itemView);
            View v = LayoutInflater.from(mcontext).inflate(R.layout.article_structure, null);
            extra= itemView.findViewById(R.id.extra);
            body=itemView.findViewById(R.id.body);
            writer=itemView.findViewById(R.id.writer);
            arti= itemView.findViewById(R.id.textView2);
        }
    }
}
