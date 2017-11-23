package me.xiaouc.be;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Mrt extends MainActivity {
Spinner spinner;
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrt);
        spinner=(Spinner)findViewById(R.id.spinner);
        imageView=(ImageView)findViewById(R.id.imageView);
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.mrt,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = spinner.getItemAtPosition(position).toString();
                switch (position+1){
                    case 1:
                        imageView.setImageResource(R.drawable.tpmrt);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.tymrt);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.ksmrt);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void clickBack(View v){
        Intent intent = new Intent();
        intent.setClass(Mrt.this,MainActivity.class);
        startActivity(intent);

    }

    }

