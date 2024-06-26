package kup.moemoetun.shwegrammaroffline.webview;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;

public class Shopping_web extends AppCompatActivity {

    public WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);


        //mAdView = findViewById(R.id.adView);
        //mAdView.loadAd(adRequest);

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);

        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/basic/shopping/bad-business.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/basic/shopping/i-like-that-shirt.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/basic/shopping/pants-that-fit.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/basic/shopping/pc-or-mac.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/basic/shopping/poor-pocket.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/basic/shopping/sharpen-the-pencil.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/basic/shopping/shopping-list.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/basic/shopping/the-99-cents-store.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/basic/shopping/to-save-the-money.html");
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
