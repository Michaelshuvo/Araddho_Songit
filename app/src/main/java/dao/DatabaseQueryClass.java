package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class DatabaseQueryClass {

    private  Context context;
    public DatabaseQueryClass(Context context) {
        this.context=context;

    }








// DATA INSERT=============================================================================
   /* public long insertStudent(Song song){

        long id = -1;
        DbHelper databaseHelper = DbHelper.getInstance(context);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_song_Number,song.getNumber());
        contentValues.put(Config.COLUMN_song_BNumber,song.getBnumber());
        contentValues.put(Config.COLUMN_SONG_TITLE, song.getTitle());
        contentValues.put(Config.COLUMN_SONG_BTITLE, song.getBtitle());
        contentValues.put(Config.COLUMN_SONG_LYRICS, song.getLyrics());
        contentValues.put(Config.COLUMN_SONG_Catagory, song.getCatagory());
        contentValues.put(Config.COLUMN_SONG_WRITER, song.getWriter());

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_SONG, null, contentValues);
        } catch (SQLiteException e){
           // Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }*/
    //END DATA INSERT=============================================================================
// DATA INSERT in FAV=============================================================================
    public long set_fav(int songid,int condition){

        long rowCount = 0;
        DbHelper databaseHelper = DbHelper.getInstance(context);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SONG_FAV,condition);


        try {
            //id = sqLiteDatabase.insertOrThrow(Config.TABLE_SONG, null, contentValues);
            rowCount = sqLiteDatabase.update(Config.TABLE_SONG, contentValues,
                    Config.COLUMN_song_Number + " = ? ",
                    new String[] {String.valueOf(songid)});
           // Toast.makeText(context, "success", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e){
            // Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }
    //END DATA INSERT=============================================================================

public List<Song> getFav(){
    DbHelper databaseHelper = DbHelper.getInstance(context);

    SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

    Cursor cursor = null;

    try {

//            cursor = sqLiteDatabase.query(Config.TABLE_SONG, null,
//                    Config.COLUMN_SONG_TITLE + " = ? ", new String[]{String.valueOf(letter)},
//                    null, null, null);
        //  SELECT StudentName FROM Students WHERE StudentName LIKE '%y'

        /**
         // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

         String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
         cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
         */
        String SELECT_QUERY = String.format("SELECT %s,%s,%s FROM %s WHERE %s LIKE %s ORDER BY %s",Config.COLUMN_song_Number,Config.COLUMN_song_BNumber,Config.COLUMN_SONG_BTITLE,Config.TABLE_SONG, Config.COLUMN_SONG_FAV, String.valueOf("'1%'"), Config.COLUMN_SONG_BTITLE);
        cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
        if(cursor!=null)
            if(cursor.moveToFirst()){
                List<Song> songslist = new ArrayList<>();
                do {
                    String bnumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_song_BNumber));
                    String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_BTITLE));
                    int number = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));

                    //if for shorting multiline to single line title
                    if(title.length()>28) {
                        title = title.substring(0, 28) + "...";
                    }
                    songslist.add(new Song(bnumber, title,number));
                }   while (cursor.moveToNext());

                return songslist;
            }
    } catch (Exception e){
        //Logger.d("Exception: " + e.getMessage());
        Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
    } finally {
        if(cursor!=null)
            cursor.close();
        sqLiteDatabase.close();
    }

    return Collections.emptyList();


}





//    public long updateStudentInfo(Student student){
//
//        long rowCount = 0;
//        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
//        contentValues.put(Config.COLUMN_STUDENT_REGISTRATION, student.getRegistrationNumber());
//        contentValues.put(Config.COLUMN_STUDENT_PHONE, student.getPhoneNumber());
//        contentValues.put(Config.COLUMN_STUDENT_EMAIL, student.getEmail());
//
//        try {
//            rowCount = sqLiteDatabase.update(Config.TABLE_STUDENT, contentValues,
//                    Config.COLUMN_STUDENT_ID + " = ? ",
//                    new String[] {String.valueOf(student.getId())});
//        } catch (SQLiteException e){
//            Logger.d("Exception: " + e.getMessage());
//            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//        } finally {
//            sqLiteDatabase.close();
//        }
//
//        return rowCount;
//    }


    //Get song all==========================================================================================
