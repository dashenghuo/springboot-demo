package com.dalishen.demo;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetAllFilesTest {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        File file = new File("D:\\work-doc\\ref\\fleaf\\WEB-INF\\classes");
        StringBuffer stringBuffer = new StringBuffer();
        if (file.isDirectory()) {
            listFiles(file, stringBuffer);
        }

        writeStringToFile("E:\\class.txt", stringBuffer.toString());
    }

    public static void listFiles(File directory, StringBuffer stringBuffer) {

        File[] listFiles = directory.listFiles();

        for (int i = 0; i < listFiles.length; i++) {
            File f = listFiles[i];
            if (f.isDirectory()) {
                listFiles(f, stringBuffer); //存在最终的节点
            } else {
                if (f.getName().endsWith(".class") && !f.getName().contains("$")) {
                    System.out.println(f.getAbsolutePath() + "   " +
                            getTime(f.lastModified()));

                    stringBuffer.append(f.getAbsolutePath())
                            .append("    ")
                            .append(getTime(f.lastModified()))
                            .append("\r\n");
                }
            }
        }



    }

    public static String getTime(long time) {
        Date d = new Date(time);
        return format.format(d);
    }

    public static void writeStringToFile(String filePath, String content) {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filePath, "rw");
            rf.writeBytes(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}