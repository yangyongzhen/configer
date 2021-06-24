package com.yangyongzhen.configer;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yangyongzhen.configer.utils.FileUtil;
import com.yangyongzhen.configer.utils.GsonUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Configer{
    private static final String TAG = "Configer";
    private static String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static String dirPath = "/Android/data";
    private transient String filename;

    public Configer(String filename) {
        this.filename = filename;
    }


    public Boolean save(){
        //Log.d(TAG,this.toString());
        String jsStr = GsonUtil.GsonString(this);
        //Log.d(TAG,jsStr);
        Boolean ret = FileUtil.writeFile(rootPath+dirPath,filename,jsStr);
        return ret;
    }

    public Boolean load(){
        Class<?> cls = this.getClass();
        Object rec = null;
        String strfile = FileUtil.readFile(rootPath+dirPath+"/"+filename,"");
        if (!TextUtils.isEmpty(strfile)) {
            JSONObject jsonObject = JSON.parseObject(strfile);
            rec = GsonUtil.deserializer(jsonObject, this.getClass());
            Log.d(TAG,rec.toString());
        }
        if(rec != null){
            Class<?> cls1 = rec.getClass();
            Field[] declaredFields = cls.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                if (field.isAnnotationPresent(Ignore.class)) {
                    continue;
                }
                try {
                    Method method = cls1.getDeclaredMethod("get"+upperHeadChar(field.getName()));
                    Object obj = method.invoke(rec);
                    field.setAccessible(true);
                    field.set(this,obj);
                } catch (Exception e) {
                    Log.d(TAG,e.toString());
                    //e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }

    public static String getRootPath() {
        return rootPath;
    }

    public static void setRootPath(String rootPath) {
        Configer.rootPath = rootPath;
    }

    public static String getDirPath() {
        return dirPath;
    }

    public static void setDirPath(String dirPath) {
        Configer.dirPath = dirPath;
    }
}