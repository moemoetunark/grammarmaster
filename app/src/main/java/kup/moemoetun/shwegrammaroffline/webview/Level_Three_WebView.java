package kup.moemoetun.shwegrammaroffline.webview;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class Level_Three_WebView extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Listening Level Three");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int intExtra = getIntent().getIntExtra("key", 0);

        // ✅ Cleaner mapping instead of if-else
        String[] pages = {
                "1-meeting-the-guys.html",
                "2-grandma-sweater.html",
                "3-ugly--sister.html",
                "4-i-know-how-to-snowboard.html",
                "5-all-you-can-eat.html",
                "6-the-pet-chicken.html",
                "7-the-last-cigarette.html",
                "8-back-to-school.html",
                "9-cat-lovers.html",
                "10-losing-weight.html"
        };

        if (intExtra >= 0 && intExtra < pages.length) {
            webView.loadUrl("file:///android_asset/level3/" + pages[intExtra]);
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