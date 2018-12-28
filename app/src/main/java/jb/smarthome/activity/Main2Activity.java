package jb.smarthome.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import jb.smarthome.R;


public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */



    @BindView(R.id.drawer_layout1)
    DrawerLayout drawer;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager mViewPager;
    @BindView(R.id.fragmentHeader)
    TextView fragmentHeaderTextView;


    //Auth
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
//        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorBurgerMenu));
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toggle.getDrawerArrowDrawable().setSpinEnabled(true);
        toolbar.setContentInsetsAbsolute(toolbar.getContentInsetRight(), 500);
        toggle.syncState();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int tabIconColor = ContextCompat.getColor(getBaseContext(), R.color.colorTabSelectedText);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                int tabIconColor = ContextCompat.getColor(getBaseContext(), R.color.colorTabText);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_dashboard_24px);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_message_24px);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_bar_chart_24px);
        //default header
        fragmentHeaderTextView.setText("Menu");
        //set color on selected menu
        int tabIconColor = ContextCompat.getColor(getBaseContext(), R.color.colorTabSelectedText);
        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0){
                    fragmentHeaderTextView.setText("Menu");
                }
                else if(i==1){
                    fragmentHeaderTextView.setText("Powiadomienia");
                }
                else{
                    fragmentHeaderTextView.setText("Statystyki");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        //TODO
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    public void singOut(View v){
        auth.signOut();
        finish();
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_alarm) {

        } else if (id == R.id.nav_sensor) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_temperature) {

        } else if (id == R.id.nav_light) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            switch(position){
                case 0 :
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    return dashboardFragment;
                case 1 :
                    NotificationFragement notificationFragement = new NotificationFragement();
                    return notificationFragement;
                case 2 :
                    StatisticsFragment statisticsFragment = new StatisticsFragment();
                    return statisticsFragment;

                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
