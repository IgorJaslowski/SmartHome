package jb.smarthome.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import jb.smarthome.R;
import jb.smarthome.RetrofitClientInstance;
import jb.smarthome.adapter.NotificationAdapter;
import jb.smarthome.adapter.TemperatureAdapter;
import jb.smarthome.api.model.Notification;
import jb.smarthome.api.model.Temperature;
import jb.smarthome.api.model.TemperatureResponse;
import jb.smarthome.api.service.SensorService;
import jb.smarthome.api.service.TemperatureService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    @BindView(R.id.nav_view)
    NavigationView nav;


    //Auth
    FirebaseAuth auth;
    FirebaseUser user;

    String gasResponse = "0";
    String fireResponse = "0";
    boolean motionResponse = false;
    Thread sensorThread;
    Date date;
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    String formattedDate;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Powiadomienia");
    Map notify = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        nav.setNavigationItemSelectedListener(this);
        nav.bringToFront();
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
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
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
                if (i == 0) {
                    fragmentHeaderTextView.setText("Menu");
                } else if (i == 1) {
                    fragmentHeaderTextView.setText("Powiadomienia");

                } else {
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


        sensorThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    gasSensor();

                    if (gasResponse.equals("1")) {
                        System.out.println("Wykryto gaz" + gasResponse);
                        date = Calendar.getInstance().getTime();
                        formattedDate = df.format(date);
                        notify.put(formattedDate, new ArrayList<String>(Arrays.asList("Wykryto GAZ", "warning")));
                        myRef.updateChildren(notify);


                        try {
                            sensorThread.sleep(15000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        sensorThread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        sensorThread.start();

    }

    private void gasSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<String> call = service.gasSensor();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                gasResponse = response.body();
                if (gasResponse == null) {
                    gasResponse = "0";
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                gasResponse = "0";
            }
        });
    }

    private void fireSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<String> call = service.fireSensor();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                fireResponse = response.body();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                fireResponse = "0";
            }
        });
    }

    private void motionSensor() {
        SensorService service = RetrofitClientInstance.getRetrofitInstance().create(SensorService.class);
        Call<Boolean> call = service.motionSensor();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                motionResponse = response.body();

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                motionResponse = false;
            }
        });
    }


    public void singOut() {
        auth.signOut();
        finish();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d("Hello", "called navigation");
        int id = menuItem.getItemId();


        menuItem.setChecked(true);
        drawer.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_alarm:
                startActivity(new Intent(this, AlarmActivity.class));
                break;
            case R.id.nav_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.nav_light:
                startActivity(new Intent(this, LightActivity.class));
                break;
            case R.id.nav_sensor:
//                Intent sensorIntent = new Intent(getApplicationContext(),SensorActivity.class);
//                sensorIntent.putExtra("gas",gasResponse);
//                startActivity(sensorIntent);
                break;
            case R.id.nav_temperature:
                startActivity(new Intent(this, TemperatureActivity.class));
                break;
            case R.id.logoutBtn:
                singOut();
                break;
            default:
                drawer.closeDrawer(GravityCompat.START);
                break;

        }
        return false;
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


            switch (position) {
                case 0:
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    return dashboardFragment;
                case 1:
                    NotificationFragement notificationFragement = new NotificationFragement();
                    return notificationFragement;
                case 2:
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

