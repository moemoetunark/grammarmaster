package kup.moemoetun.shwegrammaroffline.webview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kup.moemoetun.shwegrammaroffline.R;

public class Level_Four_WebView extends AppCompatActivity {

    public WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        //mAdView = findViewById(R.id.adView);
        //mAdView.loadAd(adRequest);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/level4/1-the-first-day-at-the-job.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/level4/2-the-sale-associate.html");
        } else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/level4/3-watching-moview.html");
        } else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/level4/4-no-phone.html");
        } else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/level4/5-bob-lost-50-pounds.html");
        } else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/level4/6-instant-noodle.html");
        } else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/level4/7-a-generous-woman.html");
        } else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/level4/8-mom-doesn't-like-john.html");
        } else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/level4/9-an-old-friend.html");
        } else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/level4/10-she-loves-walking.html");
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void onDestroy() {
        try {
            this.webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}
