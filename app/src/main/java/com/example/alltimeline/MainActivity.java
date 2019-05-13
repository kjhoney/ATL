package com.example.alltimeline;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int YEAR_VIEW_ID = 0x4000;
    private final int DYNAMIC_VIEW_ID  = 0x8000;
    private int year = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_search) {
            Intent intent = new Intent(getApplicationContext(),Search.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(),Add.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        LinearLayout yearLayout = findViewById(R.id.year_table);
        for(int i=0; i<50; i++) {
            AddYearView(yearLayout);
        }
        MyApplication myApp = (MyApplication)getApplicationContext();
        ArrayList<MyApplication.Category> myArray = myApp.getCategory_list();
        int idx = 1;
        for(Object object : myArray) {
            int layoutID = getResources().getIdentifier("category" + idx, "id", "com.example.alltimeline");
            LinearLayout dynamicLayout = findViewById(layoutID); // layoutID로 변경
            MyApplication.Category tmp = (MyApplication.Category) object;
            for (MyApplication.Event event : tmp.eventList) {
                AddCategoryView(dynamicLayout, event.title, event.length);
            }
            idx++;
        }
    }

    private void AddYearView(LinearLayout layout) {
        // VIEW XML로 바꿔야됨
        TextView dynamicTextView = new TextView(this);
        dynamicTextView.setId(YEAR_VIEW_ID + year);
        dynamicTextView.setText(String.valueOf(year));
        dynamicTextView.setGravity(Gravity.CENTER);
        dynamicTextView.setTextColor(Color.parseColor("#000000"));
        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        layout.addView(dynamicTextView, new LinearLayout.LayoutParams(width, height));
        year += 5;
    }

    private void AddCategoryView(LinearLayout layout, String title, int length) {
        // VIEW XML로 바꿔야됨
        TextView dynamicTextView = new TextView(this);
        //dynamicTextView.setId(DYNAMIC_VIEW_ID + idx);
        dynamicTextView.setText(title);
        dynamicTextView.setGravity(Gravity.CENTER);
        dynamicTextView.setTextColor(Color.parseColor("#ffffff"));
        dynamicTextView.setBackgroundColor(Color.parseColor("#8dbabd"));
        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20 * length - 10, getResources().getDisplayMetrics());
        final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        lp.setMargins(10,10,10,10);
        layout.addView(dynamicTextView, lp);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };
}
