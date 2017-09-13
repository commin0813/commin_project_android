package com.commin.pro.gangwon.page.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.model.Model2Menu;
import com.commin.pro.gangwon.page.ApplicationProperty;
import com.commin.pro.gangwon.page.development.Page2Development;
import com.commin.pro.gangwon.page.energy.Page2Energy;
import com.commin.pro.gangwon.page.webview.Page2WebView;

import java.util.ArrayList;

/************************************
 * 처음에 그냥 리스트만있는 메뉴인지 알았는데
 * 알고보니 ExpendableListView를 사용한 메뉴더라구요.
 * 제가 잘못봤으니 그냥 해드렸습니다!
 * 라이브러리를 사용하지않고 만들었기때문에 많이 복잡합니다.
 * 다른 Activity에서도 재활용이 가능하게 만들었기때문입니다.
 */

public class CustomMenu extends LinearLayout {
    private ImageView iv_item_cancel_menu;
    Context context;

    public CustomMenu(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private class CustomMenuAdapter extends BaseExpandableListAdapter {
        private ArrayList<Model2Menu> items;
        private Context context;

        public CustomMenuAdapter(Context context, ArrayList<Model2Menu> items) {
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

        /***************************
         * 이 메서드는 리스트의 그룹을 그려주는 메서드입니다.
         * 그러니까 이앱에서는 에너지 역사, 에너지발전, 관련링크앱을 넣으면되겠지요.
         * 메뉴를 보시면 아이콘 + 그룹이름 으로 되어있는데
         * 아이콘을 각각 따른 아이콘으로 만들기위해서는 이곳에서 조건으로 분기하여 따로따로 이미지뷰를 설정하면되겠습니다.
         * @param groupPosition
         * @param isExpanded
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = (LinearLayout) inflater.inflate(R.layout.list_item_menu_group, null);
            }

            final TextView tv_use_date = (TextView) convertView.findViewById(R.id.tv_item_menu_group_name);
            final ImageView iv_item_menu_gorup_icon = (ImageView) convertView.findViewById(R.id.iv_item_menu_gorup_icon);//아이콘 ImageView입니다.


            /**************
             * 주석풀고 바꾸면됩니다.
             */
//            switch (groupPosition){
//                case 0: iv_item_menu_gorup_icon.setImageResource(R.drawable.icon_sun_fire);
//                    break;
//                case 1: iv_item_menu_gorup_icon.setImageResource(R.drawable.icon_sun_light);
//                    break;
//                case 2: iv_item_menu_gorup_icon.setImageResource(R.drawable.icon_water_fire);
//                    break;
//                default:iv_item_menu_gorup_icon.setImageResource(R.drawable.icon_wind_force);
//                    break;
//            }

            tv_use_date.setText(items.get(groupPosition).getGroup_name());

            return convertView;
        }


        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final Context context = parent.getContext();
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = (LinearLayout) inflater.inflate(R.layout.list_item_menu_child, null);
            }
            ArrayList<String> imsi = items.get(groupPosition).getChild_list();
            ListView lst_item_child_list = (ListView) convertView.findViewById(R.id.lst_item_child_list);
            lst_item_child_list.setDivider(null);
            ArrayAdapter2Menu adapter = new ArrayAdapter2Menu(context, R.layout.list_item_menu_child_list, imsi);
            lst_item_child_list.setAdapter(adapter);
            setListViewHeightBasedOnChildren(lst_item_child_list);
            adapter.notifyDataSetChanged();


            /***************
             * 이메뉴는 ExpandableListView에 또하나의 ListView가 들어가있는 형태입니다.
             * 각 리스트를 클릭하였을때 해당 TextView의 이름으로 비교하여
             * Activity를 열어줍니다.
             */
            lst_item_child_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final TextView menu_name = (TextView) view.findViewById(R.id.tv_item_menu_child_name);
                    String str = menu_name.getText().toString();
                    Intent intent = null;
                    if (str.equalsIgnoreCase(ApplicationProperty.energy[0])) {//개요
                        intent = new Intent(context, Page2Energy.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_SUMMARY);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.energy[1])) {//내용
                        intent = new Intent(context, Page2Energy.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_CONTENT);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.energy[2])) {//요약
                        intent = new Intent(context, Page2Energy.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_DETAIL_SUMMARY);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.energy[3])) {//r관련링크
                        intent = new Intent(context, Page2Energy.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_LINK);
                    }else if (str.equalsIgnoreCase(ApplicationProperty.energy2[0])) {//태양광
                        intent = new Intent(context, Page2Development.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_SUN_LIGHT);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.energy2[1])) {//태양열
                        intent = new Intent(context, Page2Development.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_SUN_FIRE);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.energy2[2])) {//풍 력
                        intent = new Intent(context, Page2Development.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_WIND_FORCE);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.energy2[3])) {//지열/수열
                        intent = new Intent(context, Page2Development.class);
                        intent.putExtra("tab", ApplicationProperty.CODE_WATER_FIRE);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.links[0])) {//동계 올림픽
                        intent = new Intent(context, Page2WebView.class);
                        intent.putExtra("url", ApplicationProperty.ADDR_PYEONGCHANG);
                    } else if (str.equalsIgnoreCase(ApplicationProperty.links[1])) {//강원사이버관광페이지
                        intent = new Intent(context, Page2WebView.class);
                        intent.putExtra("url", ApplicationProperty.ADDR_SIBER);
                    }
                    if (intent != null)
                        context.startActivity(intent);
                    if (context != ApplicationProperty.HOME_CONTEXT)
                        ((Activity) context).finish();

                }
            });

            return convertView;
        }

        /****************************
         * ListView의 크기를 계산해주는 메서드입니다.
         * 이 메서드는 유용하게 쓰이니까 가지고있다가 리스트뷰 사용할때 많이 사용하세요.
         * @param listView
         */
        public void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }


    }

    private ExpandableListView elst_menu;
    private int lastClickedPosition = 0;

    private void init() {
        inflate(getContext(), R.layout.item_menu, this);
        elst_menu = (ExpandableListView) findViewById(R.id.elst_menu);
        elst_menu.setGroupIndicator(null);
        elst_menu.setDivider(null);
        ArrayList<Model2Menu> arrayList = new ArrayList<Model2Menu>();
        Model2Menu model = null;

        String[] title = ApplicationProperty.title;
        ArrayList<String[]> sub_title = new ArrayList<>();
        String[] energy = ApplicationProperty.energy;
        String[] energy2 = ApplicationProperty.energy2;
        String[] links = ApplicationProperty.links;
        sub_title.add(0, energy);
        sub_title.add(1, energy2);
        sub_title.add(2, links);

        for (int i = 0; i < title.length; i++) {
            model = new Model2Menu();
            model.setGroup_name(title[i]);
            ArrayList<String> imsi = null;
            imsi = new ArrayList<>();
            for (int j = 0; j < sub_title.get(i).length; j++) {
                imsi.add(sub_title.get(i)[j]);
            }
            model.setChild_list(imsi);
            arrayList.add(model);
        }


        CustomMenuAdapter adapter = new CustomMenuAdapter(context, arrayList);
        elst_menu.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        /******************
         * 이 리스너를 등록하지않으면
         * 열어놓은 메뉴는 다시 닫지않는한 닫히지않습니다.
         * 다른 메뉴그룹을 클릭했을때 기존의 메뉴그룹을 닫고 열어주는 코딩입니다.
         */
        elst_menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                boolean isExpand = (!elst_menu.isGroupExpanded(i));
                elst_menu.collapseGroup(lastClickedPosition);
                if (isExpand) {
                    elst_menu.expandGroup(i);
                }
                lastClickedPosition = i;
                return true;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
