package commin.pro.diseasemanagement.page.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import java.util.Date;
import java.util.HashMap;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.dao.DBHelper;
import commin.pro.diseasemanagement.model.Model2Calendar;
import commin.pro.diseasemanagement.page.ApplicationProperty;
import commin.pro.diseasemanagement.util.UtilDialog;

public class Dialog2Calendar extends Activity {
    private Model2Calendar model = null;
    private EditText ed_dialog_relaxation, ed_dialog_shrink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        setContentView(R.layout.activity_dialog_calendar);
        init();
    }

    private void init() {
        ed_dialog_shrink = (EditText) findViewById(R.id.ed_dialog_shrink);
        ed_dialog_relaxation = (EditText) findViewById(R.id.ed_dialog_relaxation);

        Intent intent = getIntent();
        model = (Model2Calendar) intent.getSerializableExtra(ApplicationProperty.STRING_KEY_VALUE_CALANDAR_MODEL);

    }

    public void save(View view) {
//        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "disease_management2.db", null, 1);
        final DBHelper dbHelper =ApplicationProperty.dbHelper;

        if (ed_dialog_shrink.getText().toString().equals("")) {
            UtilDialog.showToast(Dialog2Calendar.this, "수축 값을 입력하세요");
            return;
        }
        if (ed_dialog_relaxation.getText().toString().equals("")) {
            UtilDialog.showToast(Dialog2Calendar.this, "이완 값을 입력하세요");
            return;
        }

        model.setShrink(Integer.parseInt(ed_dialog_shrink.getText().toString()));
        model.setRelaxation(Integer.parseInt(ed_dialog_relaxation.getText().toString()));
        model.setText();

        try {
            dbHelper.insert(model);
            UtilDialog.showToast(Dialog2Calendar.this, "저장완료 : " + model.getRelaxation() + " : " + model.getShrink() + ":" + model.getText());
        } catch (Exception e) {
            Log.w("try", e);
        }

        setResult(1);
        finish();
    }
}
