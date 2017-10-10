package commin.pro.diseasemanagement.page.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.page.hblood.Page2HighBlood;

public class Page2Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home_layout);
    }

    public void onClicked(View view){
       startActivity(new Intent(Page2Home.this, Page2HighBlood.class));
    }
}
