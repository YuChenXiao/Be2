package me.xiaouc.be.ZOO;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
    MyAdapter adapter;
    Spinner spinner;

    ArrayList<ZooInfo>dogarr ;
    ArrayList<ZooInfo>catarr ;
    ArrayList<ZooInfo>otherarr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo);
        lv = (ListView) findViewById(R.id.listview);
        spinner=(Spinner)findViewById(R.id.spinner6);

        //擷取api 用gson解析
        RequestQueue queue = Volley.newRequestQueue(ZOO.this);
        final StringRequest request = new StringRequest("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=f4a75ba9-7721-4363-884d-c3820b0b917c",
                new Response.Listener<String>() {
        //成功
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG","成功");
                        Gson gson = new Gson();
                        Myzoo z = gson.fromJson(response, Myzoo.class);
                        Log.d("ZOO", "解析");
                        adapter = new MyAdapter(ZOO.this, z.result.results);

//                        for (int i=0;i<response.length();i++){
//                            Log.d("TAG","迴圈正常");
//                            if(z.result.results[i].Type.equals("犬")){
//                                Log.d("TAG","犬");
//                                dogarr.add(z.result.results[i]);
//                            }else if( z.result.results[i].Type.equals("貓")){
//                                Log.d("TAG","貓");
//                                catarr.add(z.result.results[i]);
//                            }else{
//                                Log.d("TAG","其他");
//                                otherarr.add(z.result.results[i]);
//                            }
//                        }
                        lv.setAdapter(adapter);

//                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?>
//                                Intent it = new Intent(ZOO.this, ZooMessageActivity.class);
//                                startActivity(it);
//                           }
//                       });
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
    //spinner 選單
    final ArrayAdapter adapters = ArrayAdapter.createFromResource(this,R.array.zoo,android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapters);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = spinner.getItemAtPosition(position).toString();
                Log.d("TAG","選單正常");
                switch (position + 1) {
                    case 1:

                        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        Log.d("TAG","選單1正常");
                        break;
                    case 2:

                        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        Log.d("TAG","選單2正常");
                        break;
                    case 3:

                        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        Log.d("TAG","選單3正常");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
   }

    //返回鍵
    public void clickBack(View v){
        Intent intent = new Intent();
        intent.setClass(ZOO.this,MainActivity.class);
        startActivity(intent);

    }

}



