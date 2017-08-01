package com.commin.pro.comminproject.page.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.commin.pro.comminproject.R;
import com.commin.pro.comminproject.model.Model2Comment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 2017-07-25.
 */
public class Frag2Content extends Fragment{
    private String LOG_TAG = "Frag2Content";
    private SlidingUpPanelLayout layout;
    private ImageView iv_arrow;
    private Animation anim_down, anim_up,anim_move_down, anim_move_up;
    private TextView tv_content;
    private ListView lsv_list_view;
    private View header,footer;

    private ArrayAdapter2Content adapter ;

    private int arrow_status = 0; /** 0 : UP 1:DOWN ***/

    public Frag2Content() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public interface OnBackPressedListener{
        void onBackPressed();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (SlidingUpPanelLayout) inflater.inflate(R.layout.page_main_frag_content, container, false);
        init();
        return layout;
    }

    private void init() {
        iv_arrow = (ImageView)layout.findViewById(R.id.iv_arrow);
        tv_content = (TextView)layout.findViewById(R.id.tv_content);
        lsv_list_view = (ListView)layout.findViewById(R.id.lsv_list_view);

        setAnim();
        setSlidingPanel();
        setList();

    }
    private ArrayList<Model2Comment> item_list;

    private void setList(){

        item_list = new ArrayList<Model2Comment>();
        adapter = new ArrayAdapter2Content(getActivity().getApplicationContext(),R.layout.item_comment_header,item_list);

        lsv_list_view.setDivider(null);
        lsv_list_view.setAdapter(adapter);

        setItem_imsi();

    }

    private void setItem_imsi(){
        Model2Comment model = null;
        for(int i = 0 ; i <10 ; i++){
            model = new Model2Comment();
            model.setComment_contents("안녕하세요, 이것은 테스트 댓글입니다. :: "+i);
            model.setCreate_time(new Date());
            model.setUser_name("잘생긴형민");
            model.setComment_private_num(i);
            model.setDevice_id(i*10);
            item_list.add(model);
        }
        adapter.notifyDataSetChanged();

    }

    private void setSlidingPanel(){
        layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.d(LOG_TAG,"onPanelSlide"+ slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.d(LOG_TAG,"onPanelStateChanged" + previousState +"--->"+newState);

                if(newState.equals(SlidingUpPanelLayout.PanelState.ANCHORED)){
                    setAnimation(anim_move_up,tv_content);
                }else if(newState.equals(SlidingUpPanelLayout.PanelState.COLLAPSED)){
                    setAnimation(anim_move_down,tv_content);
                }


                if(newState.equals(SlidingUpPanelLayout.PanelState.COLLAPSED)){
                    if(arrow_status == 0){
                        return;
                    }
                    setAnimation(anim_up,iv_arrow);
                    arrow_status = 0;
                }else if(newState.equals(SlidingUpPanelLayout.PanelState.EXPANDED)) {
                    if(arrow_status == 1){
                        return;
                    }
                    setAnimation(anim_down,iv_arrow);
                    arrow_status = 1;
                }


            }
        });
        layout.setAnchorPoint(0.7f);
        layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        layout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }

    private void setAnimation(Animation animation, final View image){
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                image.setImageResource(R.drawable.below_shadow);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        image.startAnimation(animation);
    }

    private void setAnim(){
        anim_down = AnimationUtils.loadAnimation(
                getActivity().getApplicationContext(),
                R.anim.rotate_anim_down);

        anim_up = AnimationUtils.loadAnimation(
                getActivity().getApplicationContext(),
                R.anim.rotate_anim_up);

        anim_move_up = AnimationUtils.loadAnimation(
                getActivity().getApplicationContext(),
                R.anim.move_anim_up);

        anim_move_down = AnimationUtils.loadAnimation(
                getActivity().getApplicationContext(),
                R.anim.move_anim_down);
    }
}
