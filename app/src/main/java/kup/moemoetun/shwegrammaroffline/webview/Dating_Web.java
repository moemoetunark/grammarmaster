package kup.moemoetun.shwegrammaroffline.webview;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class Dating_Web extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Speaking for Dating");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int intExtra = getIntent().getIntExtra("key", 0);

        // ✅ Cleaner approach instead of if-else
        String[] pages = {
                "1-a-blind-date.html",
                "2-let's-have-dinner.html",
                "3-true-love.html",
                "4-ask-her-out.html",
                "5-a-night-by-himself.html",
                "6-go-on-a-blind-date.html",
                "7-one-date-only.html",
                "8-blue-eyes.html",
                "9-i-love-you-more-than-anything.html",
                "10-an-old-man.html"
        };

        if (intExtra >= 0 && intExtra < pages.length) {
            webView.loadUrl("file:///android_asset/basic/dating/" + pages[intExtra]);
        }

       
    }

    // Toolbar back button (optional)
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