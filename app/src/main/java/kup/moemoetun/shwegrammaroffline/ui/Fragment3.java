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

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;

import java.util.ArrayList;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Level_One_WebView;

public class Fragment3 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    private int itemPosition;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Morning");
        animalNames.add("The first day of the school");
        animalNames.add("water on the floor");
        animalNames.add("Babysitting");
        animalNames.add("Doctor");
        animalNames.add("Twin");
        animalNames.add("Reading");
        animalNames.add("Ruined by the rain");
        animalNames.add("Banana nut muffin");
        animalNames.add("The park");
        animalNames.add("Fruit Shop");
        animalNames.add("New Shirt");
        animalNames.add("Picking the color for the house");
        animalNames.add("A beautiful garden");
        animalNames.add("A substitute teacher");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean isPrecache) {
                // Called when interstitial is loaded
            }
            @Override
            public void onInterstitialFailedToLoad() {
                // Called when interstitial failed to load
            }
            @Override
            public void onInterstitialShown() {
                // Called when interstitial is shown
            }
            @Override
            public void onInterstitialShowFailed() {
                // Called when interstitial show failed
            }
            @Override
            public void onInterstitialClicked() {
                // Called when interstitial is clicked
            }
            @Override
            public void onInterstitialClosed() {
                // Called when interstitial is closed
                Intent intent = new Intent(requireContext(), Level_One_WebView.class);
                intent.putExtra("key",itemPosition);
                startActivity(intent);
            }
            @Override
            public void onInterstitialExpired() {
                // Called when interstitial is expired
            }
        });

    }
    @Override
    public void onItemClick(View view, int position) {
        itemPosition = position;
        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)){
            Appodeal.show(requireActivity(), Appodeal.INTERSTITIAL);
        }else {
            Intent intent = new Intent(requireContext(), Level_One_WebView.class);
            intent.putExtra("key",itemPosition);
            startActivity(intent);
        }
    }
}
