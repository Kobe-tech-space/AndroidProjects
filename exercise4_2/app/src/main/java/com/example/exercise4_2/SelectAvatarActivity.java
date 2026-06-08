package com.example.exercise4_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class SelectAvatarActivity extends AppCompatActivity {

    private GridView gvAvatars;
    private int[] avatarResources = {
            R.drawable.ic_avatar1,
            R.drawable.ic_avatar2,
            R.drawable.ic_avatar3,
            R.drawable.ic_avatar4,
            R.drawable.ic_avatar5,
            R.drawable.ic_avatar6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        gvAvatars = findViewById(R.id.gv_avatars);

        AvatarAdapter adapter = new AvatarAdapter(this, avatarResources);
        gvAvatars.setAdapter(adapter);

        gvAvatars.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent();
            intent.putExtra("selected_avatar", avatarResources[position]);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}