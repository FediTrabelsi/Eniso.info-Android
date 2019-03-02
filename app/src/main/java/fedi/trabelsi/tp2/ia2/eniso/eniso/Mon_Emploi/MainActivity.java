package fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fedi.trabelsi.tp2.ia2.eniso.eniso.GroupesList;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section.InnovationGroups;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.mFragments_Days.Jeudi_Frag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.mFragments_Days.Lundi_Frag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.mFragments_Days.Mardi_Frag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.mFragments_Days.Mercredi_Frag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.mFragments_Days.Samedi_Frag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.mFragments_Days.Vendredi_Frag;
import fedi.trabelsi.tp2.ia2.eniso.eniso.PageAdaptater.SectionPageAdapter;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase.Testing;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.Welcome;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionPageAdapter mSectionPageAdapter;
//
    private ViewPager mViewPager;
    public Context mcontext;
    public DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma2in);
        mcontext=getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG,"onCreate Starting");
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ImageView header= (ImageView)navigationView.getHeaderView(0).findViewById(R.id.profimg);
        TextView name=navigationView.getHeaderView(0).findViewById(R.id.titl);
        name.setText(Login.username);
        TextView desc=navigationView.getHeaderView(0).findViewById(R.id.desc);
        desc.setText(Login.classe);
        Picasso.with(getApplicationContext()).load(Login.iconurl).resize(250,250).error(R.drawable.calendar).into(header);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();

                        switch (id){
                            case R.id.nav_wall : {
                                Intent i= new Intent(mcontext,Welcome.class);
                                startActivity(i);
                                break;
                            }

                            case R.id.nav_timetable : {
                                Intent i= new Intent(mcontext,My_TimeTable.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_eval : {
                                Intent i= new Intent(mcontext,GroupesList.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_groups : {
                                Intent i= new Intent(mcontext,InnovationGroups.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_calendar : {
                                Intent i= new Intent(mcontext,Testing.class);
                                startActivity(i);
                                break;
                            }


                        }


                        menuItem.setChecked(true);

                        mDrawerLayout.closeDrawers();



                        return true;
                    }
                });
        //getSupportActionBar().setTitle("Mon Emplois Temps");






    }

    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Lundi_Frag() , "LUN");
        adapter.addFragment(new Mardi_Frag() , "MAR");
        adapter.addFragment(new Mercredi_Frag() , "MER");
        adapter.addFragment(new Jeudi_Frag() , "JEU");
        adapter.addFragment(new Vendredi_Frag() , "VEN");
        adapter.addFragment(new Samedi_Frag() , "SAM");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
