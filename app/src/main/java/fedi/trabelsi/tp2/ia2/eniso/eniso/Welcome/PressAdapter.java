package fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class PressAdapter extends RecyclerView.Adapter<PressAdapter.ViewHolder> {
    private Context mycontext;
    private ArrayList<PressModel> mlist;

    public PressAdapter(Context c,ArrayList<PressModel> list ){
        mycontext=c;
        mlist=list;
    }
    @NonNull
    @Override
    public PressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mycontext);

        View view= layoutInflater.inflate(R.layout.press_structure,parent,false);
        PressAdapter.ViewHolder viewHolder= new PressAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PressAdapter.ViewHolder holder, int position) {
        PressModel model=mlist.get(position);

        TextView t=holder.title;
        TextView c=holder.content;
        TextView s=holder.autheur;

        t.setText(model.getTitre());
        c.setText(model.getContenu());
        s.setText(model.getSender());
        t.setPaintFlags(t.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,content,autheur;
        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.presstitle);
            content=itemView.findViewById(R.id.presscontent);
            autheur=itemView.findViewById(R.id.pressender);
        }
    }
}
