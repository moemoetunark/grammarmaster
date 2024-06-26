package kup.moemoetun.shwegrammaroffline.webview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kup.moemoetun.shwegrammaroffline.R;

public class Level_Three_WebView extends AppCompatActivity {

    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);


        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/level3/1-meeting-the-guys.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/level3/2-grandma-sweater.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/level3/3-ugly--sister.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/level3/4-i-know-how-to-snowboard.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/level3/5-all-you-can-eat.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/level3/6-the-pet-chicken.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/level3/7-the-last-cigarette.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/level3/8-back-to-school.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/level3/9-cat-lovers.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/level3/10-losing-weight.html");
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
