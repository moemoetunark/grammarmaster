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
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;

import java.util.ArrayList;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Level_Four_WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Fragment6 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;
    private InterstitialAd mInterstitialAd;
    private int clickCount = 0;
    private int selectedPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level6, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadInterstitialAd();
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

    private void loadInterstitialAd() {
        if (!AppConstant.isAdOn) return;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                requireContext(),
                AppConstant.OFFLINE_INTERSTITIALS,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                }
        );
    }

    private void showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    mInterstitialAd = null;
                    loadInterstitialAd();
                    openNextScreen();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    mInterstitialAd = null;
                    openNextScreen();
                }
            });
            mInterstitialAd.show(requireActivity());
        } else {
            openNextScreen();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        selectedPosition = position;
        clickCount++;
        if (clickCount == 1 || clickCount >= AppConstant.adFrequency) {
            clickCount = 0;
            showInterstitialAd();
        } else {
            openNextScreen();
        }
    }

    private void openNextScreen() {
        Intent intent = new Intent(requireContext(), Level_Four_WebView.class);
        intent.putExtra("key", selectedPosition);
        startActivity(intent);
    }
}