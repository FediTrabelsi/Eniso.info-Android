package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.ArticleModel;

public class TbsAdapter extends RecyclerView.Adapter<TbsAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<ArticleModel> mlist;
    static  int h,w;
    public PopupWindow popupWindow;

    public TbsAdapter(Context c,ArrayList<ArticleModel> list){
        this.mcontext=c;
        this.mlist=list;
    }
    @NonNull
    @Override
    public TbsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mcontext);
        h=parent.getHeight();
        w=parent.getWidth();

        View view= layoutInflater.inflate(R.layout.tbs_structure,parent,false);

        TbsAdapter.ViewHolder viewHolder= new TbsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TbsAdapter.ViewHolder holder, int position) {
        ArticleModel model=mlist.get(position% mlist.size());


        TextView dd= holder.d;
        dd.setText(model.getExtra());
        TextView t=holder.disc;
        t.setText(model.getArticle());
        TextView s= holder.tag;
        s.setText(model.getBody());
        ImageView i = holder.img2;
        Picasso.with(mcontext).load(model.getBody()).resize(w+50, (int) (h+50)).centerCrop().into(i);

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textname,disc,tag,d;

        public ImageView img2;
        public ViewHolder(final View itemView) {
            super(itemView);
            d=itemView.findViewById(R.id.d);
            disc=itemView.findViewById(R.id.tbsd);
            img2=itemView.findViewById(R.id.img2);
            tag=itemView.findViewById(R.id.src);

            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater layoutInflater = (LayoutInflater) itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View customView = layoutInflater.inflate(R.layout.open_resp,null);
                    TextView resptitle=customView.findViewById(R.id.resptitle);
                    ImageView respimg= customView.findViewById(R.id.imgresp);
                    TextView respdesc= customView.findViewById(R.id.respdescription);
                    Button close= customView.findViewById(R.id.cloeresp);

                    respdesc.setText(d.getText());
                    resptitle.setText(disc.getText());
                    Picasso.with(mcontext).load(tag.getText().toString()).resize(w+50, (int) (h+50)).centerCrop().into(respimg);

                    popupWindow = new PopupWindow(customView, 1000, WindowManager.LayoutParams.WRAP_CONTENT);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(
                            android.graphics.Color.TRANSPARENT));

                    RelativeLayout relativeLayout = itemView.findViewById(R.id.rltbs);
                    popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

                    close.setOnClickListener(new View.OnClickListener() {
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
