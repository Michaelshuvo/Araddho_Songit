/*
package daocopy;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dao.Config;


public class Dbhelper2 extends SQLiteOpenHelper {
    private static SQLiteDatabase myDatabase;
    private  Context mycontext=null;
    private static String dbpath= Config.DATABASE_LOCATION;
    private static final int DATABASE_VERSION = 1;
    public static final String Databasename = "songs.db";


    public Dbhelper2(Context context) {
        super(context, Databasename, null, DATABASE_VERSION);

        if(Build.VERSION.SDK_INT>=17){
            dbpath=context.getApplicationInfo().dataDir+"/databases/";
        }
        else
            dbpath=Config.DATABASE_LOCATION;
      mycontext=context;

    }

    @Override
    public synchronized void close() {
        if(myDatabase!=null)
            myDatabase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
public void openDatbase(){
        String path=mycontext.getDatabasePath(Databasename).getPath();
        if(myDatabase!=null && myDatabase.isOpen()){
            return;
        }
        myDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
}

    public void createdatabase(){

        boolean isdbexist=checkable2();
        if(isdbexist){

        }
        else {
            this.getWritableDatabase();
            try {
                copydatabsae(mycontext);

            } catch (Exception e) {
                Toast.makeText(mycontext, "create faild from oncreate", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean checkable(){

        try {
            SQLiteDatabase myDatabase1 = SQLiteDatabase.openDatabase(Config.DATABASE_LOCATION + Databasename, null, SQLiteDatabase.OPEN_READWRITE);

        if (myDatabase1==null){
            Toast.makeText(mycontext, "false", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mycontext, "true", Toast.LENGTH_SHORT).show();
        }
        }catch (SQLException e)
        {
            Toast.makeText(mycontext, e.toString(), Toast.LENGTH_SHORT).show();
        }
return false;
    }
    public boolean checkable2(){

       File file=mycontext.getDatabasePath(Databasename);
      // String path=mycontext.getDatabasePath(Databasename);
       if(file.exists()){
           Toast.makeText(mycontext,"true", Toast.LENGTH_SHORT).show();
       }
       else
        Toast.makeText(mycontext, "false", Toast.LENGTH_SHORT).show();
   return file.exists();

    }
    private boolean checkDataBase3() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(Config.DATABASE_LOCATION, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            Toast.makeText(mycontext, "false", Toast.LENGTH_SHORT).show();
        }
        return checkDB != null;
    }


    public boolean copydatabsae(Context context){
        try {
            InputStream inputStream=context.getAssets().open(Databasename);
            String outfileName=dbpath+Databasename;
            OutputStream outputStream=new FileOutputStream(outfileName);
            byte[] buff=new byte[1024];
            int length=0;
            while((length=inputStream.read(buff))>0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
              Toast.makeText(mycontext, "copy succes from copydatabase", Toast.LENGTH_SHORT).show();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
               Toast.makeText(mycontext, "copy faild from copydatabase", Toast.LENGTH_SHORT).show();

            return  false;
        }


    }
}
*/
