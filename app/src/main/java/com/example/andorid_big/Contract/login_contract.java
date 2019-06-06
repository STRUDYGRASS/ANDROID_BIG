package com.example.andorid_big.Contract;

import android.net.Uri;

public interface login_contract {
    interface login_ViewInterface{
        void ShowDialogWith(String str);
        void Checkin_Regester();
        void Checkin_Login();
        void Checkin_Login_Log();
        void Checkin_Main();

        void Start_Camera_Log();
        void FaceCheck(byte[] bt);
    }

    interface login_PresenterInterface{
        /****************************布局切换**************************/
        void Login_Sign_Check();
        void Login_Register_Check();
        void Login_Log_Check();
        void Main_Check();
        /***************************函数调用*****************************/
        void Register_Submit(String name, String account);

        void FaceCheck_Log(byte[] bt);
    }

    interface login_ModelInterface{
        void CheckOut_Count(String name,String account,Login_Return login_return);
        //在model中先做if判断，继而可调用摄像头回传数据
        interface Login_Return{
            void BackWith_FaceRepeat(String name);
            void BackWith_NameRepeat();
            void BackWith_FaceSuccess();
            void BackWith_FaceFail();
            /******************尝试调用************/
            void Start_Camera_Log();
        }
        /********************相机*******************/
        void FaceCheck_Log(byte[] bt);
    }
}
