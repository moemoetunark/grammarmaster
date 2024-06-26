package kup.moemoetun.shwegrammaroffline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Level_Four_WebView;
import kup.moemoetun.shwegrammaroffline.webview.Level_Three_WebView;

public class Fragment6 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level6, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("The first day at the job");
        animalNames.add("The sale associate");
        animalNames.add("Watching Movies");
        animalNames.add("No Phone");
        animalNames.add("Bob lost 50 pounds");
        animalNames.add("Instant Noodle");
        animalNames.add("Generous Woman");
        animalNames.add("Mom doesn't like him");
        animalNames.add("An old friend");
        animalNames.add("She loves walking.");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
            Intent intent = new Intent(requireContext(), Level_Four_WebView.class);
            intent.putExtra("key",position);
            startActivity(intent);
        }

}
