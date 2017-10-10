package commin.pro.diseasemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import commin.pro.diseasemanagement.page.Page2Home;

public class Application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, Page2Home.class));

        try {

            new Thread().sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish();
//        setContentView(R.layout.activity_application_layout);
    }
}
