package kup.moemoetun.shwegrammaroffline.activity;

import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.webview.GrammarWebView;
import kup.moemoetun.shwegrammaroffline.webview.Shopping_web;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShoppingAct extends AppCompatActivity {

    ListView listView;
    String [] titleArray = {"Bad Business","I like that shirt","Pants that fit","PC or Mac","" +
            "Poor Pocket","Sharpen the pencil","Shopping List","The 99 cent Store","To save the money"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        listView = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_shopping, R.id.textView, titleArray);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ShoppingAct.this, Shopping_web.class);
            intent.putExtra("key", position);
            startActivity(intent);
        });


    }
}
