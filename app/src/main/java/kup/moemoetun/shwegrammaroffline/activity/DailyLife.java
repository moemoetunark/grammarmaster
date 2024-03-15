package kup.moemoetun.shwegrammaroffline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.webview.Daile_Web;
public class DailyLife extends AppCompatActivity {

    ListView listView;
    String [] titleArray = {"A Nice Place to live",
            "A lost Button","A new House",
            "Borrowing Money",
            "Do you have a girlfriend?",
            "Do you love me?",
            "Going to the beach",
            "Happy in heaven",
            "How about a Pizza?",
            "I have a honda",
             "I have a poodle",
            "I live in Pasadena",
            "It is too hot",
            "Kitten to give away",
            "Mother's Day","My latop is so slow",
             "My wife left me",
            "The new mattress","What is on TV?"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        listView = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_shopping, R.id.textView, titleArray);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(DailyLife.this, Daile_Web.class);
            intent.putExtra("key", position);
            startActivity(intent);
        });


    }
}
