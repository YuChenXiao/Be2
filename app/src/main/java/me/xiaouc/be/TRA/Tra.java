package me.xiaouc.be.TRA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.xiaouc.be.MainActivity;
import me.xiaouc.be.R;

public class Tra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra);
    }
    public void clickBack(View v){
        Intent intent = new Intent();
        intent.setClass(Tra.this,MainActivity.class);
        startActivity(intent);

    }
}
