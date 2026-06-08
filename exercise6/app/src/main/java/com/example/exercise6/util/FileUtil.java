package com.example.exercise6.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static boolean saveImage(String path, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap loadImage(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                return BitmapFactory.decodeFile(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String path) {
        try {
            File file = new File(path);
            return file.exists() && file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFileExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}