package com.example.andorid_big.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andorid_big.Base.BaseActivity;
import com.example.andorid_big.Presenter.login_presenter;
import com.example.andorid_big.R;
import com.example.andorid_big.Contract.login_contract;

public class MainActivity extends BaseActivity<login_contract.login_ViewInterface, login_presenter> implements login_contract.login_ViewInterface{

    login_presenter mlogin_presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlogin_presenter = getPresenter();
        //指定人脸注册按钮，并设定跳转到Checkin_Regester函数切换布局
        //签到页面同上跳转,记录同理(内部控件目前需要一种方法使得切换布局就调用控件获取数据，可写在跳转按钮中（切记符合MVP架构）)
        //指定注册页面的注册按钮，跳转到注册事件
    }

    @Override
    protected login_presenter createPresenter(){
        return new login_presenter((login_contract.login_ViewInterface)this);
    }

    @Override
    public void ShowDialogWith(String str)
    {
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Checkin_Regester(){
        setContentView(R.layout.activity_main);//layout待写
    }

    @Override
    public void Checkin_Login(){
        setContentView(R.layout.activity_main);//layout待写
    }

    @Override
    public void Checkin_Login_Log(){
        setContentView(R.layout.activity_main);//layout待写
    }

    @Override
    public void Checkin_Main(){
        setContentView(R.layout.activity_main);
    }
}
