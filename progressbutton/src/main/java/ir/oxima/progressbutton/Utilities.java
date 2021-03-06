package ir.oxima.progressbutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

import java.util.Hashtable;

public class Utilities {

    private static final Hashtable<String, Typeface> typefaceCache = new Hashtable<>();


    public static Typeface getTypeface(Context context,String assetPath) {
        synchronized (typefaceCache) {
            if (!typefaceCache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), assetPath);
                    typefaceCache.put(assetPath, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return typefaceCache.get(assetPath);
        }
    }

}
