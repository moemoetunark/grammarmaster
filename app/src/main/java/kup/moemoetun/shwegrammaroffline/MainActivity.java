package kup.moemoetun.shwegrammaroffline;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;

import kup.moemoetun.shwegrammaroffline.ui.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Basic Grammar", "Basic Speaking", "Listening Level 1","Listening Level 2",
            " Listening Level 3","Listening Level 4"};
    private FirebaseRemoteConfig mFirebaseRemoteConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HashMap<String, Object> defaultRate = new HashMap<>();
        defaultRate.put("new_version_code",String.valueOf(getVersionCode()));

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setDefaultsAsync(defaultRate);
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if(task.isSuccessful()){
                    final String new_version_code = mFirebaseRemoteConfig.getString("new_version_found");
                    if(Integer.parseInt(new_version_code) >getVersionCode())upDateApp("kup.moemoetun.shwegrammaroffline",new_version_code);
                }
            }
        });



        viewPager = findViewById(R.id.view_pager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i <6; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //change ViewPager page when tab selected

    }


    public void upDateApp(final String appPackageName, String versionFromRemoteconfig){
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Update NOW!")
                .setIcon(R.mipmap.graduate)
                .setCancelable(false)
                .setMessage("Newer Version Available!").setPositiveButton("YES!", (dialogInterface, i) -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=kup.moemoetun.shwegrammaroffline"));
                    startActivity(intent);
                }).setNegativeButton("NO!", (dialogInterface, i) -> {
                    Toast.makeText(getApplicationContext(),"Please, update soon.",Toast.LENGTH_SHORT).show();
                    //finish();
                }).show();
    }

    public int getVersionCode(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getCallingPackage(),0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("mylog","NameNotFoundExcepton"+e.getMessage());
        }
        return packageInfo != null ? packageInfo.versionCode : 32;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.download) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moemoetun.me/download-free-myanmarbook.html"));
            startActivity(browserIntent);
        }
        if (id == R.id.rating) {
           Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setData(Uri.parse(
                   "https://play.google.com/store/apps/details?id=kup.moemoetun.shwegrammaroffline"));
           intent.setPackage("com.android.vending");
           startActivity(intent);
       }
            //viewPager.setCurrentItem(0);
       // } else if (id == R.id.fr2) {
            //viewPager.setCurrentItem(1);
       // } else if (id == R.id.fr3) {
            //viewPager.setCurrentItem(2);
        //}
        if (id == R.id.go) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moemoetun.me/privacy-policy-2/"));
            startActivity(browserIntent);
        } else if (id == R.id.close) {
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