//public List<Song> getAllStudent(){
//
//    DbHelper databaseHelper = DbHelper.getInstance(context);
//    SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
//
//    Cursor cursor = null;
//    try {
//
//        cursor = sqLiteDatabase.query(Config.TABLE_SONG, null, null, null, null, null, null, null);
//
//        /**
//         // If you want to execute raw query then uncomment below 2 lines. And comment out above line.
//
//         String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
//         cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
//         */
//
//        if(cursor!=null)
//            if(cursor.moveToFirst()){
//                List<Song> songslist = new ArrayList<>();
//                do {
//                    int number = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));
//                    String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_TITLE));
//                    String lyrics = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_LYRICS));
//
//                    songslist.add(new Song(String.valueOf(number), title,lyrics));
//                }   while (cursor.moveToNext());
//
//                return songslist;
//            }
//    } catch (Exception e){
//        //Logger.d("Exception: " + e.getMessage());
//        Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
//    } finally {
//        if(cursor!=null)
//            cursor.close();
//        sqLiteDatabase.close();
//    }
//
//    return Collections.emptyList();
//}
//END Get song all==========================================================================================











//get song by number---------------------------------------------------------------------------------------------------------
//    public Song getSongByNum(String number){
//
//        DbHelper databaseHelper = DbHelper.getInstance(context);
//
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
//
//        Cursor cursor = null;
//        Song songs = null;
//        try {
//
//            cursor = sqLiteDatabase.query(Config.TABLE_SONG, null,
//                    Config.COLUMN_song_Number + " = ? ", new String[]{String.valueOf(number)},
//                    null, null, null);
//
//            /**
//             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.
//
//             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
//             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
//             */
//
//            if(cursor.moveToFirst()){
//                int snumber = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));
//                String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_TITLE));
//                String lyrics = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_LYRICS));
//
//                songs = new Song(String.valueOf(snumber),title,lyrics);
//            }
//        } catch (Exception e){
//            //Logger.d("Exception: " + e.getMessage());
//            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
//        } finally {
//            if(cursor!=null)
//                cursor.close();
//            sqLiteDatabase.close();
//        }
//
//        return songs;
//    }
    //END get song by number---------------------------------------------------------------------------------------------------------















    //get song by letter-------------------------------------------------------------------------------------------------------------------------
    public List<Song> getSongByletter(String letter){

        DbHelper databaseHelper = DbHelper.getInstance(context);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;

        try {

//            cursor = sqLiteDatabase.query(Config.TABLE_SONG, null,
//                    Config.COLUMN_SONG_TITLE + " = ? ", new String[]{String.valueOf(letter)},
//                    null, null, null);
          //  SELECT StudentName FROM Students WHERE StudentName LIKE '%y'

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */
            String SELECT_QUERY = String.format("SELECT %s,%s,%s FROM %s WHERE %s LIKE %s ORDER BY %s",Config.COLUMN_song_Number,Config.COLUMN_song_BNumber,Config.COLUMN_SONG_BTITLE,Config.TABLE_SONG, Config.COLUMN_SONG_BTITLE, String.valueOf("'"+letter+"%'"), Config.COLUMN_SONG_BTITLE);
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Song> songslist = new ArrayList<>();
                    do {
                        String bnumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_song_BNumber));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_BTITLE));
                        int number = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));

                        //if for shorting multiline to single line title
                        if(title.length()>28) {
                            title = title.substring(0, 28) + "...";
                        }
                        songslist.add(new Song(bnumber, title,number));
                    }   while (cursor.moveToNext());

                    return songslist;
                }
        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

//END get song by letter-----------------------------------------------------------------------




