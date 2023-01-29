package kup.moemoetun.shwegrammaroffline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.webview.Daile_Web;
import kup.moemoetun.shwegrammaroffline.webview.Job_Web;

public class Job_Act extends AppCompatActivity {

    ListView listView;
    String [] titleArray = {"A bad boss","Become a teacher","Before you go to an interview","Hire me","I'm a baby sitter.",
            "I need a job","I want to do a job","Working for a hotel","What if you lose your job","Work is hard"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        listView = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_shopping, R.id.textView, titleArray);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent intent = new Intent(Job_Act.this, Job_Web.class);
                intent.putExtra("key", position);
                startActivity(intent);
            }
        });


    }
}
