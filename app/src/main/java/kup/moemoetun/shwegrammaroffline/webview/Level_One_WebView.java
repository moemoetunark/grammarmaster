package kup.moemoetun.shwegrammaroffline.webview;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;

public class Level_One_WebView extends AppCompatActivity {

    public WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);


        //mAdView = findViewById(R.id.adView);
       // mAdView.loadAd(adRequest);

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/level1/1-morning.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/level1/2-the-first-day-of-school.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/level1/3-water-on-the-floor.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/level1/4-the-babysitting.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/level1/5-a-doctor.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/level1/6-twin.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/level1/7-reading.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/level1/8-reuined-by-the-rain.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/level1/9-banana-nut-muffin.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/level1/10-the-park.html");
        }else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/level1/11-the-fruit-shop.html");
        }else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/level1/12-a-new-shirt.html");
        }

        else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/level1/13-picking-the-color-for-the-house.html");
        }else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/level1/14-a-beautiful-garden.html");
        }else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/level1/15-a-substitute-teacher.html");
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
