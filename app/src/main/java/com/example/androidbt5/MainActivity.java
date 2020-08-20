package com.example.androidbt5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_RESULT = "extra.RESULT";
    private static final int[] idArray = {R.id.tv_box_1, R.id.tv_box_2, R.id.tv_box_3, R.id.tv_box_4, R.id.tv_box_5, R.id.tv_box_6, R.id.tv_box_7, R.id.tv_box_8, R.id.tv_box_9};
    private Button[] buttons = new Button[idArray.length];
    private Button button;
    private TextView tvNamePlayer;
    private TextView tvTimes;
    private int time = 5;
    private int rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        for (int i = 0; i < idArray.length; i++) {
            buttons[i] = findViewById(idArray[i]);
            buttons[i].setOnClickListener(this);
        }
        String namePlayer = getIntent().getStringExtra(MenuActivity.EXTRA_PLAYER_NAME);
        tvNamePlayer = findViewById(R.id.tv_player_name);
        tvNamePlayer.setText("Tên người chơi: " + namePlayer);
        tvTimes = findViewById(R.id.tv_times);
        setTimes();
    }

    @Override
    public void onClick(View view) {
        button = findViewById(view.getId());
        if (time > 0) {
            Random random = new Random();
            int num = random.nextInt(11);
            button.setBackgroundColor(Color.parseColor("#00B10E"));
            button.setText(Integer.toString(num));
            rs += num;
            time--;
            setTimes();
            if (time == 0) {
                if (youWin()) {
                    Toast.makeText(this, "Bạn thắng", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Bạn thua", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (time > 0) {
            Toast.makeText(this, "Bạn không thể thoát khi chưa chơi xong", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra(EXTRA_RESULT, youWin());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }

    private void setTimes() {
        tvTimes.setText("Times: " + time);
    }

    private boolean youWin() {
        if (time == 0 && rs > 40) {
            return true;
        }
        return false;
    }
}