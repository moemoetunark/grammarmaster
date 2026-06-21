package kup.moemoetun.shwegrammaroffline.webview;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class Level_Four_WebView extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Listening Level Four");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int intExtra = getIntent().getIntExtra("key", 0);

        // ✅ Cleaner mapping
        String[] pages = {
                "1-the-first-day-at-the-job.html",
                "2-the-sale-associate.html",
                "3-watching-moview.html",
                "4-no-phone.html",
                "5-bob-lost-50-pounds.html",
                "6-instant-noodle.html",
                "7-a-generous-woman.html",
                "8-mom-doesn't-like-john.html",
                "9-an-old-friend.html",
                "10-she-loves-walking.html"
        };

        if (intExtra >= 0 && intExtra < pages.length) {
            webView.loadUrl("file:///android_asset/level4/" + pages[intExtra]);
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