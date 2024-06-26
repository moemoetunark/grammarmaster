package kup.moemoetun.shwegrammaroffline.webview;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;

public class Daile_Web extends AppCompatActivity {

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_web_view);

        //Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        int intExtra = getIntent().getIntExtra("key",0);


        if (intExtra == 0) {
            webView.loadUrl("file:///android_asset/basic/dailylife/A Nice Place to Live.html");
        } else if (intExtra == 1) {
            webView.loadUrl("file:///android_asset/basic/dailylife/a-lost-button.html");
        }else if (intExtra == 2) {
            webView.loadUrl("file:///android_asset/basic/dailylife/a-new-house.html");
        }else if (intExtra == 3) {
            webView.loadUrl("file:///android_asset/basic/dailylife/borrowing-money.html");
        }else if (intExtra == 4) {
            webView.loadUrl("file:///android_asset/basic/dailylife/do you have a girlfriend.html");
        }else if (intExtra == 5) {
            webView.loadUrl("file:///android_asset/basic/dailylife/do-you-love-me.html");
        }else if (intExtra == 6) {
            webView.loadUrl("file:///android_asset/basic/dailylife/going-to-the-beach.html");
        }else if (intExtra == 7) {
            webView.loadUrl("file:///android_asset/basic/dailylife/happy-in-heaven.html");
        }else if (intExtra == 8) {
            webView.loadUrl("file:///android_asset/basic/dailylife/how-about-a-pizza.html");
        }else if (intExtra == 10) {
            webView.loadUrl("file:///android_asset/basic/dailylife/I Have a Honda.html");
        }else if (intExtra == 11) {
            webView.loadUrl("file:///android_asset/basic/dailylife/i-have-a-poodle.html");
        }else if (intExtra == 12) {
            webView.loadUrl("file:///android_asset/basic/dailylife/I-live-in-Pasadena.html");
        }else if (intExtra == 13) {
            webView.loadUrl("file:///android_asset/basic/dailylife/it's-too-hot.html");
        }else if (intExtra == 14) {
            webView.loadUrl("file:///android_asset/basic/dailylife/kitten-to-give-away.html");
        }else if (intExtra == 15) {
            webView.loadUrl("file:///android_asset/basic/dailylife/mother's-day.html");
        }else if (intExtra == 16) {
            webView.loadUrl("file:///android_asset/basic/dailylife/my-laptop-is-so-slow.html");
        }else if (intExtra == 17) {
            webView.loadUrl("file:///android_asset/basic/dailylife/my-wife-left-me.html");
        }else if (intExtra == 18) {
            webView.loadUrl("file:///android_asset/basic/dailylife/the-new-mattress.html");
        }else if (intExtra == 19) {
            webView.loadUrl("file:///android_asset/basic/dailylife/what's-on0-tv.html");
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
