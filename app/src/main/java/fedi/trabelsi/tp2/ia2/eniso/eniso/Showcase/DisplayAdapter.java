package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.ArticleModel;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {
    public Context mcontext;
    public ArrayList<ArticleModel> mlist;

    public DisplayAdapter(Context c,ArrayList<ArticleModel> list){
        mcontext=c;
        mlist=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.galery, parent, false);

        return new DisplayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleModel mymodel=mlist.get(position% mlist.size());
        ImageView imageView=holder.url;
        Picasso.with(mcontext).load(mymodel.getBody()).error(R.drawable.table).resize(1000,700).centerCrop().into(imageView);

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView url;
        public ViewHolder(View itemView) {
            super(itemView);
            url=itemView.findViewById(R.id.imgdisplay);
        }
    }
}
