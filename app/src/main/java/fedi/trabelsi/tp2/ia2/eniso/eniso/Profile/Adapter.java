package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<IdentityModel> mlist;
    private Context mcontext;


    Adapter(Context context, ArrayList<IdentityModel> list){
        this.mcontext=context;
        this.mlist=list;

    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater= LayoutInflater.from(mcontext);
        View view= layoutInflater.inflate(R.layout.identitystruct,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        IdentityModel listmodel = mlist.get(i);
        TextView tvtitre= viewHolder.titre;
        EditText edvaleur=viewHolder.valeur;

        tvtitre.setText(listmodel.getTitre());
        edvaleur.setHint(listmodel.getValuere());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titre,aff;
        public EditText valeur;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titre = itemView.findViewById(R.id.titre);
            valeur= itemView.findViewById(R.id.valeur);



        }
    }
}
