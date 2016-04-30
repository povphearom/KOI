package com.phearom.koi.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.view.WindowManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by phearom on 4/27/16.
 */
public class BarcodeUtils {
    private static final int WIDTH = 300;
    private static final int BLACK = Color.BLACK;
    private static final int WHITE = Color.WHITE;
    private static final int width = 300;
    private static final int height = 40;

    public static Bitmap generateQRCode(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);
        return bitmap;
    }

    public static Bitmap generateBarcode(String data) throws WriterException {
        BitMatrix bm = null;
        try {
            MultiFormatWriter writer = new MultiFormatWriter();
            String final_data = Uri.encode(data, "utf-8");
            bm = writer.encode(final_data, BarcodeFormat.CODE_128, WIDTH, WIDTH);
        } catch (Exception e) {
            return null;
        }

        Bitmap ImageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < WIDTH; i++) {//width
            for (int j = 0; j < height; j++) {//height
                ImageBitmap.setPixel(i, j, bm.get(i, j) ? BLACK : WHITE);
            }
        }
        return ImageBitmap;
    }

    public static void updateBrightness(Activity activity, float f) {
        WindowManager.LayoutParams layout = activity.getWindow().getAttributes();
        layout.screenBrightness = f;
        activity.getWindow().setAttributes(layout);
    }
}
