package fedi.trabelsi.tp2.ia2.eniso.eniso.Grp_Inovation_Section;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.squareup.picasso.Picasso;

import java.util.Random;

import fedi.trabelsi.tp2.ia2.eniso.eniso.Login.Login;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Mon_Emploi.TimeTable;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Profile.StudentProfile;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Profile.TeacherProfile;
import fedi.trabelsi.tp2.ia2.eniso.eniso.R;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Showcase.Testing;
import fedi.trabelsi.tp2.ia2.eniso.eniso.TimeTableByGroup;
import fedi.trabelsi.tp2.ia2.eniso.eniso.TimeTableByTeacher;
import fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome.Welcome;


public class InnovationGroups extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Context mcontext;
    private BoomMenuButton boomMenuButton;
    private boolean init = false;
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    public PopupWindow popupWindow;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innovation_groups);
        mcontext=getApplicationContext();

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

                            case R.id.nav_profile :{
                                if (Login.usertype.equals("Etudiants")){
                                    Intent i= new Intent(mcontext,StudentProfile.class);
                                    startActivity(i);
                                }
                                else{
                                    Intent i= new Intent(mcontext,TeacherProfile.class);
                                    startActivity(i);
                                    break;
                                }

                                break;
                            }
                            case R.id.nav_wall : {
                                Intent i= new Intent(mcontext,Welcome.class);
                                startActivity(i);
                                break;
                            }

                            case R.id.nav_timetable : {
                                Intent i= new Intent(mcontext,TimeTable.class);
                                startActivity(i);
                                break;
                            }
                            case R.id.nav_grtimetable : {
                                Intent i= new Intent(mcontext,TimeTableByGroup.class);
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
                            case R.id.nav_teachertimetable : {
                                Intent i= new Intent(mcontext,TimeTableByTeacher.class);
                                startActivity(i);
                                break;
                            }
                            default: ;


                        }



                        mDrawerLayout.closeDrawers();



                        return true;
                    }
                });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_innovation_groups, menu);
        MenuItem title= menu.getItem(0);
        if (Login.usertype.equals("Etudiants")){
            title.setTitle("Créer une équipe");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            if (Login.usertype.equals("Etudiants")){

                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.create_new_team,null);
                close = customView.findViewById(R.id.annuler);
                popupWindow = new PopupWindow(customView,1000,1400);
                popupWindow.setFocusable(true);
                popupWindow.update();
                popupWindow.setBackgroundDrawable(new ColorDrawable(
                        android.graphics.Color.TRANSPARENT));


                popupWindow.showAtLocation(findViewById(R.id.rlview), Gravity.CENTER, 0, 0);

            }

            else {


                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.create_new_projetc, null);
                close = customView.findViewById(R.id.annuler);
                popupWindow = new PopupWindow(customView, 1000, 1700);
                popupWindow.setFocusable(true);
                popupWindow.update();
                popupWindow.setBackgroundDrawable(new ColorDrawable(
                        android.graphics.Color.TRANSPARENT));


                popupWindow.showAtLocation(findViewById(R.id.rlview), Gravity.CENTER, 0, 0);
            }
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();

                }
            });




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_innovation_groups, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     *
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 1:
                    return new ViewFrag();

                case 0:
                    return new AddFrag();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }


    }

}
