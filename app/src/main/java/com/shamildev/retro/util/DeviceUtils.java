package com.shamildev.retro.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.shamildev.retro.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by Schamil Mischijew on 06.11.2017.
 */

public class DeviceUtils {
    /**
     * @param context
     * @return the screen height in pixels
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    /**
     * @param context
     * @return the screen width in pixels
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }


    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});

        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }

    // ===========================================================
    //
    // ===========================================================

    private static final String INSTALLATION = "INSTALLATION";

    public synchronized static boolean isFirstLaunch(Context context) {
        String sID = null;
        boolean launchFlag = false;
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists()) {
                    launchFlag = true;
                    writeInstallationFile(installation);
                }
                sID = readInstallationFile(installation);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return launchFlag;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");// read only mode
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();

        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }
}
