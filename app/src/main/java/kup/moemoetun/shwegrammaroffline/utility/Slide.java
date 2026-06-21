package kup.moemoetun.shwegrammaroffline.utility;


import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class Slide {
    public static void startAutoScroll(final RecyclerView recyclerView) {
        final int scrollSpeed = 3000; // Adjust the speed (in milliseconds) at which the RecyclerView scrolls

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            private boolean forward = true; // Flag to indicate scrolling direction

            @Override
            public void run() {
                LinearLayoutManager layoutManager = (LinearLayoutManager) (recyclerView.getLayoutManager());
                int currentPosition = layoutManager.findFirstVisibleItemPosition();
                int itemCount = (recyclerView.getAdapter()).getItemCount();
                int nextPosition;

                if (forward) {
                    // Scroll forward (increment position)
                    nextPosition = currentPosition + 1;
                } else {
                    // Scroll backward (decrement position)
                    nextPosition = currentPosition - 1;
                }

                if (nextPosition < 0) {
                    // If at the start, change direction to forward
                    forward = true;
                    nextPosition = 1;
                } else if (nextPosition >= itemCount) {
                    // If at the end, change direction to backward
                    forward = false;
                    nextPosition = itemCount - 2;
                }

                recyclerView.smoothScrollToPosition(nextPosition);
                handler.postDelayed(this, scrollSpeed);
            }
        };

        handler.postDelayed(runnable, scrollSpeed);
    }

}
