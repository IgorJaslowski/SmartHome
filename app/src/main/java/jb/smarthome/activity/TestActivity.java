package jb.smarthome.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import jb.smarthome.R;

public class TestActivity extends Activity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent = new Intent();
            switch (item.getItemId()){
                case R.id.navigation_home:{
                    startActivity(new Intent(getApplicationContext(), TestActivity.class));
                    break;

                }
                case R.id.navigation_dashboard:{
                    startActivity(new Intent(getApplicationContext(), TestActivity2.class));
                    break;
                }
                case R.id.navigation_notification:{
                    startActivity(new Intent(getApplicationContext(), TestActivity3.class));
                    break;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
