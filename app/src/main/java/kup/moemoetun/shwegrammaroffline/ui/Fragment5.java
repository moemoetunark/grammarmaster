package kup.moemoetun.shwegrammaroffline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
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
import kup.moemoetun.shwegrammaroffline.webview.Level_Three_WebView;


public class Fragment5 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

 private InterstitialAd mInterstitialAd;
    MyRecyclerViewAdapter adapter;
    private AdRequest adRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Meeting the guy");
        animalNames.add("Grandma's Sweater");
        animalNames.add("Ugly Sister");
        animalNames.add("Know how to snowboard");
        animalNames.add("All you can eat");
        animalNames.add("The pet Chicken");
        animalNames.add("The last Cigarette");
        animalNames.add("Back to School");
        animalNames.add("Cat Lover");
        animalNames.add("Losing Weight");
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
                    Intent intent = new Intent(requireContext(), Level_Three_WebView.class);
                    intent.putExtra("key",position);
                    startActivity(intent);
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
            Intent intent = new Intent(requireContext(), Level_Three_WebView.class);
            intent.putExtra("key",position);
            startActivity(intent);
        }

    }
}
