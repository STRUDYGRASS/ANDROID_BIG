package com.example.andorid_big.Presenter;

import android.net.Uri;

import com.example.andorid_big.Base.BasePresenter;
import com.example.andorid_big.Contract.login_contract;
import com.example.andorid_big.Model.login_model;

public class login_presenter extends BasePresenter<login_contract.login_ViewInterface> implements login_contract.login_PresenterInterface{
    login_model loginModel = null;
    private int LOG_FACE_CODE = 1, SIGN_FACE_CODE = 2;
    login_contract.login_ModelInterface.Login_Return login_return = new login_contract.login_ModelInterface.Login_Return() {
        @Override
        public void BackWith_FaceRepeat(String name) {
            getView().ShowDialogWith("该人脸已经被" + name + "注册！");
        }

        @Override
        public void BackWith_NameRepeat() {
            getView().ShowDialogWith("姓名、学号有重复！");
        }

        @Override
        public void BackWith_FaceSuccess() {
            getView().Checkin_Main();
            getView().ShowDialogWith("人脸注册成功！");
        }
        @Override
        public void BackWith_FaceFail(){
            getView().ShowDialogWith("人脸识别失败！");
        }
        @Override
        public void Start_Camera_Log(){
             getView().Start_Camera_Log();
        }
    };
    login_contract.login_ModelInterface.Sign_Return sign_return = new login_contract.login_ModelInterface.Sign_Return() {
        @Override
        public void BackWith_Noface() {
            getView().ShowDialogWith("无该人脸！");
        }

        @Override
        public void BackWith_FaceSuccess() {
            getView().Checkin_Login();
            getView().ShowDialogWith("签到成功！");
        }

        @Override
        public void BackWith_FaceAlready() {
            getView().Checkin_Login();
            getView().ShowDialogWith("您已签到！");
        }

        @Override
        public void BackWith_FaceFail(){
            getView().ShowDialogWith("人脸识别失败！");
        }

        public void BackWith_SignFail(){
            getView().ShowDialogWith("签到失败！");
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
    public void Main_Check(){
        getView().Checkin_Main();
    }

    @Override
    public void Register_Submit(String name, String account){
        loginModel.CheckOut_Count(name,account,login_return);
    }

    @Override
    public void FaceCheck_Log(byte[] bt){
        loginModel.FaceCheck_Log(bt,login_return);
    }

    @Override
    public void FaceCheck_Sign(byte[] bt){
        loginModel.FaceCheck_Sign(bt,sign_return);
    }

}
