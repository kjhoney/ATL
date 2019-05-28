package com.example.alltimeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Add extends AppCompatActivity {
    private DBHelper mDbOpenHelper;
    private CategoryListAdapter Adapter;
    private ListView usercustom_list_view;
    private TextView if_no_category;
    private ArrayList<Category> Data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("나만의 연표");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usercustom_list_view = (ListView) findViewById(R.id.usercustom_list_view);
        if_no_category = (TextView) findViewById(R.id.if_no_category);
        mDbOpenHelper = new DBHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        Data = mDbOpenHelper.selectAll();

        if (Data.size() == 0)
            if_no_category.setVisibility(View.VISIBLE);
        else
            if_no_category.setVisibility(View.INVISIBLE);

        Adapter = new CategoryListAdapter(Data);
        usercustom_list_view.setAdapter(Adapter);
        usercustom_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Add.this, Modify_User_Category.class);
                intent.putExtra("id", Data.get(position).getid());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), Add_User_Category.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
