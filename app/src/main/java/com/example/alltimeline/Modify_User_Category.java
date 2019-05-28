package com.example.alltimeline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Modify_User_Category extends AppCompatActivity {
    private DBHelper mDbOpenHelper;
    private EventListAdapter Adapter;
    private ListView usercustom_event_list_view;
    private TextView if_no_event;
    private ArrayList<Event> Data = new ArrayList<>();
    private int Category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Category_id = getIntent().getIntExtra("id", 1);
        usercustom_event_list_view = (ListView) findViewById(R.id.usercustom_event_list_view);
        if_no_event = (TextView) findViewById(R.id.if_no_event);
        mDbOpenHelper = new DBHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        setTitle(mDbOpenHelper.selectCategory(Category_id).name);
        Data = mDbOpenHelper.selectAllEvent(Category_id);

        if (Data.size() == 0)
            if_no_event.setVisibility(View.VISIBLE);
        else
            if_no_event.setVisibility(View.INVISIBLE);

        Adapter = new EventListAdapter(Data);
        usercustom_event_list_view.setAdapter(Adapter);
        usercustom_event_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Modify_User_Category.this, Modify_User_Event.class);
                intent.putExtra("c_id", Data.get(position).getc_id());
                intent.putExtra("id", Data.get(position).getid());
                startActivity(intent);
            }
        });

        Button addButton = (Button)findViewById(R.id.add) ;
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Modify_User_Category.this, "추가 눌림", Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteButton = (Button)findViewById(R.id.delete) ;
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                mDbOpenHelper.deleteAllEvents(Category_id);
                mDbOpenHelper.deleteColumn(Category_id);
                Toast.makeText(Modify_User_Category.this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Modify_User_Category.this, Add.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_complete, menu);
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
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(),Add_User_Event.class);
            intent.putExtra("id", Category_id);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_complete) {
            Intent intent = new Intent(getApplicationContext(),Add.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
