package com.example.shiyan2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //SharedPreferences
    private SharedPreferences sharedPreferences;
    private EditText etAuthorName;
    private Button mButton;
    private ListView mList;
    private Intent mIntent;
    private MyAdapter mAdapter;
    private NoteDb mNotedb;
    private Cursor cursor;
    private SQLiteDatabase dbreader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        etAuthorName = findViewById(R.id.etAuthorName);
        String authorName = sharedPreferences.getString("authorName", "zsy");
        etAuthorName.setText(authorName);
        mList = (ListView) this.findViewById(R.id.list);//首页显示的记事本内容ListView
        mNotedb = new NoteDb(this);
        dbreader = mNotedb.getReadableDatabase();//获取可读SQLiteDatabase()对象
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                Intent intent = new Intent(MainActivity.this,ShowContent.class);
                intent.putExtra(NoteDb.ID,cursor.getInt(cursor.getColumnIndex(NoteDb.ID)));
                intent.putExtra(NoteDb.CONTENT,cursor.getString(cursor.getColumnIndex(NoteDb.CONTENT)));
                intent.putExtra(NoteDb.TIME,cursor.getString(cursor.getColumnIndex(NoteDb.TIME)));
                startActivity(intent);
            }
        });
    }
    private void saveAuthorName(String authorName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authorName", authorName);
        editor.apply();
    }
    public void onSaveButtonClicked(View view) {
        String authorName = etAuthorName.getText().toString();

        // 调用保存作者姓名的方法
        saveAuthorName(authorName);

        // ... 保存其他日记内容的逻辑
    }
    public void add(View v) {
        //显示intent指的是直接指定目标组件
        //使用Intent显示指定要跳转的目标Activity
        //创建Intent对象传入2个参数，第一个参数：表示当前的Activity，第二个参数：表示要跳转到的目标Activity
        //启动Activity
        mIntent = new Intent(MainActivity.this,AddContent.class);
        startActivity(mIntent);
    }
    public void selectDb() {//从数据库中查询数据显示到listView
        //query()方法，该方法返回的是一个行数集合Cursor，Cursor是一个游标接口，提供遍历查询结果的方法。
        cursor = dbreader.query
                (NoteDb.TABLE_NAME,null,null,null,null,null,null);
        mAdapter = new MyAdapter(this,cursor);
        mList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDb();
    }
}