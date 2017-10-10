package commin.pro.diseasemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import commin.pro.diseasemanagement.page.home.Page2Home;

public class Application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {

            new Thread().sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, Page2Home.class));
        finish();
//        setContentView(R.layout.activity_application_layout);
    }
}
