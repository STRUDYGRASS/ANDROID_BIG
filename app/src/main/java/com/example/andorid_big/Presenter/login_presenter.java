package com.example.andorid_big.Presenter;

import com.example.andorid_big.Base.BasePresenter;
import com.example.andorid_big.Contract.login_contract;
import com.example.andorid_big.Model.login_model;

public class login_presenter extends BasePresenter<login_contract.login_ViewInterface> implements login_contract.login_PresenterInterface{
    login_model loginModel = null;
    login_contract.login_ModelInterface.Login_Return login_return = new login_contract.login_ModelInterface.Login_Return() {
        @Override
        public void BackWith_FaceRepeat() {
            getView().ShowDialogWith("该人脸已经被注册！");
        }

        @Override
        public void BackWith_NameRepeat() {
            getView().ShowDialogWith("姓名、学号有重复！");
        }

        @Override
        public void BackWith_FaceSuccess() {
            getView().ShowDialogWith("人脸注册成功！");
        }
    };

    public login_presenter(login_contract.login_ViewInterface v){
        super(v);
        loginModel = new login_model();
    }

    @Override
    public void Login_Sign_Check(){
        getView().Checkin_Login();
    }

    @Override
    public void Login_Register_Check(){
        getView().Checkin_Regester();
    }

    @Override
    public void Login_Log_Check(){
        getView().Checkin_Login_Log();
    }

    @Override
    public void Main_Check(){
        getView().Checkin_Main();
    }

    @Override
    public void Register_Submit(String name, String account){
        //loginModel
    }

}
