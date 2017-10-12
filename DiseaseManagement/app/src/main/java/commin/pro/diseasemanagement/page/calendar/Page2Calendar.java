package commin.pro.diseasemanagement.page.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import commin.pro.diseasemanagement.Application;
import commin.pro.diseasemanagement.R;
import commin.pro.diseasemanagement.model.Model2Calendar;
import commin.pro.diseasemanagement.page.ApplicationProperty;
import commin.pro.diseasemanagement.util.UtilDialog;

public class Page2Calendar extends AppCompatActivity {
    private CalendarView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_calendar_layout);
        createGUI();
    }

    private void createGUI() {
        cv = ((CalendarView) findViewById(R.id.calendar_view));

        cv.setEventHandler(new CalendarView.EventHandler() {//interface를 만들어서 등록하면 observer 패턴처럼 이용 할 수있습니다.
            @Override
            public void onDayLongPress(Date date) {//달력화면의 grid를 long 클릭 하면 이 메서드가 실행됩니다.
                DateFormat df = SimpleDateFormat.getDateInstance();
                UtilDialog.showToast(Page2Calendar.this, df.format(date));
            }

            @Override
            public void onDayClick(Date date) {//달력화면의 grid를 한번 클릭하면 이메서드가 실행됩니다.
                Date today = new Date();
                int compare = today.compareTo(date);
                if (compare > 0) {
                    //today 이전
                    Model2Calendar model = new Model2Calendar();
                    model.setDate(date);

                    Intent intent = new Intent(Page2Calendar.this, Dialog2Calendar.class);
                    intent.putExtra(ApplicationProperty.STRING_KEY_VALUE_CALANDAR_MODEL, model);

                    startActivityForResult(intent, ApplicationProperty.INTEGER_VALUE_CALANDAR_RESULT_CODE);

                } else if (compare < 0) {
                    //today 이후
                    UtilDialog.showToast(Page2Calendar.this, "선택한 날짜에는 기록 할 수 없습니다.");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cv.updateCalendar();
    }
}
