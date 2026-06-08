package com.example.course6_2.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
    public static void saveText(String path,String txt){
        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(txt.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String openText(String path) {
        String readStr = "";
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] b = new byte[fis.available()];
            fis.read(b);
            readStr = new String(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  readStr;
    }
}
