package com.example.andorid_big.Model;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;

import com.baidu.ai.aip.utils.Base64Util;
import com.example.andorid_big.Contract.login_contract;
import com.example.andorid_big.Model.FaceBaiduApi.AuthService;
import com.example.andorid_big.Model.FaceBaiduApi.FaceAdd;
import com.example.andorid_big.Model.FaceBaiduApi.FaceSearch;
import com.example.andorid_big.Model.SQL.SQL_Connection;
import com.example.andorid_big.Model.SQL.SQL_Insert;
import com.example.andorid_big.Model.SQL.SQL_Select;
import com.example.andorid_big.bean.FaceAddReturn;
import com.example.andorid_big.bean.FaceSearchReturn;
import com.google.gson.Gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class login_model implements login_contract.login_ModelInterface{
    private String AccessToken,register_name,register_account;
    private boolean return_value=true;
    private ExecutorService pool = null;
    private byte[] Face_Byte = null;
    private  String Face_account = null,Face_name = null;
    private boolean Thread_FaceAdd_Done = false,Thread_FaceSearch_Done = false,Thread_insert_Done=false;
    private FaceAddReturn faceAddReturn = null;
    private FaceSearchReturn faceSearchReturn = null;

    private Connection conn = null;
    private int Insert_Return = 0;


    public login_model() {//初始化数据库和人脸识别服务以及线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                conn = SQL_Connection.Connection();//连接数据库
            }
        }).start();
        new Thread() {
            @Override
            public void run(){
                AccessToken=AuthService.getAuth();//每次启动获取一次token
            }
        }.start();

        pool = Executors.newFixedThreadPool(2);
        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
    }

    @Override
    public void CheckOut_Count(String name,String account,final Login_Return login_return){
        register_name=name;
        register_account=account;
        pool.execute(SQL_Select);
        while (!Thread_insert_Done);
        Thread_insert_Done=false;
        if (Insert_Return==201/*数据库中有对应值*/){
            login_return.BackWith_NameRepeat();
        }
        else{ //进入人脸识别界面
            login_return.Start_Camera_Log(); //执行操作放到mainactivity中
            Face_account = account;
            Face_name = name;
//            Uri photo_uri = Uri.parse((String) login_return.Start_Camera());
//            System.out.println(photo_uri);
        }


    }

    Thread SQL_insert=new Thread(){
        @Override
        public void run(){
            Insert_Return= SQL_Insert.InsertSql(register_name,register_account);
            Thread_insert_Done=true;
        }
    };

    Thread SQL_Select=new Thread(){
        @Override
        public void run(){
            //查询待写
        }
    };

    Thread thread_FaceAdd = new Thread(){
        @Override
        public void run() {
            //FaceAddReturn faceAddReturn = new Gson().fromJson(FaceAdd.add("2","027d8308a2ec665acb1bdf63e513bcb9",AccessToken),FaceAddReturn.class);
            JSON json = JSON.parseObject(FaceAdd.add(Face_account,Base64Util.encode(Face_Byte),AccessToken));
            faceAddReturn = JSONObject.toJavaObject(json, FaceAddReturn.class);
            Thread_FaceAdd_Done = true;
        }
    }; //接收完毕后可以在或者使用布尔变量改变后销毁线程

    Thread thread_FaceSearch = new Thread(){
        @Override
        public void run() {
            //FaceAddReturn faceAddReturn = new Gson().fromJson(FaceAdd.add("2","027d8308a2ec665acb1bdf63e513bcb9",AccessToken),FaceAddReturn.class);
            JSON json = JSON.parseObject(FaceSearch.search(Base64Util.encode(Face_Byte),AccessToken));
            faceSearchReturn = JSONObject.toJavaObject(json, FaceSearchReturn.class);
            Thread_FaceSearch_Done = true;
            //System.out.println(faceSearchReturn.getResult().getUser_list().get(0).getScore());
        }
    }; //接收完毕后可以在或者使用布尔变量改变后销毁线程
    // pool.execute(thread_FaceAdd);
    @Override
    public void FaceCheck_Log(byte[] bt,final Login_Return login_return) {
        Face_Byte = bt;
        pool.execute(thread_FaceSearch);
        while (!Thread_FaceSearch_Done) ; //等待线程结束
        Thread_FaceSearch_Done = false;
        //判断识别指数是否高于95
        if (faceSearchReturn.getError_code() == 0) {
            if (faceSearchReturn.getResult().getUser_list().get(0).getScore() > 95) {//判断是相同照片
                login_return.BackWith_FaceRepeat(faceSearchReturn.getResult().getUser_list().get(0).getUser_id());
            } else {
                pool.execute(thread_FaceAdd);
                while (!Thread_FaceAdd_Done) ; //等待线程结束
                Thread_FaceAdd_Done = false;
                if (faceAddReturn.getError_code() == 0) {
                    //数据库中加入该用户
                    login_return.BackWith_FaceSuccess();

                } else if (faceAddReturn.getError_code() == 222202) {
                    login_return.BackWith_FaceFail();
                    login_return.Back_ChangetoMain();
                }
            }
        }
        else{
            login_return.BackWith_FaceFail();
        }
    }

}
