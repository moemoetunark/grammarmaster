package kup.moemoetun.shwegrammaroffline.webview;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class Job_Web extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Job");

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int intExtra = getIntent().getIntExtra("key", 0);

        // ✅ Cleaner mapping
        String[] pages = {
                "a-bad-boss.html",
                "become-a-teacher.html",
                "before-you-go-to-interview.html",
                "hire-me.html",
                "i-need-ajob.html",
                "i'm-a-babysitter.html",
                "light-my-fire-i-want-to-do-a-job.html",
                "still-working-a-hotel-job.html",
                "what-if-you-lost-your-job.html",
                "work-is-hard.html"
        };

        if (intExtra >= 0 && intExtra < pages.length) {
            webView.loadUrl("file:///android_asset/basic/job/" + pages[intExtra]);
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