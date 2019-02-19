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

public class AdapterTeacher extends RecyclerView.Adapter<AdapterTeacher.ViewHolder> {
    private ArrayList<TeacherModel> list_teacher;
    private Context context_teacher;
    AdapterTeacher (Context context, ArrayList<TeacherModel> list){
        this.context_teacher=context;
        this.list_teacher=list;
    }



    @NonNull
    @Override
    public AdapterTeacher.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(context_teacher);
        View view=layoutInflater.inflate(R.layout.teacherstruct,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return (viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TeacherModel teacherModel =list_teacher.get(i);

        TextView tvTitre= viewHolder.Titre;
        EditText edvalTitre=viewHolder.valTitre;


        TextView tvetab= viewHolder.etab;
        EditText edvaletab=viewHolder.valetab;

        TextView tvdetail= viewHolder.det;
        EditText edvaldetail=viewHolder.valdet;

        TextView tvclé= viewHolder.clé;
        EditText edvalclé=viewHolder.valcle;

       tvTitre.setText(teacherModel.getTitre());
       edvalTitre.setHint(teacherModel.getVal_titre());

       tvetab.setText(teacherModel.getEtablissement());
       edvaletab.setHint(teacherModel.getVal_etab());

       tvdetail.setText(teacherModel.getDétail());
       edvaldetail.setHint(teacherModel.getVal_détail());

       tvclé.setText(teacherModel.getClé());
       edvalclé.setHint(teacherModel.getVal_clé());



    }

    @Override
    public int getItemCount() {
        return list_teacher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Titre ,etab,det,clé;
        public EditText valTitre,valetab,valdet,valcle;

        public ViewHolder(View itemView) {
            super(itemView);
            Titre= itemView.findViewById(R.id.titre);
            valTitre= itemView.findViewById(R.id.valeur);

            etab= itemView.findViewById(R.id.etab);
            valetab= itemView.findViewById(R.id.val_etab);

            det= itemView.findViewById(R.id.détail);
            valdet= itemView.findViewById(R.id.val_détail);

            clé= itemView.findViewById(R.id.clé);
            valcle= itemView.findViewById(R.id.val_clé);
        }
    }
}
