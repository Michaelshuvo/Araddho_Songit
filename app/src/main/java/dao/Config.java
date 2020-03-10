package dao;

public class Config {
        public static final String DATABASE_NAME = "song.db";
        public static final int DATABASE_VERSION=1;
        public static final String DATABASE_LOCATION = "/data/data/com.example.system.christsong/databases/";

        //column names of songs table
        public static final String TABLE_SONG = "songs";
        public static final String COLUMN_song_Number = "id";
        public static final String COLUMN_song_BNumber = "songid";
        public static final String COLUMN_SONG_TITLE = "songtitle";
        public static final String COLUMN_SONG_BTITLE= "songtitle";
        public static final String COLUMN_SONG_LYRICS = "lyrics";
        public static final String COLUMN_SONG_Catagory ="catagory";
        public static final String COLUMN_SONG_WRITER = "songwriter";
        public static final String COLUMN_SONG_FAV = "fav";


        //others for general purpose key-value pair data
        public static final String SONG_LETTER= "letter";
        public static final String TITLE = "title";
        public static final String CREATE_STUDENT = "create_student";
        public static final String UPDATE_STUDENT = "update_student";
    }

