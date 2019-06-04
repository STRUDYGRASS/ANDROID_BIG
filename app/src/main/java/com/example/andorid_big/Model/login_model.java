package com.example.andorid_big.Model;

import com.example.andorid_big.Contract.login_contract;
import com.example.andorid_big.Model.FaceBaiduApi.AuthService;
import com.example.andorid_big.Model.FaceBaiduApi.FaceAdd;

public class login_model implements login_contract.login_ModelInterface{
    private String AccessToken;


    public login_model() {//初始化数据库和人脸识别服务
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
//                FaceAdd.add("2","ry",AccessToken);
//            }
//        }.start();

            }
}
