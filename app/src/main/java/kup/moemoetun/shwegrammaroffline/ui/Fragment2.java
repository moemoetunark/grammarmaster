package kup.moemoetun.shwegrammaroffline.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdapterResponseInfo;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.activity.DailyLife;
import kup.moemoetun.shwegrammaroffline.activity.Dating_Act;
import kup.moemoetun.shwegrammaroffline.activity.Job_Act;
import kup.moemoetun.shwegrammaroffline.activity.ShoppingAct;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.webview.Shopping_web;

public class Fragment2 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    MyRecyclerViewAdapter adapter;
    private AdRequest adRequest;
    InterstitialAd mInterstitialAd;

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

        if (mInterstitialAd !=null) {
            mInterstitialAd.show(requireActivity());
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
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
                    // Called when fullscreen content is dismissed.
                }
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                }
            });

        }

        else {
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
}
