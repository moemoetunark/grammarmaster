package kup.moemoetun.shwegrammaroffline.constant;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AppConstant {
    private static final String PREF_COIN_BALANCE = "coin_balance";
    private static SharedPreferences sharedPreferences;

    // Ad Unit IDs (Default values)
    public static String BANNER_UNDER_WEBVIEW = "ca-app-pub-4137439985376631/9576350619";
    public static String OFFLINE_INTERSTITIALS = "ca-app-pub-4137439985376631/2591615182";
    public static String OFFLINE_ONBACK = "ca-app-pub-4137439985376631/2591615182";
    public static String EXIT_BANNER = "ca-app-pub-4137439985376631/3323015026";
    public static String QUIZ_COMPLETE = "ca-app-pub-4137439985376631/7124327531";
    public static String HOME_BANNER = "ca-app-pub-4137439985376631/9576350619";
    public static String APP_OPEN = "ca-app-pub-4137439985376631/1650742998";

    // Remote Config Keys
    public static final String KEY_BANNER_UNDER_WEBVIEW = "banner_under_webview";
    public static final String KEY_OFFLINE_INTERSTITIALS = "offfline_interstitials";
    public static final String KEY_OFFLINE_ONBACK = "offline_onback";
    public static final String KEY_EXIT_BANNER = "exit_banner";
    public static final String KEY_QUIZ_COMPLETE = "quiz_complete";
    public static final String KEY_HOME_BANNER = "homebanner";
    public static final String KEY_APP_OPEN = "app_open";
    public static final String KEY_IS_AD_ON = "isAdOn";
    public static final String KEY_AD_FREQUENCY = "ad_frequency";

    public static boolean isAdOn = true;
    public static int adFrequency = 3;

    // Observer pattern
    private static List<CoinBalanceObserver> observers = new ArrayList<>();

    public static void initialize(Context context) {
        sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    }

    public static int getCoinBalance() {
        return sharedPreferences.getInt(PREF_COIN_BALANCE, 0);
    }

    public static void setCoinBalance(int coinBalance) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_COIN_BALANCE, coinBalance);
        editor.apply();

        // Notify all registered observers about the balance change
        for (CoinBalanceObserver observer : observers) {
            observer.onCoinBalanceChanged(coinBalance);
        }
    }

    // Observer pattern
    public static void registerCoinBalanceObserver(CoinBalanceObserver observer) {
        observers.add(observer);
    }

    public static void unregisterCoinBalanceObserver(CoinBalanceObserver observer) {
        observers.remove(observer);
    }

    // Observer pattern interface
    public interface CoinBalanceObserver {
        void onCoinBalanceChanged(int newBalance);
    }
}
