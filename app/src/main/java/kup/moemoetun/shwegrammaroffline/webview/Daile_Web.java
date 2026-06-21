package kup.moemoetun.shwegrammaroffline.webview;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class Daile_Web extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daily Life");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int intExtra = getIntent().getIntExtra("key", 0);

        // ✅ Cleaner mapping instead of long if-else
        String[] pages = {
                "A Nice Place to Live.html",
                "a-lost-button.html",
                "a-new-house.html",
                "borrowing-money.html",
                "do you have a girlfriend.html",
                "do-you-love-me.html",
                "going-to-the-beach.html",
                "happy-in-heaven.html",
                "how-about-a-pizza.html",
                null,
                "I Have a Honda.html",
                "i-have-a-poodle.html",
                "I-live-in-Pasadena.html",
                "it's-too-hot.html",
                "kitten-to-give-away.html",
                "mother's-day.html",
                "my-laptop-is-so-slow.html",
                "my-wife-left-me.html",
                "the-new-mattress.html",
                "what's-on0-tv.html"
        };

        if (intExtra >= 0 && intExtra < pages.length && pages[intExtra] != null) {
            webView.loadUrl("file:///android_asset/basic/dailylife/" + pages[intExtra]);
        }

    }

    // Optional: toolbar back button
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