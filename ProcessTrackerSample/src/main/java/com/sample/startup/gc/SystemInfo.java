package com.sample.startup.gc;


import android.support.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SystemInfo {
    private static final String TAG = "SystemInfo";

    /**
     * Closes the given {@code Closeable}. Suppresses any IO exceptions.
     */
    public static void closeQuietly(@Nullable Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            //
        }
    }

    @Nullable
    protected String[] readProcFile(String file) {
        RandomAccessFile procFile = null;
        String procFileContents;
        try {
            procFile = new RandomAccessFile(file, "r");
            procFileContents = procFile.readLine();
            int rightIndex =  procFileContents.indexOf(")");
            if (rightIndex > 0) {
                procFileContents = procFileContents.substring(rightIndex + 2);
            }

            return procFileContents.split(" ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } finally {
            SystemInfo.closeQuietly(procFile);
        }

    }

}
