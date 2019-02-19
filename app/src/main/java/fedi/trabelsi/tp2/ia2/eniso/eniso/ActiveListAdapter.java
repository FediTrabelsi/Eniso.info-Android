package fedi.trabelsi.tp2.ia2.eniso.eniso;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ActiveListAdapter extends ArrayAdapter<Active> {
    private static final String TAG = "PersonList";
    private Context context ;
    int mResource ;
    Bitmap mIcon_val;


    public ActiveListAdapter(@NonNull Context context, int resource, ArrayList<Active> objects) {
        super(context, resource, objects);
        this.context = context;
        mResource = resource;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        String userFullTitle = getItem(position).getUserFullTitle();
        String  iconURL = getItem(position).getIconURL();
        String  userTypeName = getItem(position).getUserTypeName();

        Active active = new Active(userFullTitle,iconURL,userTypeName);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource,parent,false);

        TextView name = (TextView) convertView.findViewById(R.id.usr_name);
        ImageView icon = (ImageView) convertView.findViewById(R.id.user_icon);
        TextView type = (TextView) convertView.findViewById(R.id.usr_groupe);

        Picasso.with(context).load(iconURL).error(R.drawable.ic_menu_share).into(icon);

        name.setText(userFullTitle);
        type.setText(userTypeName);
        icon.setImageURI(Uri.parse(iconURL));

        return  convertView ;



    }
}
