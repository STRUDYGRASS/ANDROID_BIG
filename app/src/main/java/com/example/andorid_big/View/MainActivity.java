package com.example.andorid_big.View;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;

import com.example.andorid_big.Base.BaseActivity;
import com.example.andorid_big.BuildConfig;
import com.example.andorid_big.Model.Sign_List;
import com.example.andorid_big.Presenter.login_presenter;
import com.example.andorid_big.R;
import com.example.andorid_big.Contract.login_contract;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends BaseActivity<login_contract.login_ViewInterface, login_presenter> implements login_contract.login_ViewInterface {

    EditText text_register_name, text_register_account;
    login_presenter mlogin_presenter;

    private boolean Camera_Mark = false;
    private Uri Photo_Uri = null;
    private Uri contentUri = null;
    private int LOG_FACE_CODE = 1, SIGN_FACE_CODE = 2;
    private List<Sign_List> sign_lists = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= 23 && (PermissionChecker
                .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PermissionChecker.PERMISSION_GRANTED || PermissionChecker
                .checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PermissionChecker.PERMISSION_GRANTED) ){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 0);
        }

        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= 23 && PermissionChecker
                .checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
        }
        setContentView(R.layout.main_interface);

        mlogin_presenter = getPresenter();


        /**************************************************************************/

        //指定人脸注册按钮，并设定跳转到Checkin_Regester函数切换布局
        //签到页面同上跳转,记录同理(内部控件目前需要一种方法使得切换布局就调用控件获取数据，可写在跳转按钮中（切记符合MVP架构）)
        //指定注册页面的注册按钮，跳转到注册事件
    }

    public void Register_log(View view) { //注册界面的注册按钮
        String str_name = text_register_name.getText().toString(), str_account = text_register_account.getText().toString();
        if (str_account.length() > 0 && str_name.length() > 0) {
            mlogin_presenter.Register_Submit(str_name, str_account);
        } else {
            mlogin_presenter.getView().ShowDialogWith("必须输入姓名和学号！");
        }
    }
    public void Interface_return(View view){
        mlogin_presenter.Main_Check();
    }
    public void Register_back(View view){ //注册界面的返回按钮
        mlogin_presenter.Main_Check();
    }

    public void Main_checkin(View view) { //切换到登陆界面
        mlogin_presenter.Login_Sign_Check();
    }

    public void Main_Register(View view) { //切换到注册界面
        mlogin_presenter.Login_Register_Check();
    }

    @Override
    protected login_presenter createPresenter() {
        return new login_presenter((login_contract.login_ViewInterface) this);
    }

    @Override
    public void ShowDialogWith(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Checkin_Regester() {
        setContentView(R.layout.register);
        text_register_name = findViewById(R.id.text_register_name);
        text_register_account = findViewById(R.id.text_register_account);
    }

    @Override
    public void Checkin_Login() {
        setContentView(R.layout.interface_signin);
        Start_Camera_Sign();
        sign_lists = mlogin_presenter.ListInit();
        //还未写setadapter到具体view
    }



    @Override
    public void Checkin_Main() {
        setContentView(R.layout.main_interface);
    }

    @Override
    public void Start_Camera_Log() { //相机能力有限，调用api了
//        // 记录文件保存位置
//         String mFilePath;
//         FileInputStream is = null;
//
//        // 获取SD卡路径
//        mFilePath = Environment.getExternalStorageDirectory().getPath();
//        // 文件名
//        mFilePath = mFilePath + "/" + "photo.png";

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //指定拍照
//        Uri uri = Uri.fromFile(new File(mFilePath));
//        contentUri = FileProvider.getUriForFile(this, "com.example.andorid_big.provider", new File(mFilePath));
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 楼主少了这个。没有这个finsh 无法返回的
        startActivityForResult(intent, LOG_FACE_CODE);
        //while (!Camera_Mark); //等待相机回调
//        if (Photo_Uri != null) {
//            return Photo_Uri.toString();
//        }
//        else

    }

    @Override
    public void Start_Camera_Sign() { //相机能力有限，调用api了
//        // 记录文件保存位置
//         String mFilePath;
//         FileInputStream is = null;
//
//        // 获取SD卡路径
//        mFilePath = Environment.getExternalStorageDirectory().getPath();
//        // 文件名
//        mFilePath = mFilePath + "/" + "photo.png";

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //指定拍照
//        Uri uri = Uri.fromFile(new File(mFilePath));
//        contentUri = FileProvider.getUriForFile(this, "com.example.andorid_big.provider", new File(mFilePath));
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 楼主少了这个。没有这个finsh 无法返回的
        startActivityForResult(intent, SIGN_FACE_CODE);
        //while (!Camera_Mark); //等待相机回调
//        if (Photo_Uri != null) {
//            return Photo_Uri.toString();
//        }
//        else

    }


    @Override
    public void FaceCheck(byte[] bt,int MARK) {
        if (MARK == LOG_FACE_CODE) {
            mlogin_presenter.FaceCheck_Log(bt);
        }
        else if (MARK == SIGN_FACE_CODE){
            mlogin_presenter.FaceCheck_Sign(bt);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 判断返回值是否正确
            if (requestCode == LOG_FACE_CODE) {
                if (data.getExtras() != null) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                    byte[] bt = bos.toByteArray();
                    FaceCheck(bt,LOG_FACE_CODE);
                }

            } else if (requestCode == SIGN_FACE_CODE) {
                if (data.getExtras() != null) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                    byte[] bt = bos.toByteArray();
                    FaceCheck(bt,SIGN_FACE_CODE);
                }
            }
        }
    }
}
