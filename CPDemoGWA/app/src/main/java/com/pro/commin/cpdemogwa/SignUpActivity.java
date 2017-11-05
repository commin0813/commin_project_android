package com.pro.commin.cpdemogwa;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.pro.commin.cpdemogwa.R.id.ButtonQuery;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    @InjectView(R.id.editTextEmail)
    EditText editTextEmail;
    @InjectView(R.id.editTextPassword)
    EditText editTextPassword;
    @InjectView(R.id.editTextName)
    EditText editTextName;
    @InjectView(R.id.ButtonSignUp)
    Button ButtonSignUp;
    @InjectView(R.id.ButtonQuery)
    Button ButtonQuery;
    @InjectView(R.id.spinnerCity)
    Spinner spinnerCity;
    @InjectView(R.id.textViewQuery)
    TextView textViewQuery;

    ArrayAdapter<String> adapter;

    User user;

    ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init() {
        ButterKnife.inject(this);
        resolver = getContentResolver();

        // Create an Object of User;
        user = new User();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.add("--select city --");//0
        adapter.add("korea");
        adapter.add("america");
        adapter.add("africa");
        adapter.add("pune");
        adapter.add("china"); // n-1

        spinnerCity.setAdapter(adapter);
        spinnerCity.setOnItemSelectedListener(this);
        ButtonSignUp.setOnClickListener(this);
        ButtonQuery.setOnClickListener(this);

    }

    void insertUser() {
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, user.getName());
        values.put(Util.COL_EMAIL, user.getEmail());
        values.put(Util.COL_PASSWORD, user.getPassword());
        values.put(Util.COL_CITY, user.getCity());

        Uri uri = resolver.insert(Util.USER_URI, values);
        Toast.makeText(this, user.getName() + "registerd seccessfully with id " + uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
        clearField();
        queryUser();
    }

    void queryUser() {
        String[] projection = new String[]{
                Util.COL_ID,
                Util.COL_NAME,
                Util.COL_EMAIL,
                Util.COL_PASSWORD,
                Util.COL_CITY
        };
       Cursor cursor =  resolver.query(Util.USER_URI,projection,null,null,Util.COL_NAME + " ASC" );
        cursor.moveToFirst();
        String str = "result \n";
        do{
            int _id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
            String name =cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
            String email  = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(Util.COL_PASSWORD));
            String city = cursor.getString(cursor.getColumnIndex(Util.COL_CITY));

            str += _id+"--" +name+"--"+email+"--"+password+"--"+city+"\n";
        }while(cursor.moveToNext());
        textViewQuery.setText(str);
        Log.d("aa","a");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ButtonSignUp: {

                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                insertUser();
            }
            break;

            case R.id.ButtonQuery: {
                queryUser();
            }
            break;
        }
    }

    public void clearField() {
        editTextEmail.setText("");
        editTextName.setText("");
        editTextPassword.setText("");
        spinnerCity.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String city = adapter.getItem(i);
//        user.city = city;
        user.setCity(city);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
