package com.example.exercise4_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HeadActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private Button btnEditAvatar, btnDeleteAvatar, btnSave;

    // 头像资源数组（需要放在 res/drawable 文件夹下）
    private int[] avatarResources = {
            R.drawable.ic_avatar1,
            R.drawable.ic_avatar2,
            R.drawable.ic_avatar3,
            R.drawable.ic_avatar4,
            R.drawable.ic_avatar5,
            R.drawable.ic_avatar6
    };

    private int currentAvatarRes = R.drawable.ic_default_avatar;
    private static final int REQUEST_CODE_AVATAR = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);

        initViews();
        setListeners();
    }

    private void initViews() {
        ivAvatar = findViewById(R.id.iv_avatar);
        btnEditAvatar = findViewById(R.id.btn_edit_avatar);
        btnDeleteAvatar = findViewById(R.id.btn_delete_avatar);
        btnSave = findViewById(R.id.btn_save);
    }

    private void setListeners() {
        // 编辑头像按钮 - 弹出选择界面
        btnEditAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAvatarChoiceDialog();
            }
        });

        // 删除头像按钮
        btnDeleteAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAvatar();
            }
        });

        // 保存按钮
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });
    }

    // 弹出头像选择对话框（方法1：使用 AlertDialog + GridView）
    private void showAvatarChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择头像");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_avatar_choice, null);
        GridView gvAvatarList = dialogView.findViewById(R.id.gv_avatar_list);

        AvatarAdapter adapter = new AvatarAdapter(this, avatarResources);
        gvAvatarList.setAdapter(adapter);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        gvAvatarList.setOnItemClickListener((parent, view, position, id) -> {
            currentAvatarRes = avatarResources[position];
            ivAvatar.setImageResource(currentAvatarRes);
            dialog.dismiss();
            Toast.makeText(this, "头像已更换", Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }

    // 删除头像（恢复默认）
    private void deleteAvatar() {
        new AlertDialog.Builder(this)
                .setTitle("删除头像")
                .setMessage("确定要删除头像吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentAvatarRes = R.drawable.ic_default_avatar;
                        ivAvatar.setImageResource(currentAvatarRes);
                        Toast.makeText(HeadActivity.this, "头像已删除", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    // 保存用户信息（可以跳回上一个页面或显示保存成功）
    private void saveUserInfo() {
        // 可以将头像信息通过 Intent 返回
        Intent intent = new Intent();
        intent.putExtra("avatar_res", currentAvatarRes);
        setResult(RESULT_OK, intent);

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}