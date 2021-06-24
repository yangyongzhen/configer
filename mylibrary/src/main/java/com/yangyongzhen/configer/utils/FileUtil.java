package com.yangyongzhen.configer.utils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public  class FileUtil {
	public static String TAG= FileUtil.class.getSimpleName();
	/**
	 * 读文件
	 * @return
	 */
	public static String readFile(String fileName, String defaultvalue) {
		//Log.i(TAG,"readFile  fileName  "+fileName+"  defaultvalue  "+defaultvalue);
		String readRes = "";
		try {
			FileInputStream fin = new FileInputStream(fileName);
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			readRes = new String(buffer, "UTF-8");
			fin.close();
		}catch (Exception e) {
			Log.i(TAG,"readFile  Exception  "+e.toString());
			readRes=defaultvalue;
		}
		//Log.i(TAG,"readRes  "+readRes);
		return readRes;
	}
	/**
	 * 写文件
	 */
	public static boolean writeFile(String dir, String Filename, String value) {
		//Log.i(TAG,"writeFile  dir  "+dir+"    Filename  "+Filename+"  value  "+value);
		boolean writeFileSuccess=false;
		try{
			File dirfile = new File(dir);
			if (!dirfile.exists()){
				boolean dirfilemkdirRe=dirfile.mkdirs();
				//Log.i(TAG,"dirfilemkdirRe  "+dirfilemkdirRe);
			}
			File f = new File(dir+"/"+Filename);
			//if(f.exists()){
			//f.delete();
			//}
			FileOutputStream fos = new FileOutputStream(f.getPath());
			fos.write(value.getBytes("UTF-8"));
			fos.close();
			writeFileSuccess=true;
		}catch (Exception e) {
			Log.i(TAG,"writeFile  Exception  "+e.toString());
			writeFileSuccess=false;
		}
		//Log.i(TAG,"writeFileSuccess  "+writeFileSuccess);
		return writeFileSuccess;
	}


}