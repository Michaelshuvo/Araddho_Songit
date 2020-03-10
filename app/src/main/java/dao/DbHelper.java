package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteAssetHelper {
    private Context context=null;
    private static DbHelper databaseHelper;
  private DbHelper(Context context) {
      super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);

    }
    public static synchronized DbHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DbHelper(context);
           // Toast.makeText(context,"nodatabase",Toast.LENGTH_SHORT).show();
        }
        return databaseHelper;
    }



//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_SONG_TABLE = "CREATE TABLE " + Config.TABLE_SONG + "("
//                + Config.COLUMN_song_Number + " INTEGER  UNIQUE,"
//                + Config.COLUMN_song_BNumber + " TEXT,"
//                + Config.COLUMN_SONG_TITLE + " TEXT,"
//                + Config.COLUMN_SONG_BTITLE + " TEXT NOT NULL,"
//                + Config.COLUMN_SONG_LYRICS + " TEXT ,"
//                + Config.COLUMN_SONG_Catagory + " TEXT," //nullable
//                + Config.COLUMN_SONG_WRITER + " TEXT" //nullable
//                + ")";
//
//        db.execSQL(CREATE_SONG_TABLE);
//
//
//    }

//    @Override
//    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_SONG);
//
//        // Create tables again
//        onCreate(db);
//
//    }


//    public boolean dataInsert(Song song){
//
//        long id = -1;
//        DbHelper databaseHelper = DbHelper.getInstance(context);
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put("name", name);
//        contentValues.put("email", email);
//
//        long result = db.insert("students", null, contentValues);
//
//        if (result== -1)
//            return false;
//        else return true;
//    }

//    public Cursor getalldata(){
//        SQLiteDatabase db=this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from " +"students", null);
//        return res;
//    }
//    public Integer deleteData (String id){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        return db.delete("students", "id  = ?", new String[] {id});
//
//    }




}
