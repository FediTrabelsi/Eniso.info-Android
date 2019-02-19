package fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class FeacherAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Feacher> mlist;

    public FeacherAdapter(Context c,ArrayList<Feacher> list){
        context=c;
        mlist=list;
    }



    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Feacher feacher= mlist.get(position);
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.feachers_structure, null);

            final ImageView image = (ImageView) convertView.findViewById(R.id.img);
            final TextView name = (TextView) convertView.findViewById(R.id.text);


            final ViewHolder viewHolder = new ViewHolder(name, image);
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.name.setText(feacher.getText());




        //Picasso.with(context).load(feacher.getSrcimg()).fit().centerCrop().into(viewHolder.image);
        Picasso.with(context).load(feacher.getSrcimg()).resize(parent.getWidth(), (int) (0.5*parent.getHeight())).centerCrop().into(viewHolder.image);
        return convertView;
    }

    private class ViewHolder {
        private final TextView name;

        private final ImageView image;


        public ViewHolder(TextView nameTextView, ImageView imageViewCoverArt) {
            this.name = nameTextView;

            this.image = imageViewCoverArt;

        }
    }

}
