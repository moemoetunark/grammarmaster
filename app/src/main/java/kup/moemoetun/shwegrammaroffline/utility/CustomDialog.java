package kup.moemoetun.shwegrammaroffline.utility;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import kup.moemoetun.shwegrammaroffline.R;

public class CustomDialog {
    public static androidx.appcompat.app.AlertDialog createDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.fullScreenDialog);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.full_screen_dialog, null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        // Find your custom buttons by their IDs in the dialogView
        Button customPositiveButton = dialogView.findViewById(R.id.positiveButton);
        Button customNegativeButton = dialogView.findViewById(R.id.negativeButton);
        AdView adView = dialogView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        customPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        customNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the custom negative button click
                // Add your logic here
                alertDialog.dismiss(); // Close the dialog if needed
            }
        });

        return alertDialog;
    }
}
