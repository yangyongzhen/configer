package com.yangyongzhen.demo;

import com.yangyongzhen.configer.Configer;
import com.yangyongzhen.configer.Ignore;

public class SysCfg extends Configer {
    //Ignore,不序列化存储的使用Ignore注解
    @Ignore
    public static final String TAG = SysCfg.class.getSimpleName(); //做为配置文件的默认文件名
    @Ignore
    private static SysCfg _instance = null;

    private Integer Ver = 0;				//版本号
    private String Time ="";		//时间
    private String PosCode = ""; //终端编号
    private String UserName = "";		//登录用户名
    private String UserPwd = "";			//密码

    public SysCfg() {
        super(TAG);
    }

    private SysCfg(String filename) {
        super(filename);
    }

    public Integer getVer() {
        return Ver;
    }

    public void setVer(Integer ver) {
        Ver = ver;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPosCode() {
        return PosCode;
    }

    public void setPosCode(String posCode) {
        PosCode = posCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }

    @Override
    public String toString() {
        return "SysCfg{" +
                "Ver=" + Ver +
                ", Time='" + Time + '\'' +
                ", PosCode='" + PosCode + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserPwd='" + UserPwd + '\'' +
                '}';
    }

    public static SysCfg getInstance() {
        if (_instance == null) {
            synchronized(SysCfg.class){
                if (_instance == null) {
                    _instance = new SysCfg(TAG);
                }
            }
        }
        return _instance;
    }
}
