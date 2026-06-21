package kup.moemoetun.shwegrammaroffline.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageManager;

public class NavigationHandler {
    public static void openYouTubeChannel(Context context) {
        // Replace with your YouTube channel URL
        String channelUrl = "https://www.youtube.com/channel/UCpsZ6xw2m33IqktVJYQbhiw";
        // Check if the YouTube app is installed
        boolean isYouTubeAppInstalled = isAppInstalled(context);
        // Create an Intent to open the YouTube channel URL
        Intent intent;
        if (isYouTubeAppInstalled) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(channelUrl));
            intent.setPackage("com.google.android.youtube");
        } else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/channel/" + channelUrl));
        }
        context.startActivity(intent);
    }

    private static boolean isAppInstalled(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.youtube", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
