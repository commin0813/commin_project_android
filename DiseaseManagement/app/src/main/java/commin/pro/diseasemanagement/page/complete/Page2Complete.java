package commin.pro.diseasemanagement.page.complete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.page.ApplicationProperty;
import commin.pro.diseasemanagement.page.manage_highblood.Page2Manage_Highblood;

public class Page2Complete extends AppCompatActivity {
    private TextView tv_complete_result, tv_complete_explain, tv_complete_result2;
    private Button btn_complete_finish, btn_complete_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_complete_layout);
        init();
        createGUI();
    }

    private void init() {
        tv_complete_result = (TextView) findViewById(R.id.tv_complete_result);
        tv_complete_result2 = (TextView) findViewById(R.id.tv_complete_result2);
        tv_complete_explain = (TextView) findViewById(R.id.tv_complete_explain);

        btn_complete_finish = (Button) findViewById(R.id.btn_complete_finish);
        btn_complete_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_complete_arrow = (Button) findViewById(R.id.btn_complete_arrow);
        btn_complete_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                startActivity(new Intent(Page2Complete.this, Page2Manage_Highblood.class));
            }
        });
    }

    private void createGUI() {
        Intent intent = getIntent();
        String result = intent.getStringExtra(ApplicationProperty.STRING_KEY_VALUE_RESULT);
        String result_detail = intent.getStringExtra(ApplicationProperty.STRING_KEY_VALUE_RESULT_DETAIL);
        String explain = intent.getStringExtra(ApplicationProperty.STRING_KEY_VALUE_EXPLAIN);
        int btn_type = intent.getIntExtra(ApplicationProperty.STRING_KEY_VALUE_BUTTON_TYPE, 0);

        if (result_detail != null) {
            tv_complete_result2.setText(result_detail);
        }

        tv_complete_result.setText(result);
        tv_complete_explain.setText(explain);

        if (btn_type == ApplicationProperty.INTEGER_VALUE_COMPLETE_BUTTON_TYPE_NORMAL) {
            btn_complete_finish.setVisibility(View.VISIBLE);
            btn_complete_arrow.setVisibility(View.GONE);
        } else if (btn_type == ApplicationProperty.INTEGER_VALUE_COMPLETE_BUTTON_TYPE_ARROW) {
            btn_complete_finish.setVisibility(View.GONE);
            btn_complete_arrow.setVisibility(View.VISIBLE);
        }

    }
}