//get song by subject---------------------------------------------------------------------------------------------------------------

    public List<Song> getSonglistBysubject(int positon){//from subjective
        int number;
        DbHelper databaseHelper = DbHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;

        try {

//           cursor = sqLiteDatabase.query(Config.TABLE_SONG, null,
//                   Config.COLUMN_SONG_Catagory + " = ? ", new String[]{String.valueOf(positon)},
//                    null, null, null);
            //  SELECT StudentName FROM Students WHERE StudentName LIKE '%y'

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */
            String SELECT_QUERY = String.format("SELECT %s,%s,%s FROM %s WHERE %s = %s",Config.COLUMN_song_Number, Config.COLUMN_song_BNumber,Config.COLUMN_SONG_BTITLE,Config.TABLE_SONG, Config.COLUMN_SONG_Catagory, String.valueOf(positon));
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Song> songslist = new ArrayList<>();
                    do {
                         number = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));
                        String bnumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_song_BNumber));
                        String btitle = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_BTITLE));
                        if(btitle.length()>28) {
                            btitle = btitle.substring(0, 28) + "...";
                        }

                        songslist.add(new Song(number,bnumber, btitle));
                    }   while (cursor.moveToNext());
                    Toast.makeText(context, "Operation down"+number , Toast.LENGTH_SHORT).show();
                    return songslist;


                }
        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


    //END get song by subject-----------------------------------------------------------------------------------------------------


  //  get song by number---------------------------------------------------------------------------------------------------------
    public Song getSongByNum(int songnumber){//from lyricpage

        DbHelper databaseHelper = DbHelper.getInstance(context);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Song songs = null;
        try {

//            cursor = sqLiteDatabase.query(Config.TABLE_SONG, null,
//                    Config.COLUMN_song_Number + " = ? ", new String[]{String.valueOf(songnumber)},
//                    null, null, null);


             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_SONG, Config.COLUMN_song_Number, String.valueOf(songnumber));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


            if(cursor.moveToFirst()){
                int number = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));
                 String bnumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_song_BNumber));
                String lyrics = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_LYRICS));
                String writer = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_WRITER));
                int fav = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SONG_FAV));

                songs = new Song(number,bnumber,lyrics,writer,fav);
            }
            Toast.makeText(context, "Operation "+songnumber, Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed f 888", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return songs;
    }
    //END get song by number---------------------------------------------------------------------------------------------------------

    public String[] getSongBySearch(String text){//from Search view

        DbHelper databaseHelper = DbHelper.getInstance(context);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        String[] listReturn;
        ArrayList<String> songtitle=new ArrayList<>();
        String SELECT_QUERY;

        if(Pattern.compile( "[0-9]" ).matcher( text ).find()){

            SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_SONG, Config.COLUMN_song_Number, String.valueOf(text));
        }
       else {
           // SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_SONG, Config.COLUMN_SONG_TITLE, String.valueOf(text));
            SELECT_QUERY = String.format("SELECT %s,%s,%s FROM %s WHERE %s LIKE %s",Config.COLUMN_song_Number,Config.COLUMN_song_BNumber,Config.COLUMN_SONG_BTITLE,Config.TABLE_SONG, Config.COLUMN_SONG_BTITLE, String.valueOf("'"+text+"%';"));
        }


        try {

            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);

            if(cursor!=null) {
                if (cursor.moveToFirst()) {
                    do {
                        String bnumber = cursor.getString(cursor.getColumnIndex(Config.COLUMN_song_BNumber));
                        String btitle = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SONG_BTITLE));

                        songtitle.add(btitle);

                    } while (cursor.moveToNext());

                    listReturn=songtitle.toArray(new String[songtitle.size()]);
                    return listReturn;
                }
                else{

                    listReturn=songtitle.toArray(new String[songtitle.size()]);
                    return listReturn;
                }
            }
            else{

                listReturn=songtitle.toArray(new String[songtitle.size()]);
                return listReturn;
            }



        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
           // Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
            songtitle.add("not found");
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }


        listReturn=songtitle.toArray(new String[songtitle.size()]);
       // Toast.makeText(context,listReturn[0],Toast.LENGTH_SHORT).show();
        return listReturn;
    }

    //get song by letter-------------------------------------------------------------------------------------------------------------------------
    public int getSongNumberByTitle(String letter){
        int number=0;
        DbHelper databaseHelper = DbHelper.getInstance(context);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;

        try {

//            cursor = sqLiteDatabase.query(Config.TABLE_SONG, null,
//                    Config.COLUMN_SONG_TITLE + " = ? ", new String[]{String.valueOf(letter)},
//                    null, null, null);
            //  SELECT StudentName FROM Students WHERE StudentName LIKE '%y'

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */
            String SELECT_QUERY = String.format("SELECT %s,%s,%s FROM %s WHERE %s LIKE %s",Config.COLUMN_song_Number,Config.COLUMN_song_BNumber,Config.COLUMN_SONG_BTITLE,Config.TABLE_SONG, Config.COLUMN_SONG_BTITLE, String.valueOf("'"+letter+"%';"));
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
            if(cursor!=null)
                if(cursor.moveToFirst()){
                        number = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_song_Number));

                }

        } catch (Exception e){
            //Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }
        return number;
    }

//END get song by letter-----------------------------------------------------------------------


}
