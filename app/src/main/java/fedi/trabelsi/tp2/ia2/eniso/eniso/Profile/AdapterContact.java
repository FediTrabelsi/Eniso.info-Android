package fedi.trabelsi.tp2.ia2.eniso.eniso.Profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ViewHolder> {
    private ArrayList<IdentityModel> list_contact;
    private Context context_contact;
    AdapterContact(Context context, ArrayList<IdentityModel> list){
        this.context_contact=context;
        this.list_contact=list;

    }

    @NonNull
    @Override
    public AdapterContact.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater= LayoutInflater.from(context_contact);
        View view= layoutInflater.inflate(R.layout.identitystruct,viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContact.ViewHolder viewHolder, int i) {
        IdentityModel identityModel=list_contact.get(i);

        TextView inf=viewHolder.information;
        TextView valeur=viewHolder.val;


        inf.setText(identityModel.getTitre());
        valeur.setText(identityModel.getValuere());
    }



    @Override
    public int getItemCount() {
        return list_contact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView information ,val;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            information= itemView.findViewById(R.id.titre);
            val=itemView.findViewById(R.id.valeur);
        }
    }
}
