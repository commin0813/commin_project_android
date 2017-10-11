package commin.pro.diseasemanagement.page.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.page.diabetes.Page2Diabetes;
import commin.pro.diseasemanagement.page.gastritis.Page2Gastritis;
import commin.pro.diseasemanagement.page.hblood.Page2HighBlood;

public class Page2Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home_layout);
    }

    public void onClicked(View view){

        int view_id = view.getId();
        switch (view_id){
            case R.id.btn_high_blood : {
                startActivity(new Intent(Page2Home.this, Page2HighBlood.class));
                break;
            }

            case R.id.btn_diabetes : {
                startActivity(new Intent(Page2Home.this, Page2Diabetes.class));
                break;
            }

            case R.id.btn_gastritis : {
                startActivity(new Intent(Page2Home.this, Page2Gastritis.class));
                break;
            }
//
//            case R.id.btn_high_blood : {
//
//                break;
//            }


        }
    }
}
