package com.example.andorid_big.View;

import android.app.Activity;
import android.os.Bundle;

import com.example.andorid_big.Base.BaseActivity;
import com.example.andorid_big.Presenter.login_presenter;
import com.example.andorid_big.R;
import com.example.andorid_big.Contract.login_contract;

public class MainActivity extends BaseActivity<login_contract.login_ViewInterface, login_presenter> implements login_contract.login_ViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected login_presenter createPresenter(){
        return new login_presenter((login_contract.login_ViewInterface)this);
    }
}
