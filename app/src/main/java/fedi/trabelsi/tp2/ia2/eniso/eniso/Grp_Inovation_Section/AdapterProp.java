package fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class AdapterProp extends RecyclerView.Adapter<AdapterProp.ViewHolder> {

    private String responsable,disc;
    private Context mcontext;
    private ArrayList<GroupModel> mlist;
    public PopupWindow popupWindow;

    AdapterProp(Context context, ArrayList<GroupModel> list){
        mcontext=context;
        mlist=list;
    }

    @NonNull
    @Override
    public AdapterProp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mcontext);

        View view= layoutInflater.inflate(R.layout.proposed_groups,parent,false);
        AdapterProp.ViewHolder viewHolder= new AdapterProp.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProp.ViewHolder holder, int position) {
        GroupModel listmodel= mlist.get(position);




        TextView e = holder.equi1;
        TextView c = holder.co1;
        TextView m = holder.mem1;





        e.setText(listmodel.getEquipe());
        c.setText(listmodel.getCoach());
        m.setText(listmodel.getMembers());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView equi1,co1,tm1,mem1,desc,occ1,occ21;
        public Button open,join;


        public ViewHolder(final View itemView) {
            super(itemView);




            equi1 = itemView.findViewById(R.id.equipe1);
            co1 = itemView.findViewById(R.id.respproj1);
            mem1 = itemView.findViewById(R.id.alldiscrip1);

            join=itemView.findViewById(R.id.join1);
            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(),"joining "+equi1.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });








        }
    }





}
