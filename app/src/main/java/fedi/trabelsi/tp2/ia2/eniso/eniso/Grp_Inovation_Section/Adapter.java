
package fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String responsable,disc;
    private  Context mcontext;
    private ArrayList<GroupModel> mlist;
    public PopupWindow popupWindow;

    Adapter(Context context, ArrayList<GroupModel> list){
        mcontext=context;
        mlist=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mcontext);

        View view= layoutInflater.inflate(R.layout.group_structure,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupModel listmodel= mlist.get(position);



        TextView occ=holder.occ;
        TextView occ2=holder.occ2;
        TextView e = holder.equi;
        TextView c = holder.co;
        TextView t= holder.tm;
        TextView m = holder.mem;




        occ.setText(listmodel.getDesc());

        occ2.setText(listmodel.getOwner());
        e.setText(listmodel.getEquipe());
        c.setText(listmodel.getCoach());
        t.setText(listmodel.getTeam());
        m.setText(listmodel.getMembers());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView equi,co,tm,mem,desc,occ,occ2;
        public Button open,join;


        public ViewHolder(final View itemView) {
            super(itemView);

            View v = LayoutInflater.from(mcontext).inflate(R.layout.openteam, null);

            occ= itemView.findViewById(R.id.occ);
            occ2=itemView.findViewById(R.id.occ2);
            equi = itemView.findViewById(R.id.equipe1);
            co = itemView.findViewById(R.id.respproj1);
            tm = itemView.findViewById(R.id.team);
            mem = itemView.findViewById(R.id.members);
            open= itemView.findViewById(R.id.open);
            join=itemView.findViewById(R.id.join1);
            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(),"joining "+equi.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });


            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //instantiate the popup.xml layout file
                    LayoutInflater layoutInflater = (LayoutInflater) itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.openteam,null);
                    TextView dis=customView.findViewById(R.id.fulldesc);
                    TextView respo=customView.findViewById(R.id.resp);
                    TextView teem=customView.findViewById(R.id.tmm);
                    TextView doc= customView.findViewById(R.id.doc);
                    Spannable teamcolored= new SpannableString("Team :  ");
                    teamcolored.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, teamcolored.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    Spannable rescolored= new SpannableString("Responsable :  ");
                    rescolored.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, rescolored.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    Spannable doccolored= new SpannableString("Document :  ");
                    doccolored.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, doccolored.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                    doc.setText(doccolored);
                    doc.append("(Aucun)");
                    dis.setText("Description : \n"+occ2.getText());
                    respo.setText(rescolored);
                    respo.append(occ.getText());
                    teem.setText(teamcolored);
                    teem.append(equi.getText());
                    Button closePopupBtn = (Button) customView.findViewById(R.id.close);



                    //instantiate popup window
                     popupWindow = new PopupWindow(customView, 1000, 1200);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(
                            android.graphics.Color.TRANSPARENT));

                    RelativeLayout relativeLayout = itemView.findViewById(R.id.rl);
                    popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);


                    //close the popup window on button click
                    closePopupBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();

                        }
                    });

                }
            });






        }
    }
}


