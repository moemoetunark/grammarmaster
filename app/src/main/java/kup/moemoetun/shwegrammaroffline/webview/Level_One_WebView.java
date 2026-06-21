package kup.moemoetun.shwegrammaroffline.webview;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class Level_One_WebView extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Listening Level One");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int intExtra = getIntent().getIntExtra("key", 0);

        // ✅ Cleaner mapping
        String[] pages = {
                "1-morning.html",
                "2-the-first-day-of-school.html",
                "3-water-on-the-floor.html",
                "4-the-babysitting.html",
                "5-a-doctor.html",
                "6-twin.html",
                "7-reading.html",
                "8-reuined-by-the-rain.html",
                "9-banana-nut-muffin.html",
                "10-the-park.html",
                "11-the-fruit-shop.html",
                "12-a-new-shirt.html",
                "13-picking-the-color-for-the-house.html",
                "14-a-beautiful-garden.html",
                "15-a-substitute-teacher.html"
        };

        if (intExtra >= 0 && intExtra < pages.length) {
            webView.loadUrl("file:///android_asset/level1/" + pages[intExtra]);
        }

        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}