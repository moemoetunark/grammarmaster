package kup.moemoetun.shwegrammaroffline.webview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;

public class Level_Two_WebView extends AppCompatActivity {

    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);
        webView = (WebView)findViewById(R.id.webView);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/level2/1-getting-ready-for-work.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/level2/2-going-to-sleep.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/level2/3-walking-the-dog.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/level2/4-lemonade-on-hot-day.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/level2/5-coffee-on-a-cold-night.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/level2/6-pick-up-little-sister.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/level2/7-Jim-walked-Nancy-Home.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/level2/8-making-a-sandwich.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/level2/9-john-love-to-read-books.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/level2/10-sam-loves-watching-television.html");
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
