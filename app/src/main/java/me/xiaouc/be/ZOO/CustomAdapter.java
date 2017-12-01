package me.xiaouc.be.ZOO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import me.xiaouc.be.R;

/**
 * Created by ROOT on 2017/11/30.
 */
public class CustomAdapter extends BaseAdapter implements Filterable {
    List<String> item;
    List<String> originalitem;
    private LayoutInflater mLayout;
    Context context;
    ZooInfo[] zooInfo;

    public CustomAdapter(Context context, List<String> mList) {
        mLayout = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.item = mList;
        this.context = context;
        this.zooInfo = zooInfo;
    }

    @Override
    public int getCount() {
        return item.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // R.layout.custom_layout 是你自己創的自訂layout( 可參考下面)
        View v = mLayout.inflate(R.layout.zooitem,parent,false);
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

        return v;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString();
                FilterResults result = new FilterResults();
                if(originalitem == null){
                    synchronized (this){
                        originalitem = new ArrayList<String>(item);
                        // 若originalitem 沒有資料，會複製一份item的過來.
                    }
                }
                if(constraint != null && constraint.toString().length()>0){
                    ArrayList<String> filteredItem = new ArrayList<String>();
                    for(int i=0;i<originalitem.size();i++){
                        String title = originalitem.get(i).toString();
                        if(title.contains(constraint)){
                            filteredItem.add(title);
                        }
                    }
                    result.count = filteredItem.size();
                    result.values = filteredItem;
                }else{
                    synchronized (this){
                        ArrayList<String> list = new ArrayList<String>(originalitem);
                        result.values = list;
                        result.count = list.size();

                    }
                }

                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                item = (ArrayList<String>)results.values;
                if(results.count>0){
                    notifyDataSetChanged();
                }else{
                    notifyDataSetInvalidated();
                }
            }
        };

        return filter;
    }
}