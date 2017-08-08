package com.commin.pro.shinhancard.page.use;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.commin.pro.shinhancard.R;
import com.commin.pro.shinhancard.model.Model2Use;
import com.commin.pro.shinhancard.util.Util2Excel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Page2Use extends AppCompatActivity {


    private class Page2UseAdapter extends BaseExpandableListAdapter {
        private ArrayList<Model2Use> items;
        private Context context;

        public Page2UseAdapter(Context context,ArrayList<Model2Use> items) {
            this.items = items;
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return items.get(i) == null ? 0 : 1;
        }

        @Override
        public Object getGroup(int i) {
            return items.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return items.get(i);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }


        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = (RelativeLayout) inflater.inflate(R.layout.list_item_use_group, null);
            }
            TextView tv_use_date = (TextView) convertView.findViewById(R.id.tv_use_date);
            TextView tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            TextView tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            Model2Use model = items.get(groupPosition);
            tv_use_date.setText(model.getUse_date());
            tv_shop_name.setText(model.getShop_name());
            tv_price.setText(model.getPrice());

            return convertView;
        }


        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = (RelativeLayout) inflater.inflate(R.layout.list_item_use_child, null);
            }
            TextView tv_suc_number = (TextView) convertView.findViewById(R.id.tv_suc_number);
            TextView tv_how_use = (TextView) convertView.findViewById(R.id.tv_how_use);
            TextView tv_use_confirm_date = (TextView) convertView.findViewById(R.id.tv_use_confirm_date);
            TextView tv_check_date = (TextView) convertView.findViewById(R.id.tv_check_date);
            TextView tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);

            Model2Use model = items.get(groupPosition);
            tv_suc_number.setText(model.getSuc_number());
            tv_how_use.setText(model.getHow_use());
            tv_use_confirm_date.setText(model.getUse_confirm_date());
            tv_check_date.setText(model.getCheck_date());
            tv_shop_name.setText(model.getShop_name());


            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_use);
        createGUI();


    }

    private ExpandableListView lst_use;
    private ArrayList<Model2Use> arrayList;
    private Page2UseAdapter adapter;
//    private Button btn_query_data;
//    private Spinner sp_day;

    private void createGUI() {
        lst_use = (ExpandableListView) findViewById(R.id.lst_use);
        lst_use.setGroupIndicator(null);
        lst_use.setDivider(null);
        lst_use.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                lst_use.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        arrayList = new ArrayList<Model2Use>();
        arrayList = Util2Excel.getMapDataToExcel(Page2Use.this, 0);
        adapter = new Page2UseAdapter(Page2Use.this,arrayList);
        lst_use.setAdapter(adapter);



//        sp_day = (Spinner) findViewById(R.id.sp_day);
//
//
//        btn_query_data = (Button) findViewById(R.id.btn_query_data);
//        btn_query_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int sheetNum = sp_day.getSelectedItemPosition();
//                switch (sheetNum) {
//                    case 0:
//                        sheetNum = 4;
//                        break;
//                    case 1:
//                        sheetNum = 3;
//                        break;
//                    case 2:
//                        sheetNum = 2;
//                        break;
//                    case 3:
//                        sheetNum = 1;
//                        break;
//                    case 4:
//                        sheetNum = 0;
//                        break;
//                }
//                try {
//                    arrayList.clear();
//
//                    arrayList = Util2Excel.getMapDataToExcel(Page2Use.this, sheetNum);
//
//                    adapter.notifyDataSetChanged();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });


    }

    public void back(View view) {
        onBackPressed();
    }
}
