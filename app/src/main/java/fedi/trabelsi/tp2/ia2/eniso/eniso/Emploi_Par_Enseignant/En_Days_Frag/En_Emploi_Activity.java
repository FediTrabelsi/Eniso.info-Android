package fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Days_Frag.En_Days.En_Jeudi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Days_Frag.En_Days.En_Lundi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Days_Frag.En_Days.En_Mardi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Days_Frag.En_Days.En_Mercredi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Days_Frag.En_Days.En_Samedi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Emploi_Par_Enseignant.En_Days_Frag.En_Days_Frag.En_Days.En_Vendredi;
import fedi.trabelsi.tp2.ia2.eniso.eniso.PageAdaptater.SectionPageAdapter;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;

public class En_Emploi_Activity extends AppCompatActivity {

    private static final String TAG = "En_Activity";

    private SectionPageAdapter En_SectionPageAdapter;

    public static int id ;
    public  static String name;

    private ViewPager mViewPager;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en__emploi_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.en_toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG,"onCreate Starting");
        En_SectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.en_container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.en_tabs);



        tabLayout.setupWithViewPager(mViewPager);

        Bundle extra = getIntent().getExtras();
        id = extra.getInt("id");
        name = extra.getString("name");
        getSupportActionBar().setTitle("Emplois Par Enseignant : "+name);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new En_Lundi() , "LUN");
        adapter.addFragment(new En_Mardi() , "MAR");
        adapter.addFragment(new En_Mercredi() , "MER");
        adapter.addFragment(new En_Jeudi(), "JEU");
        adapter.addFragment(new En_Vendredi() , "VEN");
        adapter.addFragment(new En_Samedi() , "SAM");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome,menu);
        //menu.add(0,3,0,"email");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

}
