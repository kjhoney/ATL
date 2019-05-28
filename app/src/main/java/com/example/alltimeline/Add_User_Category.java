package com.example.alltimeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Add_User_Category extends AppCompatActivity {
    private DBHelper mDbOpenHelper;
    private ArrayList<Category> Data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_category);
        setTitle("연표 추가");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDbOpenHelper = new DBHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
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
            Intent intent = new Intent(getApplicationContext(), Add.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_complete) {
            final EditText edittext=findViewById(R.id.search_box);
            String name = edittext.getText().toString();
            if(name.equals("")) {
                Toast.makeText(Add_User_Category.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                mDbOpenHelper.insertCategory(name, 0);
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
