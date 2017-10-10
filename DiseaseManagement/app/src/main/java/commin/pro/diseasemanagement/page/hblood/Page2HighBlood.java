package commin.pro.diseasemanagement.page.hblood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import commin.pro.diseasemanagement.R;

public class Page2HighBlood extends AppCompatActivity {
    private int cnt_checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_high_blood_layout);
    }


    public void onCheckboxClicked(View view) {
        Log.d("aaaaaaaa",this.cnt_checked + "bb");
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.cb_1:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_2:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_3:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_4:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_5:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_6:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_7:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_8:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
            case R.id.cb_9:
                if (checked) {
                    this.cnt_checked++;
                } else {
                    this.cnt_checked--;
                }
                break;
        }

       Log.d("aaaaaaaa",this.cnt_checked + "bb");
    }
}
