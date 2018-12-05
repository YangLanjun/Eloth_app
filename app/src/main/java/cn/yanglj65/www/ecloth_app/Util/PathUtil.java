package cn.yanglj65.www.ecloth_app.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class PathUtil {
    public static Drawable assets2Drawable(Context context, String fileName) {
        InputStream open = null;
        Drawable drawable = null;
        try {
            open = context.getAssets().open(fileName);
            drawable = Drawable.createFromStream(open, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (open != null) {
                    open.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return drawable;
    }

}
