package kup.moemoetun.shwegrammaroffline.webview;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.WindowMetrics;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;

public class Job_Web extends AppCompatActivity {

    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/basic/job/a-bad-boss.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/basic/job/become-a-teacher.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/basic/job/before-you-go-to-interview.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/basic/job/hire-me.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/basic/job/i-need-ajob.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/basic/job/i'm-a-babysitter.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/basic/job/light-my-fire-i-want-to-do-a-job.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/basic/job/still-working-a-hotel-job.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/basic/job/what-if-you-lost-your-job.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/basic/job/work-is-hard.html");
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
