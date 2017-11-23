package me.xiaouc.be.ZOO;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.xiaouc.be.MainActivity;
import me.xiaouc.be.Mrt;
import me.xiaouc.be.R;

public class ZOO extends AppCompatActivity {
    ListView lv;
    Context context;
    MyAdapter adapter;

    ArrayList<ZooInfo> TYPE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo);
        lv = (ListView) findViewById(R.id.listview);
        RequestQueue queue = Volley.newRequestQueue(ZOO.this);
        StringRequest request = new StringRequest("http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=f4a75ba9-7721-4363-884d-c3820b0b917c",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        final Myzoo z = gson.fromJson(response, Myzoo.class);
                        Log.d("ZOO", z.result.results[0].Variety);
                        adapter = new MyAdapter(ZOO.this, z.result.results);

                        for (int i =0;i>response.length();i++){
                            if(z.result.results[0].Type=="狗"){


                                ArrayList<ZooInfo> TYPE;
                            }else if( z.result.results[0].Type=="貓"){


                            }else{


                            }
                        }
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Intent it = new Intent(ZOO.this, ZooMessageActivity.class);
                                startActivity(it);
                           }
                       });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ZOO", "Error" + error.toString());
            }
        });
        queue.add(request);
        queue.start();
    }
    public void clickBack(View v){
        Intent intent = new Intent();
        intent.setClass(ZOO.this,MainActivity.class);
        startActivity(intent);

    }

}



