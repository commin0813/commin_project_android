package com.commin.pro.comminproject.page.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.commin.pro.comminproject.R;
import com.commin.pro.comminproject.model.Model2Comment;

import java.util.ArrayList;

/**
 * Created by user on 2017-07-26.
 */
public class ArrayAdapter2Content extends ArrayAdapter<Model2Comment>{

    private Context contex;
    private int resource;
    private  ArrayList<Model2Comment> items;
    public ArrayAdapter2Content(Context context, int resource, ArrayList<Model2Comment> items){
        super(context,resource,items);
        this.contex = context;
        this.resource = resource;
        this.items = items;
    }

    class ViewHolder {


    }
    TextView tv_comment_name;
    TextView tv_comment_contents;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View view =convertView;
        final Model2Comment model2Comment = items.get(position);
        if(model2Comment == null){
            return null;
        }
        if(view  == null ){
            LayoutInflater inflater = (LayoutInflater)contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);


            tv_comment_contents = (TextView)view.findViewById(R.id.tv_comment_contents);
            tv_comment_name = (TextView)view.findViewById(R.id.tv_comment_name);
            tv_comment_name.setText(model2Comment.getUser_name());
            tv_comment_contents.setText(model2Comment.getComment_contents());
        }
//        final ViewHolder viewHolder = (ViewHolder) view.getTag();




        return view;
    }
}
