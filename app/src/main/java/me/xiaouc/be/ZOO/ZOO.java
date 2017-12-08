package me.xiaouc.be.ZOO;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.util.ArrayList;
import me.xiaouc.be.MainActivity;
import me.xiaouc.be.R;

public class ZOO extends AppCompatActivity {
    ListView lv;
    TextView tv,tvv;
    MyAdapter adapter;
    Myzoo z;
    ProgressBar progressBar;
    SearchView searchView; //搜尋器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo);
        lv = (ListView) findViewById(R.id.listview);
        searchView=(SearchView)findViewById(R.id.searchView);
        tv = (TextView)findViewById(R.id.text1);
        tvv = (TextView)findViewById(R.id.text2);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(false);
        setTitle("動物認養資訊");
        searchView.setQueryHint("輸入犬貓搜尋");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        //擷取api 用gson解析
        RequestQueue queue = Volley.newRequestQueue(ZOO.this);
        StringRequest request = new StringRequest("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=f4a75ba9-7721-4363-884d-c3820b0b917c",
                new Response.Listener<String>() {
        //成功
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG","成功");
                        Gson gson = new Gson();
                         z = gson.fromJson(response, Myzoo.class);
                        Log.d("TAG", "解析");
                        adapter = new MyAdapter(ZOO.this, z.result.results);
                        //讀取中圖像文字
                        progressBar.setVisibility(View.VISIBLE);
                        tv.setVisibility(View.VISIBLE);
                        tvv.setVisibility(View.VISIBLE);
                        //

                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        //隱藏讀取中圖像文字
                        progressBar.setVisibility(View.INVISIBLE);
                        tv.setVisibility(View.INVISIBLE);
                        tvv.setVisibility(View.INVISIBLE);
                        //
                    }
                }, new Response.ErrorListener() {
         //失敗
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG","失敗");
                Log.d("ZOO", "Error" + error.toString());
            }
        });
        queue.add(request);
        queue.start();
        Log.d("TAG","輸出");
        lv.setOnItemClickListener(listener);
        lv.setTextFilterEnabled(true);
   }
AdapterView.OnItemClickListener listener =new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
};





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater =getMenuInflater();
//        inflater.inflate(R.menu.action,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action:
//                break;
//        }


        return super.onOptionsItemSelected(item);
    }

    //返回鍵
    public void clickBack(View v){
        Intent intent = new Intent();
        intent.setClass(ZOO.this,MainActivity.class);
        startActivity(intent);

    }

}



