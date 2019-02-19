package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section.AdapterProp;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

class CustomPagerAdapter extends RecyclerView.Adapter<CustomPagerAdapter.ViewHolder> {
    static int h,w;

    ArrayList<Feacher> mlist;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context, ArrayList<Feacher> list) {
        mContext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public CustomPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mContext);
        h=parent.getHeight();
        w=parent.getWidth();

        View view= layoutInflater.inflate(R.layout.feachers_structure,parent,false);

        CustomPagerAdapter.ViewHolder viewHolder= new CustomPagerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomPagerAdapter.ViewHolder holder, int position) {

        Feacher feacher= mlist.get(position% mlist.size());

        TextView t= holder.tv;
        t.setText(feacher.getText());

        ImageView i = holder.img;
        Picasso.with(mContext).load(feacher.getSrcimg()).resize(w, h).centerCrop().into(i);

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public  TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.text);
            img=itemView.findViewById(R.id.img);
        }
    }
}