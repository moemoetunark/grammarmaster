package kup.moemoetun.shwegrammaroffline.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kup.moemoetun.shwegrammaroffline.R;


public class QuizReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_review);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.statusText);
        TextView textView1 = findViewById(R.id.chartCenterText);

        Button statusButton = findViewById(R.id.btnStatus);
        statusButton.setBackgroundResource(R.drawable.tranperrant_bg);


        int correctCount = getIntent().getIntExtra("correctCount", 0);

        int incorrectCount = getIntent().getIntExtra("incorrectCount", 0);
        String statusText = getIntent().getStringExtra("statusText");
        textView.setText("Grade: " +statusText);

        if(statusText.equals("A")){
            statusButton.setText("Excellent");
        }else if(statusText.equals("B")){
            statusButton.setText("Very Good.");
        }else if(statusText.equals("C")){
            statusButton.setText("Satisfactory");
        }else if(statusText.equals("D")){
            statusButton.setText("Poor");
        }else {
            statusButton.setText("Failed. Try again.");
            statusButton.setBackgroundResource(R.drawable.options_button_bg);
        }


        // Set up the PieChart
        PieChart chartView = findViewById(R.id.chartView);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(correctCount, "Correct " + correctCount));
        entries.add(new PieEntry(incorrectCount, "Incorrect " + incorrectCount));
        PieDataSet dataSet = new PieDataSet(entries, "Quiz Result " + statusText);
        dataSet.setColors(Color.GREEN, Color.RED);

        PieData data = new PieData(dataSet);
        chartView.setData(data);
        chartView.getDescription().setEnabled(false);
        chartView.setHoleRadius(50f);
        chartView.animateY(2000, Easing.EaseInOutCubic);
        chartView.invalidate();

        // Set up the RecyclerView and adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<QuizQuestion> questions = getIntent().getParcelableArrayListExtra("filteredQuestions");
        textView1.setText(correctCount +"/"+questions.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReviewAdapter adapter1 = new ReviewAdapter(questions, this);
        recyclerView.setAdapter(adapter1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
