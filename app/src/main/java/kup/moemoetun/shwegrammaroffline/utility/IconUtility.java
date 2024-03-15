package kup.moemoetun.shwegrammaroffline.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class IconUtility {
    public static Drawable generateNumberIcon(Context context, int number, int iconSize, int backgroundColor, int textColor) {
        // Create a bitmap with the appropriate size
        Bitmap iconBitmap = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);
        // Create a canvas with the bitmap
        Canvas canvas = new Canvas(iconBitmap);

        // Set the background color and draw a circular shape
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, backgroundColor));
        paint.setAntiAlias(true);
        canvas.drawCircle(iconSize / 2f, iconSize / 2f, iconSize / 2f, paint);

        // Draw the number in the center
        Paint textPaint = new Paint();
        textPaint.setColor(ContextCompat.getColor(context, textColor));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(iconSize / 2f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        float xPos = canvas.getWidth() / 2f;
        float yPos = (canvas.getHeight() / 2f) - ((textPaint.descent() + textPaint.ascent()) / 2f);
        canvas.drawText(String.valueOf(number), xPos, yPos, textPaint);

        // Create a drawable from the bitmap and return it
        return new BitmapDrawable(context.getResources(), iconBitmap);
    }
}
