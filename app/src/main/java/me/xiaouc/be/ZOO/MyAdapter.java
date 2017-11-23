package me.xiaouc.be.ZOO;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.xiaouc.be.R;

/**
 * Created by ROOT on 2017/11/22.
 */
class MyAdapter extends BaseAdapter {
    Context context;
    ZooInfo[] zooInfo;
    public MyAdapter(Context context,ZooInfo[] zooInfo){
        this.context = context;
        this.zooInfo = zooInfo;
    }
    @Override
    public int getCount() {
        return zooInfo.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View conveview, ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View v =inflater.inflate(R.layout.zooitem,null);
        TextView tv =(TextView)v.findViewById(R.id.textView);
        tv.setText(zooInfo[position].Name);
        ImageView img =(ImageView)v.findViewById(R.id.imageView);
        Picasso.with(context).load(zooInfo[position].ImageName).into(img);
        TextView tv2 =(TextView)v.findViewById(R.id.textView2);
        tv2.setText(zooInfo[position].Type);
        TextView tv3 =(TextView)v.findViewById(R.id.textView3);
        tv3.setText(zooInfo[position].Age);
        TextView tv4 =(TextView)v.findViewById(R.id.textView4);
        tv4.setText("收容位置 :    "+zooInfo[position].Resettlement);
        TextView tv5 =(TextView)v.findViewById(R.id.textView5);
        tv5.setText("電話 : "+zooInfo[position].Phone);




//        TextView nametv = (TextView) v.findViewById(R.id.nametv);
//        nametv.setText(zooInfo[position].Name);
//        TextView agetv = (TextView) v.findViewById(R.id.agetv);
//        agetv.setText(zooInfo[position].Age);
//        TextView varietytv = (TextView) v.findViewById(R.id.varietytv2);
//        varietytv.setText(zooInfo[position].Variety);
//        TextView buildtv = (TextView) v.findViewById(R.id.buildtv);
//        buildtv.setText(zooInfo[position].Build);
//        TextView hairtypetv = (TextView) v.findViewById(R.id.hairtypetv);
//        hairtypetv.setText(zooInfo[position].HairType);
//        TextView typetv = (TextView) v.findViewById(R.id.typetv);
//        typetv.setText(zooInfo[position].Type);
//        TextView resettlementtv2 = (TextView) v.findViewById(R.id.resettlementtv2);
//        resettlementtv2.setText(zooInfo[position].Resettlement);
//        TextView phonetv = (TextView) v.findViewById(R.id.phonetv);
//        phonetv.setText(zooInfo[position].Phone);
//        EditText editText = (EditText) v.findViewById(R.id.editText);
//        editText.setText(zooInfo[position].Note);
        return v;
        }
}