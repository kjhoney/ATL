package com.example.alltimeline;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modify_User_Event extends AppCompatActivity {
    private DBHelper mDbOpenHelper;
    private Button modify_event_year;
    private EditText modify_event_name;
    private EditText modify_event_memo;
    private int Category_id;
    private int Event_id;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            modify_event_year.setText(year+"");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_event);
        setTitle("Event 편집");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Category_id = getIntent().getIntExtra("c_id", 1);
        Event_id = getIntent().getIntExtra("id", 1);
        modify_event_year = (Button)findViewById(R.id.modify_event_year);
        modify_event_name = (EditText)findViewById(R.id.modify_event_name);
        modify_event_memo = (EditText)findViewById(R.id.modify_event_memo);

        mDbOpenHelper = new DBHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        modify_event_year.setText(mDbOpenHelper.selectEvent(Event_id).getyear()+"");
        modify_event_year.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyYearPickerDialog pd = new MyYearPickerDialog();
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
        modify_event_name.setText(mDbOpenHelper.selectEvent(Event_id).getname());
        modify_event_memo.setText(mDbOpenHelper.selectEvent(Event_id).getmemo());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_complete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), Modify_User_Category.class);
            intent.putExtra("id",Category_id);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_complete) {
            String name = modify_event_name.getText().toString();
            int year = Integer.parseInt(modify_event_year.getText().toString());
            String memo = modify_event_memo.getText().toString();

            mDbOpenHelper.updateEvent(Event_id, name, year, memo);
            Intent intent = new Intent(getApplicationContext(), Modify_User_Category.class);
            intent.putExtra("id",Category_id);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
