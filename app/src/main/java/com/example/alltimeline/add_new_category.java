package com.example.alltimeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_new_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_category);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //or switch문을 이용하면 될듯 하다.
        if (id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(),Add.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_complete) {
            final EditText edittext=findViewById(R.id.search_box);
            String title = edittext.getText().toString();
            if(title.equals("")) {
                Toast.makeText(add_new_category.this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                MyApplication myApp = (MyApplication)getApplicationContext();
                MyApplication.Category new_category = new MyApplication.Category(title);
                myApp.Add_User_Category(new_category);
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
