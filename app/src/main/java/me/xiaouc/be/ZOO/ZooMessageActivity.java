package me.xiaouc.be.ZOO;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import me.xiaouc.be.R;

public class ZooMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_message);
        TextView nametv = (TextView) findViewById(R.id.nametv);
        TextView agetv = (TextView) findViewById(R.id.agetv);
        TextView varietytv = (TextView) findViewById(R.id.varietytv2);
        TextView buildtv = (TextView) findViewById(R.id.buildtv);
        TextView hairtypetv = (TextView) findViewById(R.id.hairtypetv);
        TextView typetv = (TextView) findViewById(R.id.typetv);
        TextView resettlementtv2 = (TextView) findViewById(R.id.resettlementtv2);
        TextView phonetv = (TextView) findViewById(R.id.phonetv);
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent it = getIntent();
      //  id = it.getIntExtra("name", -1);

    }
}
