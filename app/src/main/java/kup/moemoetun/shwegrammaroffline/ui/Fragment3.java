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
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Level_One_WebView;

public class Fragment3 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    private int itemPosition;
    private InterstitialAd interstitialAd;
    private int clickCount = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> animalNames = new ArrayList<>();
        loadInterstitialAd();

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
    }

@Override
public void onItemClick(View view, int position) {
    itemPosition = position;
    clickCount++;
    
    if (clickCount == 1 || clickCount >= AppConstant.adFrequency) {
        clickCount = 0;
        showInterstitialAd();
    } else {
        openNextScreen();
    }
}

private void showInterstitialAd() {
    if (interstitialAd != null) {
        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                interstitialAd = null;
                loadInterstitialAd();
                openNextScreen();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                interstitialAd = null;
                openNextScreen();
            }
        });
        interstitialAd.show(requireActivity());
    } else {
        openNextScreen();
    }
}

private void openNextScreen() {
    Intent intent = new Intent(requireContext(), Level_One_WebView.class);
    intent.putExtra("key", itemPosition);
    startActivity(intent);
}

    private void loadInterstitialAd() {
    if (!AppConstant.isAdOn) return;
    AdRequest adRequest = new AdRequest.Builder().build();

    InterstitialAd.load(
            requireContext(),
            AppConstant.OFFLINE_INTERSTITIALS,
            adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd ad) {
                    interstitialAd = ad;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                    interstitialAd = null;
                }
            });
}
}