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
import android.widget.Toast;

public class Add_User_Event extends AppCompatActivity {
    private DBHelper mDbOpenHelper;
    private Button event_year;
    private int Category_id;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            event_year.setText(year+"");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_event);
        setTitle("Event 추가");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDbOpenHelper = new DBHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        Category_id = getIntent().getIntExtra("id", 1);
        event_year = findViewById(R.id.event_year);
        event_year.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyYearPickerDialog pd = new MyYearPickerDialog();
                pd.setListener(d);
                pd.show(getSupportFragmentManager(), "YearMonthPickerTest");
            }
        });
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
            final EditText event_name=findViewById(R.id.event_name);
            final EditText event_memo=findViewById(R.id.event_memo);
            String name = event_name.getText().toString();
            String memo = event_memo.getText().toString();
            if(name.equals("")) {
                Toast.makeText(Add_User_Event.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                return true;
            } else if (memo.equals("")) {
                Toast.makeText(Add_User_Event.this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            } else {
                mDbOpenHelper.insertEvent(Category_id, name, Integer.parseInt(event_year.getText().toString()), memo);
                Intent intent = new Intent(getApplicationContext(), Modify_User_Category.class);
                intent.putExtra("id",Category_id);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
