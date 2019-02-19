package fedi.trabelsi.tp2.ia2.eniso.eniso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase.Showcase;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase.Testing;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.Welcome;


import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private EditText id;
    private EditText pass;
    private Button btn,next;
    String user,password;
    Intent intent;
    private Button btn2;

    private TextView mTextViewResult;
    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    private RequestQueue mQueue;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);

         mQueue = Volley.newRequestQueue( context );




        btn = (Button) findViewById(R.id.button);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);

        bgapp.animate().translationY(-2100).setDuration(1100).setStartDelay(1100);
        clover.animate().alpha(0).setDuration(1100).setStartDelay(1100);
        textsplash.animate().translationY(140).alpha(0).setDuration(1100).setStartDelay(600);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);


        id = (EditText) findViewById(R.id.id);
        pass = (EditText) findViewById(R.id.pass);
        mTextViewResult = (TextView) findViewById(R.id.tv);




         btn2 =(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Passto();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = id.getText().toString();
                password = pass.getText().toString();



                 Login.login(user, password, mQueue, mTextViewResult,btn2,context);
                mTextViewResult.setText("");

            }
        });




    }

    public void Passto(){

    }


}
