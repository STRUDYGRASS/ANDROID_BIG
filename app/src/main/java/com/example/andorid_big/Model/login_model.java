package com.example.andorid_big.Model;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.example.andorid_big.Contract.login_contract;
import com.example.andorid_big.Model.FaceBaiduApi.AuthService;
import com.example.andorid_big.Model.FaceBaiduApi.FaceAdd;
import com.example.andorid_big.bean.FaceAddReturn;
import com.google.gson.Gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class login_model implements login_contract.login_ModelInterface{
    private String AccessToken;

    private Handler mHandler;
    private boolean mRunning = false;


    public login_model() {//初始化数据库和人脸识别服务以及线程
        new Thread() {
            @Override
            public void run(){
                AccessToken=AuthService.getAuth();//每次启动获取一次token
            }
        }.start();
    }

    @Override
    public void CheckOut_Count(String name,String account,final Login_Return login_return){
        if (true/*数据库中有对应值*/){
            login_return.BackWith_NameRepeat();
        }
        else{ //进入人脸识别界面

        }
//        new Thread(){
//            @Override
//            public void run() {
//                //FaceAddReturn faceAddReturn = new Gson().fromJson(FaceAdd.add("2","027d8308a2ec665acb1bdf63e513bcb9",AccessToken),FaceAddReturn.class);
//                JSON json = JSON.parseObject(FaceAdd.add("2","027d8308a2ec665acb1bdf63e513bcb9",AccessToken));
//                FaceAddReturn faceAddReturn = JSONObject.toJavaObject(json, FaceAddReturn.class);
//                System.out.println(faceAddReturn.getError_msg() + faceAddReturn.getResult().getLocation().getLeft());
//            }
//        }.start(); //接收完毕后可以在或者使用布尔变量改变后销毁线程

            }

}
