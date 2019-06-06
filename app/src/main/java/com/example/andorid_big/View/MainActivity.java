package com.example.andorid_big.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.andorid_big.Base.BaseActivity;
import com.example.andorid_big.Presenter.login_presenter;
import com.example.andorid_big.R;
import com.example.andorid_big.Contract.login_contract;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.Inflater;


public class MainActivity extends BaseActivity<login_contract.login_ViewInterface, login_presenter> implements login_contract.login_ViewInterface{

    EditText text_register_name,text_register_account;
    login_presenter mlogin_presenter;

    private boolean Camera_Mark = false;
    private Uri Photo_Uri = null;
    private  Uri contentUri = null;
    private int LOG_FACE_CODE = 1,SIGN_FACE_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);

        mlogin_presenter = getPresenter();


        /**************************************************************************/

        //指定人脸注册按钮，并设定跳转到Checkin_Regester函数切换布局
        //签到页面同上跳转,记录同理(内部控件目前需要一种方法使得切换布局就调用控件获取数据，可写在跳转按钮中（切记符合MVP架构）)
        //指定注册页面的注册按钮，跳转到注册事件
    }

    public void Register_log(View view) {
        String str_name = text_register_name.getText().toString(), str_account = text_register_account.getText().toString();
        if (str_account != null && str_name != null) {
            mlogin_presenter.Register_Submit(str_name, str_account);
        }
    }

    public void Main_Register(View view){
        mlogin_presenter.Login_Register_Check();
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
        setContentView(R.layout.register);
        text_register_name = findViewById(R.id.text_register_name);
        text_register_account = findViewById(R.id.text_register_account);
    }

    @Override
    public void Checkin_Login(){
        setContentView(R.layout.interface_signin);
    }

    @Override
    public void Checkin_Login_Log(){
        setContentView(R.layout.activity_main);//layout待写
    }

    @Override
    public void Checkin_Main(){
        setContentView(R.layout.main_interface);
    }

    @Override
    public void Start_Camera_Log(){ //相机能力有限，调用api了
        // 记录文件保存位置
         String mFilePath;
         FileInputStream is = null;

        // 获取SD卡路径
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        // 文件名
        mFilePath = mFilePath + "/" + "photo.png";

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //指定拍照
//        Uri uri = Uri.fromFile(new File(mFilePath));
//        contentUri = FileProvider.getUriForFile(this, "com.example.andorid_big.provider", new File(mFilePath));
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        startActivityForResult(intent,LOG_FACE_CODE);
        //while (!Camera_Mark); //等待相机回调
        Camera_Mark = false;
//        if (Photo_Uri != null) {
//            return Photo_Uri.toString();
//        }
//        else

    }

    @Override
    public void FaceCheck(byte[] bt){
        mlogin_presenter.FaceCheck_Log(bt);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 判断返回值是否正确
            if (requestCode == LOG_FACE_CODE ) {
                if (data.getExtras() != null) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap)extras.get("data") ;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                    byte[] bt = bos.toByteArray();
                    FaceCheck(bt);
                }

            }
        }
    }
}
