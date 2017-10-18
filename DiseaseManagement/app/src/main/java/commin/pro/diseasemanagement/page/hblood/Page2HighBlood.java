package commin.pro.diseasemanagement.page.hblood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.page.ApplicationProperty;
import commin.pro.diseasemanagement.page.complete.Page2Complete;
import commin.pro.diseasemanagement.util.UtilCustomDialog;
import commin.pro.diseasemanagement.util.UtilDialog;

public class Page2HighBlood extends AppCompatActivity {
    private int cnt_checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_high_blood_layout);
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            this.cnt_checked++;
        } else {
            this.cnt_checked--;
            if (this.cnt_checked < 0) {
                this.cnt_checked = 0;
            }
        }
    }


    public void goResult(View view) {
        boolean isComplete = false;
        final Intent intent = new Intent(Page2HighBlood.this, Page2Complete.class);
        if (this.cnt_checked >= 0 && this.cnt_checked <= 3) {
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_RESULT, ApplicationProperty.STRING_VALUE_COMPLETE_RESULT_NORMAL);
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_EXPLAIN, ApplicationProperty.STRING_VALUE_COMPLETE_EXPLAIN_NORMAL);
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_BUTTON_TYPE, ApplicationProperty.INTEGER_VALUE_COMPLETE_BUTTON_TYPE_NORMAL);
        } else if (this.cnt_checked >= 4 && this.cnt_checked <= 6) {
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_RESULT, ApplicationProperty.STRING_VALUE_COMPLETE_RESULT_ABNORMAL_HIGH_1);
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_EXPLAIN, ApplicationProperty.STRING_VALUE_COMPLETE_EXPLAIN_ABNORMAL);
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_BUTTON_TYPE, ApplicationProperty.INTEGER_VALUE_COMPLETE_BUTTON_TYPE_ARROW);
        } else if (this.cnt_checked >= 7 && this.cnt_checked <= 9) {
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_RESULT, ApplicationProperty.STRING_VALUE_COMPLETE_RESULT_ABNORMAL_HIGH_2);
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_EXPLAIN, ApplicationProperty.STRING_VALUE_COMPLETE_EXPLAIN_ABNORMAL);
            intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_BUTTON_TYPE, ApplicationProperty.INTEGER_VALUE_COMPLETE_BUTTON_TYPE_ARROW);
        }

        intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_TYPE,ApplicationProperty.HIGH_BLOOD_TYPE);


        //Dialog
        UtilDialog.openCustomDialogConfirm(Page2HighBlood.this, "검사완료", "검사 결과 " + this.cnt_checked + "개가 체크되었습니다. 맞습니까?", "예", "아니오", new UtilCustomDialog.OnClickListener() {
            @Override
            public void onClick() {
                startActivity(intent);
                finish();
            }
        }, new UtilCustomDialog.OnClickListener() {
            @Override
            public void onClick() {
                return;
            }
        });
    }
}
