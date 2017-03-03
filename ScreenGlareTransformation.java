package com.github.julianonunes.glidetransformations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.util.Date;

/**
 * Created by juliano on 3/3/2017.
 */

public class ScreenGlareTransformation extends BitmapTransformation {
    public ScreenGlareTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap result = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        // If no matching Bitmap is in the pool, get will return null, so we should allocate.
        if (result == null) {
            // Use ARGB_8888 since we're going to add alpha to the image.
            result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }

        Shader shader = new LinearGradient(0, 0, outWidth, outHeight, 0x8CFFFFFF, 0x00FFFFFF,
                Shader.TileMode.CLAMP);

        // Create a Canvas backed by the result Bitmap.
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();

        // Draw the original Bitmap onto the result Bitmap with a transformation.
        canvas.drawBitmap(toTransform, 0, 0, paint);

        Paint paint2 = new Paint();
        paint2.setShader(shader);

        Point a = new Point(getPositionFromPercent(outWidth, 45), 0);
        Point b = new Point(outWidth, 0);
        Point c = new Point(outWidth, outHeight);
        Point d = new Point(getPositionFromPercent(outWidth, 90),
                outHeight);


        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(d.x, d.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, paint2);

        // Since we've replaced our original Bitmap, we return our new Bitmap here. Glide will
        // will take care of returning our original Bitmap to the BitmapPool for us.
        return result;
    }

    private int getPositionFromPercent(int dimension, int percent) {
        return (dimension * percent) / 100;
    }

    @Override
    public String getId() {
        return ScreenGlareTransformation.class.getSimpleName() + new Date().toString(); // this might impact caching/performance
    }
}
