package com.example.alltimeline;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private EditText search_box;
    private TextView output;
    private RecyclerView listview;
    private SearchAdapter adapter;
    private String url = "http://165.246.241.106:3000/categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        search_box = findViewById(R.id.search_box);
        Button button = findViewById(R.id.search_button);
        output = findViewById(R.id.output);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });

        search_box.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        //엔터키 입력시
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        init();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            output.setText(s);
        }
    }

    private void init() {

        listview = findViewById(R.id.search_listview);
        listview.setLayoutManager(new GridLayoutManager(this, 3));

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("한국사");
        itemList.add("세계사");
        itemList.add("과학사");
        itemList.add("미술사");
        itemList.add("아시아사");
        itemList.add("유럽사");
        adapter = new SearchAdapter(this, itemList, onClickItem);
        listview.setAdapter(adapter);

        SearchListDecoration decoration = new SearchListDecoration();
        listview.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*MyApplication myApp = (MyApplication)getApplicationContext();
            if(myApp.getCategory_num() < 5) {
                MyApplication.Category tmp1 = new MyApplication.Category("한국사");
                tmp1.Add_Event(0, 1010, 5, "OS과제");
                tmp1.Add_Event(1, 1015, 10, "어셈과제");
                tmp1.Add_Event(2, 1020, 15, "중간고사");
                tmp1.Add_Event(3, 1025, 20, "웹강듣기");
                tmp1.Add_Event(4, 1030, 5, "개짜증");
                tmp1.Add_Event(5, 1592, 100, "임진왜란");
                myApp.Add_Category(tmp1);
                String str = (String) v.getTag();
                Toast.makeText(Search.this, str + " 추가완료", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(Search.this, "연표가 5개를 넘을 수 없습니다", Toast.LENGTH_SHORT).show();*/
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    };

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
            Intent intent = new Intent(getApplicationContext(), Add.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
