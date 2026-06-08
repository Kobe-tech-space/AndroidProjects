package com.example.course7_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.course7_client.util.PermissionUtil;
import com.example.course7_client.util.ToastUtil;

public class PermissionLazyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] PERMISSIONS_CONTACTS = new String[] {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
    };
    private static final String[] PERMISSIONS_SMS =  new String[] {
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS
    };
    private static final int REQUEST_CODE_CONTACTS = 1;
    private static final int REQUEST_CODE_SMS = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_lazy);
        findViewById(R.id.btn_contact).setOnClickListener(this);
        findViewById(R.id.btn_sms).setOnClickListener(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CONTACTS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("legion","通讯录权限获取成功");
                } else {
                    ToastUtil.show(this,"获取通讯录读写权限失败");
                }
                break;
            case REQUEST_CODE_SMS:
                if (PermissionUtil.checkGrant(grantResults)) {
                    Log.d("legion", "收发短信权限获取成功");
                } else {
                    ToastUtil.show(this,"获取收发短信权限失败");
                    jumpToSettings();
                }
                break;
        }
    }
    private void jumpToSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package",getPackageName(),null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_contact:
                boolean hasContactPermission = PermissionUtil.checkPermission(this,PERMISSIONS_CONTACTS,REQUEST_CODE_CONTACTS);
                if (hasContactPermission) {
                    Log.d("legion","通讯录权限已存在");
                }
                break;
            case R.id.btn_sms:
                boolean hasSmsPermission = PermissionUtil.checkPermission(this,PERMISSIONS_SMS,REQUEST_CODE_SMS);
                if (hasSmsPermission) {
                    Log.d("legion","短信权限已存在");
                }
                break;
        }

    }
}