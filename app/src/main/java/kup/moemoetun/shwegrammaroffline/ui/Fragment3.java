package kup.moemoetun.shwegrammaroffline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Level_One_WebView;

public class Fragment3 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    private InterstitialAd mInterstitialAd;
    MyRecyclerViewAdapter adapter;
    private AdRequest adRequest;
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

        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(), getString(R.string.offfline_interstitials),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                    }
                });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(requireContext(), Level_One_WebView.class);
        intent.putExtra("key",position);
        startActivity(intent);
    }
}
