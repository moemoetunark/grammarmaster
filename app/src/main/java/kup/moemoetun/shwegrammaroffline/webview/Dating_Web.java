package kup.moemoetun.shwegrammaroffline.webview;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;

public class Dating_Web extends AppCompatActivity {

    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/basic/dating/1-a-blind-date.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/basic/dating/2-let's-have-dinner.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/basic/dating/3-true-love.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/basic/dating/4-ask-her-out.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/basic/dating/5-a-night-by-himself.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/basic/dating/6-go-on-a-blind-date.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/basic/dating/7-one-date-only.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/basic/dating/8-blue-eyes.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/basic/dating/9-i-love-you-more-than-anything.html");
        }else if (intExtra == 9) {
            webView.loadUrl("file:///android_asset/basic/dating/10-an-old-man.html");
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
