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
import kup.moemoetun.shwegrammaroffline.constant.AppConstant;
import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.activity.DailyLife;
import kup.moemoetun.shwegrammaroffline.activity.Dating_Act;
import kup.moemoetun.shwegrammaroffline.activity.Job_Act;
import kup.moemoetun.shwegrammaroffline.activity.ShoppingAct;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Fragment2 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;

    // ===== AdMob =====
    private InterstitialAd mInterstitialAd;
    private int clickCount = 0;
    private int selectedPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load first ad
        loadInterstitialAd();

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

    private void loadInterstitialAd() {
        if (!AppConstant.isAdOn) return;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                requireContext(),
                AppConstant.OFFLINE_INTERSTITIALS, 
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
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
                    startNextActivity(selectedPosition);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    mInterstitialAd = null;
                    startNextActivity(selectedPosition);
                }
            });
            mInterstitialAd.show(requireActivity());
        } else {
            startNextActivity(selectedPosition);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        selectedPosition = position;
        showInterstitialAd();
    }

    private void startNextActivity(int position) {
        Intent intent = null;
        if(position==0){
            intent = new Intent(getContext(), ShoppingAct.class);
        } else if (position==1){
            intent = new Intent(getContext(), DailyLife.class);
        } else if (position==2){
            intent = new Intent(getContext(), Job_Act.class);
        } else if (position==3){
            intent = new Intent(getContext(), Dating_Act.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}