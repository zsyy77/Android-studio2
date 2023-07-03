package com.example.shiyan2;



import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowContent extends AppCompatActivity {
    private TextView mTextview;
    private TextView time;
    private NoteDb mDb;
    private SQLiteDatabase mSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        mTextview = (TextView)this.findViewById(R.id.showtext);
        time = (TextView)this.findViewById(R.id.showtime);
        mDb = new NoteDb(this);
        mSql = mDb.getWritableDatabase();
        mTextview.setText(getIntent().getStringExtra(NoteDb.CONTENT));
        time.setText(getIntent().getStringExtra(NoteDb.TIME));
    }
    public void delete(View v) {
        int id = getIntent().getIntExtra(NoteDb.ID,0);
        mSql.delete(NoteDb.TABLE_NAME," _id = " + id,null);
        finish();

    }
    public void goBack(View v) {
        finish();
    }
}
