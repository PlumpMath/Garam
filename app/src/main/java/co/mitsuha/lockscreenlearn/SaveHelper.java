package co.mitsuha.lockscreenlearn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by studiodoth on 2017. 5. 17..
 */

public class SaveHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "garam.db";
    private static final String MEMO_TABLE = "memos";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_PW = "password";
    private static final String KEY_TYPE = "type";
    private static final String[] COLS = {KEY_ID,KEY_TYPE,KEY_TITLE,KEY_PW};

    public SaveHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE memos (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, title TEXT, password TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS memos");
        this.onCreate(db);
    }
    public void addItem(Memo memo) {
        Log.d("GaramDB","Add "+memo.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,memo.title);
        values.put(KEY_PW,memo.password);
        values.put(KEY_TYPE,memo.type);
        db.insert(MEMO_TABLE,null,values);
        db.close();
    }
    public Memo getMemo(int id) {
        Log.d("GaramDB","Get "+id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MEMO_TABLE,COLS,KEY_ID+" = ?",new String[] {String.valueOf(id)},null,null,null,null);
        if(cursor != null) cursor.moveToFirst();
        Memo memo = new Memo();
        memo.ID = Integer.parseInt(cursor.getString(0));
        memo.type = cursor.getString(1);
        memo.title = cursor.getString(2);
        memo.password = cursor.getString(3);
        db.close();
        return memo;
    }
    public List<Memo> getMemos() {
        Log.d("GaramDB","getMemos");
        List<Memo> memos = new ArrayList<>();
        String query = "SELECT * FROM "+MEMO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Memo memo = new Memo();
                memo.ID = Integer.parseInt(cursor.getString(0));
                memo.type = cursor.getString(1);
                memo.title = cursor.getString(2);
                memo.password = cursor.getString(3);
                memos.add(memo);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return memos;
    }
    public List<Memo> getMemosType(String type) {
        Log.d("GaramDB","getMemosType "+type);
        List<Memo> memos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(MEMO_TABLE,COLS,KEY_TYPE+" = ?",new String[] {String.valueOf(type)},null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                Memo memo = new Memo();
                memo.ID = Integer.parseInt(cursor.getString(0));
                memo.type = cursor.getString(1);
                memo.title = cursor.getString(2);
                memo.password = cursor.getString(3);
                memos.add(memo);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return memos;
    }
    public int updateMemo(Memo memo) {
        Log.d("GaramDB","Update "+memo.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,memo.title);
        values.put(KEY_PW,memo.password);
        values.put(KEY_TYPE,memo.type);
        int i = db.update(MEMO_TABLE,values,KEY_ID+" = ?",new String[] {String.valueOf(memo.ID)});
        db.close();
        return i;
    }
    public void deleteMemo(int id) {
        Log.d("GaramDB","Delete "+id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MEMO_TABLE,KEY_ID+" = ?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteAll() {
        Log.d("GaramDB","Delete All");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MEMO_TABLE,null,null);
        db.close();
    }
}
