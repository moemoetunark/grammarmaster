package kup.moemoetun.shwegrammaroffline.webview;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;

public class CommonWebViewActivity extends AppCompatActivity {

    public static final String EXTRA_FOLDER = "folder";
    public static final String EXTRA_KEY = "key";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        
        String folder = getIntent().getStringExtra(EXTRA_FOLDER);
        if (folder != null) {
            getSupportActionBar().setTitle(folder.substring(0, 1).toUpperCase() + folder.substring(1));
        }

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        int key = getIntent().getIntExtra(EXTRA_KEY, 0);

        String page = getPage(folder, key);

        if (page != null) {
            webView.loadUrl("file:///android_asset/" + folder + "/" + page);
        }

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (webView.canGoBack()) {
                            webView.goBack();
                        } else {
                            setEnabled(false);
                            getOnBackPressedDispatcher().onBackPressed();
                        }
                    }
                });
    }

    private String getPage(String folder, int key) {

        if (folder == null) return null;

        switch (folder) {

            case "level1":
                return new String[]{
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
                }[key];

            case "level3":
                return new String[]{
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
                }[key];

            case "job":
                return new String[]{
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
                }[key];

            case "shopping":
                return new String[]{
                        "bad-business.html",
                        "i-like-that-shirt.html",
                        "pants-that-fit.html",
                        "pc-or-mac.html",
                        "poor-pocket.html",
                        "sharpen-the-pencil.html",
                        "shopping-list.html",
                        "the-99-cents-store.html",
                        "to-save-the-money.html"
                }[key];

            case "dating":
                return new String[]{
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
                }[key];
        }

        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
