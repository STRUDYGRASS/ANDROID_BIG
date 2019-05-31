package com.example.andorid_big.Model;

import com.example.andorid_big.Contract.login_contract;

public class login_model implements login_contract.login_ModelInterface{
    public login_model() {//初始化数据库和人脸识别服务

    }

    @Override
    public void CheckOut_Count(String name,String account,final Login_Return login_return){
        if (true/*数据库中有对应值*/){
            login_return.BackWith_NameRepeat();
        }
        else{ //进入人脸识别界面

        }
            }
}
