package dao;

public class Song {
    private int number;
    private String bnumber;
    private String title;
    private String btitle;
    private String lyrics;
    private String writer;
    private String catagory;
    private int fav;
    public Song(int number,String bnumber, String title,String btitle,  String lyrics,String catagory,String writer,int fav) {
        this.number = number;
        this.bnumber = bnumber;
        this.title = title;
       this.btitle = btitle;
        this.lyrics = lyrics;
        this.catagory = catagory;
        this.writer=writer;
        this.fav=fav;

    }

    public Song(int number, String bnumber,  String btitle) {//from get data by catagory
        this.number = number;
         this.bnumber = bnumber;
        this.btitle = btitle;
    }
    public Song(String bnumber, String btitle) {//for list of song
        this.bnumber = bnumber;
        // this.bnumber = bnumber;
        this.btitle = btitle;
        // this.btitle = btitle;
    }
    public Song(){

    }
    public Song(String bnumber, String btitle,  int number) {
        this.number =number;
         this.bnumber = bnumber;
       // this.title = title;
         this.btitle = btitle;
        //this.lyrics = lyrics;
    }

    public Song(int number,String bnumber,String lyrics, String writer,int fav) { //get data from lyricpage
        this.number = number;
        this.bnumber = bnumber;
        this.lyrics = lyrics;
        this.writer=writer;
        this.fav=fav;
    }


    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getBnumber() {
        return bnumber;
    }

    public String getWriter() {
        return writer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
