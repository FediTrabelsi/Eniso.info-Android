package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;

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

import fedi.trabelsi.tp2.ia2.eniso.eniso.Active;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase.TbsAdapter;

public class ActiveAdapter extends RecyclerView.Adapter<ActiveAdapter.ViewHolder> {

    public ArrayList<Active> mlist;
    public Context mcontext;
    public ActiveAdapter(Context context, ArrayList<Active> list){
        mcontext=context;
        mlist=list;
    }
    @NonNull
    @Override
    public ActiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mcontext);
        View view= layoutInflater.inflate(R.layout.activity_active_now_adapter,parent,false);

        ActiveAdapter.ViewHolder viewHolder= new ActiveAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveAdapter.ViewHolder holder, int position) {
        Active active=mlist.get(position);

        TextView  n=holder.name;
        TextView t=holder.type;
        ImageView i = holder.icon;

        n.setText(active.getUserFullTitle());
        t.setText(active.getUserTypeName());
        Picasso.with(mcontext).load(active.getIconURL()).error(R.drawable.ic_menu_share).into(i);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,type;
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.usr_name);
            type= itemView.findViewById(R.id.usr_groupe);
            icon=itemView.findViewById(R.id.user_icon);

        }
    }
}
