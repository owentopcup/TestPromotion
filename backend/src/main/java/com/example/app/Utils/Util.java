package com.example.app.Utils;

import com.example.app.Constants.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Util {
    public static String readFile(File file) {
        String sCurrentLine = null;
        StringBuilder txt = new StringBuilder("");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((sCurrentLine = br.readLine()) != null) {
                txt.append(sCurrentLine);
                txt.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt.toString();
    }

    public static String convertDateFormat(String date, String formatOld, String formatNew) throws ParseException {
//        System.out.println("date:"+date);
        SimpleDateFormat sdf = new SimpleDateFormat(formatOld);
        Date d = sdf.parse(date);
        sdf.applyPattern(formatNew);
        return sdf.format(d);
    }
    public static boolean isEmpty(Object obj){
        if(obj==null)return  true;
        if(obj instanceof String){
            return  ((String) obj).isEmpty();
        }else{
            return  false;
        }
    }
    public static   Object copyDomain(Object obj,Class cls) throws Exception {
        return Constant.G_OBJ_MAPPER.readValue(Constant.G_OBJ_MAPPER.writeValueAsString(obj),cls);
    }
    public  static  String toJSONString(Object obj) throws JsonProcessingException {
        return Constant.G_OBJ_MAPPER.writeValueAsString(obj);
    }
    public static void writeJSONFile(String json, String fileName, String path) {
        StringBuilder myFilePath = new StringBuilder(path).append("\\").append(fileName).append(".").append("json");
        File file = new File(myFilePath.toString());
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            br.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static  String dateNow(String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format,Locale.US);
        return sdf.format(new Date());
    }
    public static File[] findFileInDirectoryBeginWith(String startWith, String path){
        File floder=new File(path);

        File[] files = floder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(startWith);
            }
        });
        return  files;

    }
}
