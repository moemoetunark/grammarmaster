package kup.moemoetun.shwegrammaroffline;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;
import kup.moemoetun.shwegrammaroffline.utility.GoogleMobileAdsConsentManager;

import java.util.HashMap;

import kup.moemoetun.shwegrammaroffline.adapter.ViewPagerAdapter;

import kup.moemoetun.shwegrammaroffline.HtmlActivity;
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;
import kup.moemoetun.shwegrammaroffline.utility.GoogleMobileAdsConsentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private AlertDialog exitDialog;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    FirebaseRemoteConfig mFirebaseRemoteConfig =
            FirebaseRemoteConfig.getInstance();

    GoogleMobileAdsConsentManager googleMobileAdsConsentManager;

    private final String[] pageTitle = {
            "Basic Grammar",
            "Basic Speaking",
            "Listening Level 1",
            "Listening Level 2",
            "Listening Level 3",
            "Listening Level 4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(
                        getApplicationContext());

        AppConstant.initialize(getApplicationContext());

        setupRemoteConfig();

        viewPager = findViewById(R.id.viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close
                );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setupTabs();

        navigationView = findViewById(R.id.nav_view);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        ViewPagerAdapter pagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(
                        findViewById(R.id.tab_layout)));

        loadDialog();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
    @Override
    public void handleOnBackPressed() {

        if (drawer != null
                && drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else if (exitDialog != null) {

            exitDialog.show();

        } else {

            finish(); // same effect as super.onBackPressed()
        }
    }
};

getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void setupRemoteConfig() {

    HashMap<String, Object> defaultRate = new HashMap<>();

    defaultRate.put("new_version_found", getVersionCode());
    defaultRate.put(AppConstant.KEY_BANNER_UNDER_WEBVIEW, AppConstant.BANNER_UNDER_WEBVIEW);
    defaultRate.put(AppConstant.KEY_OFFLINE_INTERSTITIALS, AppConstant.OFFLINE_INTERSTITIALS);
    defaultRate.put(AppConstant.KEY_OFFLINE_ONBACK, AppConstant.OFFLINE_ONBACK);
    defaultRate.put(AppConstant.KEY_EXIT_BANNER, AppConstant.EXIT_BANNER);
    defaultRate.put(AppConstant.KEY_QUIZ_COMPLETE, AppConstant.QUIZ_COMPLETE);
    defaultRate.put(AppConstant.KEY_HOME_BANNER, AppConstant.HOME_BANNER);
    defaultRate.put(AppConstant.KEY_APP_OPEN, AppConstant.APP_OPEN);
    defaultRate.put(AppConstant.KEY_IS_AD_ON, AppConstant.isAdOn);

    FirebaseRemoteConfigSettings configSettings =
            new FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(0)
                    .build();

    mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

    mFirebaseRemoteConfig.setDefaultsAsync(defaultRate);

    mFirebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener(this,
                    new OnCompleteListener<Boolean>() {

                        @Override
                        public void onComplete(
                                @NonNull Task<Boolean> task) {

                            if (task.isSuccessful()) {

                                AppConstant.BANNER_UNDER_WEBVIEW = mFirebaseRemoteConfig.getString(AppConstant.KEY_BANNER_UNDER_WEBVIEW);
                                AppConstant.OFFLINE_INTERSTITIALS = mFirebaseRemoteConfig.getString(AppConstant.KEY_OFFLINE_INTERSTITIALS);
                                AppConstant.OFFLINE_ONBACK = mFirebaseRemoteConfig.getString(AppConstant.KEY_OFFLINE_ONBACK);
                                AppConstant.EXIT_BANNER = mFirebaseRemoteConfig.getString(AppConstant.KEY_EXIT_BANNER);
                                AppConstant.QUIZ_COMPLETE = mFirebaseRemoteConfig.getString(AppConstant.KEY_QUIZ_COMPLETE);
                                AppConstant.HOME_BANNER = mFirebaseRemoteConfig.getString(AppConstant.KEY_HOME_BANNER);
                                AppConstant.APP_OPEN = mFirebaseRemoteConfig.getString(AppConstant.KEY_APP_OPEN);
                                AppConstant.isAdOn = mFirebaseRemoteConfig.getBoolean(AppConstant.KEY_IS_AD_ON);
                                AppConstant.adFrequency = (int) mFirebaseRemoteConfig.getLong(AppConstant.KEY_AD_FREQUENCY);

                                long newVersionCode =
                                        mFirebaseRemoteConfig
                                                .getLong(
                                                        "new_version_found"
                                                );

                                Log.d(
                                        "VERSION",
                                        "Current: "
                                                + getVersionCode()
                                );

                                Log.d(
                                        "VERSION",
                                        "Firebase: "
                                                + newVersionCode
                                );

                                if (newVersionCode
                                        > getVersionCode()) {

                                    upDateApp();
                                }

                                loadBannerAd();
                            } else {
                                loadBannerAd(); // Load with defaults if fetch fails
                            }
                        }
                    });
    }

    private void loadBannerAd() {
        FrameLayout adContainer = findViewById(R.id.ad_container);
        if (adContainer == null) return;
        adContainer.removeAllViews();

        if (!AppConstant.isAdOn) return;

        AdView adView = new AdView(this);
        adView.setAdUnitId(AppConstant.HOME_BANNER);

        // Adaptive Banner logic
        com.google.android.gms.ads.AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        adContainer.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private com.google.android.gms.ads.AdSize getAdSize() {
        // Determine the screen width (less padding) to use for the ad width.
        android.view.Display display = getWindowManager().getDefaultDisplay();
        android.util.DisplayMetrics outMetrics = new android.util.DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Get adaptive ad size and return for setting on the ad view.
        return com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
    private void setupTabs() {

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        for (String title : pageTitle) {
            tabLayout.addTab(
                    tabLayout.newTab().setText(title)
            );
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {

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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.download) {

            openUrl("https://englishlearning4mm.blogspot.com/");

        } else if (id == R.id.rating) {

            openUrl("https://play.google.com/store/apps/details?id="
                    + getPackageName());

        } else if (id == R.id.share) {

            shareApp();

        } else if (id == R.id.youtube) {

            openUrl("https://youtube.com/@mmtdev");

        } else if (id == R.id.go) {
            startActivity(new Intent(this, HtmlActivity.class));

        } else if (id == R.id.close) {

            if (exitDialog != null) {
                exitDialog.show();
            }
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_menu, menu);

        MenuItem moreMenu = menu.findItem(R.id.action_more);

        if (moreMenu != null) {
            moreMenu.setVisible(
                    googleMobileAdsConsentManager
                            .isPrivacyOptionsRequired());
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_more) {

            View anchor = findViewById(R.id.action_more);

            if (anchor == null) {
                return super.onOptionsItemSelected(item);
            }

            PopupMenu popup = new PopupMenu(this, anchor);

            popup.getMenuInflater()
                    .inflate(R.menu.popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(menuItem -> {

                if (menuItem.getItemId()
                        == R.id.privacy_settings) {

                    googleMobileAdsConsentManager
                            .showPrivacyOptionsForm(
                                    this,
                                    formError -> {

                                        if (formError != null) {

                                            Toast.makeText(
                                                    this,
                                                    formError.getMessage(),
                                                    Toast.LENGTH_SHORT
                                            ).show();
                                        }
                                    });

                    return true;
                }

                return false;
            });

            popup.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void upDateApp() {

        new AlertDialog.Builder(this)
                .setTitle("Update NOW!")
                .setIcon(R.mipmap.graduate)
                .setCancelable(true)
                .setMessage("Newer Version Available!")
                .setPositiveButton("YES!",
                        (dialogInterface, i) -> {

                            Intent intent =
                                    new Intent(Intent.ACTION_VIEW);

                            intent.setData(Uri.parse(
                                    "https://play.google.com/store/apps/details?id="
                                            + getPackageName()));

                            startActivity(intent);
                        })

                .setNegativeButton("NO",
                        (dialogInterface, i)
                                -> dialogInterface.dismiss())

                .show();
    }

    public int getVersionCode() {

    try {

        PackageInfo packageInfo;

        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.TIRAMISU) {

            packageInfo = getPackageManager()
                    .getPackageInfo(
                            getPackageName(),
                            PackageManager.PackageInfoFlags.of(0)
                    );

        } else {

            packageInfo = getPackageManager()
                    .getPackageInfo(
                            getPackageName(),
                            0
                    );
        }

        return (int)
                packageInfo.getLongVersionCode();

    } catch (Exception e) {

        Log.e(
                "MainActivity",
                "Version Error: "
                        + e.getMessage()
        );

        return 1;
    }
}

   /*
@Override
public void onBackPressed() {

    if (drawer != null
            && drawer.isDrawerOpen(GravityCompat.START)) {

        drawer.closeDrawer(GravityCompat.START);

    } else if (exitDialog != null) {

        exitDialog.show();

    } else {

        super.onBackPressed();
    }
}
*/
    public void loadDialog() {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(
                        this,
                        R.style.fullScreenDialog
                );

        LayoutInflater inflater = getLayoutInflater();

        View dialogView =
                inflater.inflate(
                        R.layout.full_screen_dialog,
                        null
                );

        builder.setView(dialogView);

        exitDialog = builder.create();

        // Access views after creating the dialog to set listeners
        AdView mAdView =
                dialogView.findViewById(R.id.adView);

        if (mAdView != null) {
            mAdView.setAdUnitId(AppConstant.EXIT_BANNER);
            AdRequest adRequest =
                    new AdRequest.Builder().build();

            mAdView.loadAd(adRequest);
        }

        Button positiveButton =
                dialogView.findViewById(R.id.positiveButton);

        Button negativeButton =
                dialogView.findViewById(R.id.negativeButton);

        positiveButton.setOnClickListener(v -> finish());

        negativeButton.setOnClickListener(v -> {

            if (exitDialog != null) {
                exitDialog.dismiss();
            }
        });
    }

    private void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    private void openUrl(String url) {

        try {

            CustomTabsIntent.Builder builder =
                    new CustomTabsIntent.Builder();

            CustomTabsIntent customTabsIntent =
                    builder.build();

            customTabsIntent.launchUrl(
                    this,
                    Uri.parse(url)
            );

        } catch (Exception e) {

            Intent intent =
                    new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));

            startActivity(intent);
        }
    }
}
