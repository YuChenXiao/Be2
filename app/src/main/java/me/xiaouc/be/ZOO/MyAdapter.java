package me.xiaouc.be.ZOO;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.xiaouc.be.R;

/**
 * Created by ROOT on 2017/11/22.
 */
class MyAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<ZooInfo> zooInfo;
    ArrayList<ZooInfo> orgzooInfo;
    LayoutInflater inflater;
    int k =0;
    public MyAdapter(Context context,List<ZooInfo> zooInfo){
        this.context = context;
        this.zooInfo = zooInfo;
        inflater =LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return zooInfo.size();
    }

    @Override
    public ZooInfo getItem(int i) {
        return zooInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        inflater =LayoutInflater.from(context);
        view = inflater.inflate(R.layout.zooitem,viewGroup,false);
        holder =new ViewHolder();
        holder.Resettlement=(TextView) view.findViewById(R.id.textView4);
        holder.Phone=(TextView) view.findViewById(R.id.textView5);
        holder.Type=(TextView) view.findViewById(R.id.textView2);
        holder.Name=(TextView) view.findViewById(R.id.textView);
        holder.Age=(TextView) view.findViewById(R.id.textView3);
        holder.Note=(TextView)view.findViewById(R.id.textViewnote);
        holder.img =(ImageView)view.findViewById(R.id.imageView);
         Picasso.with(context).load(zooInfo.get(position).ImageName).into(holder.img);
        view.setTag(holder);
        if (position!=0) {
            holder.Name.setText(zooInfo.get(position).Name);
            holder.Resettlement.setText(zooInfo.get(position).Resettlement);
            holder.Age.setText(zooInfo.get(position).Age);
            holder.Type.setText(zooInfo.get(position).Type);
            holder.Note.setText(zooInfo.get(position).Note);
            holder.Phone.setText(zooInfo.get(position).Phone);
        }

        return view;
        }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                charSequence = charSequence.toString();
                Log.d("TAG","FILTER"+charSequence);
                FilterResults result =new FilterResults();
//                orgzooInfo =new ArrayList<ZooInfo>(zooInfo);
                if (orgzooInfo == null) {
                    synchronized (this) {
                        orgzooInfo =new ArrayList<ZooInfo>(zooInfo);
                    }
                }
                if (charSequence != null&& charSequence.toString().length()>0) {
                    List<ZooInfo> filteredItem = new ArrayList<ZooInfo>();
                    for (int i =0;i<orgzooInfo.size();i++) {
                        String title = orgzooInfo.get(i).Type+orgzooInfo.get(i).Phone+orgzooInfo.get(i).Age+orgzooInfo.get(i).Name+orgzooInfo.get(i).Resettlement;
                        if (title.contains(charSequence)) {
                            Log.d("TAG",i+":"+title);
                            filteredItem.add(orgzooInfo.get(i));
                        }
                    }
                    result.count = filteredItem.size();
                    result.values = filteredItem;
                }else {
                    synchronized (this){
                        Log.d("TAG","synchronized");
                        List<ZooInfo>list =new ArrayList<ZooInfo>(orgzooInfo);
                        result.values=list;
                        result.count=list.size();
                    }
                }
                Log.d("count",""+result.count);
                Log.d("results",""+result.values.toString());
                return result;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                zooInfo = (ArrayList<ZooInfo>) filterResults.values;
                for (int j = 0; j < zooInfo.size(); j++) {
                    Log.d("TAG", j + ":" + orgzooInfo.get(j).Type);
                    if (filterResults.count > 0) {
                        notifyDataSetChanged();
                        Log.d("TAG", "NDS" + orgzooInfo.get(j).Resettlement);
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            }
        };
        Log.d("TAG","結束");
        return filter;
    }
    static class  ViewHolder{
        TextView Resettlement;
        TextView Phone;
        TextView Age;
        TextView Type;
        TextView Name;
        ImageView img;
        TextView Note;
    }
}