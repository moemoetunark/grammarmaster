package kup.moemoetun.shwegrammaroffline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.activity.DailyLife;
import kup.moemoetun.shwegrammaroffline.activity.Dating_Act;
import kup.moemoetun.shwegrammaroffline.activity.Job_Act;
import kup.moemoetun.shwegrammaroffline.activity.ShoppingAct;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;

public class Fragment2 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Shopping ထွက်ခြင်း");
        animalNames.add("နေ့စဥ်ဘဝ");
        animalNames.add("Job - အလုပ်အကြောင်း");
        animalNames.add("Dating - ချိန်ဆိုခြင်း");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

            if(position==0){
                Intent intent = new Intent(getContext(), ShoppingAct.class);
                startActivity(intent);
            }else if (position==1){
                Intent intent = new Intent(getContext(), DailyLife.class);
                startActivity(intent);
            }

            else if (position==2){
                Intent intent = new Intent(getContext(), Job_Act.class);
                startActivity(intent);
            }

            else if (position==3){
                Intent intent = new Intent(getContext(), Dating_Act.class);
                startActivity(intent);
            }
        }



}
