package com.example.shiyan2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContent extends AppCompatActivity {
    private EditText mEt;
    private NoteDb mDb;
    private SQLiteDatabase mSqldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        mEt = (EditText) this.findViewById(R.id.text);
        mDb = new NoteDb(this);
        mSqldb = mDb.getWritableDatabase();//获取可读写SQLiteDatabase对象
    }
    public void save(View v) {
        DbAdd();
        finish();
    }
    public void cancle(View v) {
        mEt.setText("");
        finish();
    }
    public void DbAdd() {//将文档内容和时间添加到数据库
        ContentValues cv = new ContentValues();
        cv.put(NoteDb.CONTENT,mEt.getText().toString());
        cv.put(NoteDb.TIME,getTime());
        mSqldb.insert(NoteDb.TABLE_NAME,null,cv);
    }
    //获得当前时间
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }
}
