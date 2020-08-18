package com.example.androidbt5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_PLAYER_NAME = "extra.PLAYER_NAME";
    public static final int REQUEST_CODE = 1;

    private EditText editPlayerName;
    private Button btnStart;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
    }

    private void initViews() {
        editPlayerName = findViewById(R.id.edt_playername);
        btnStart = findViewById(R.id.btn_start);
        tvTitle = findViewById(R.id.tv_title);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String playerName = editPlayerName.getText().toString();
        if (playerName.isEmpty()) {
            Toast.makeText(this, "Không được để trống tên", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_PLAYER_NAME, playerName);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE == requestCode) {
            if (resultCode == Activity.RESULT_CANCELED) {
                if (data.getBooleanExtra(MainActivity.EXTRA_RESULT, false)) {
                    tvTitle.setText("Bạn thắng");
                } else {
                    tvTitle.setText("Bạn thua");
                }
            }
        }
    }

}