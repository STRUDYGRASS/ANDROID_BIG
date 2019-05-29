package com.example.andorid_big.Presenter;

import com.example.andorid_big.Base.BasePresenter;
import com.example.andorid_big.Contract.login_contract;
import com.example.andorid_big.Model.login_model;

public class login_presenter extends BasePresenter<login_contract.login_ViewInterface> implements login_contract.login_PresenterInterface{
    login_model loginModel = null;

    public login_presenter(login_contract.login_ViewInterface v){
        super(v);
        loginModel = new login_model();
    }
}
