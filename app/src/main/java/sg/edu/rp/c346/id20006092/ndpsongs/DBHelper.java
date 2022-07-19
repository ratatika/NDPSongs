package sg.edu.rp.c346.id20006092.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndpsongs.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SINGERS + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_STARS + " INTEGER ) " ;
        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public long insertSong(String title,String singers, int year, int stars) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);

        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "" + result);
        return result;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songsList = new ArrayList<Song>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_SINGERS + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song newSong = new Song(id, title, singers, year, stars);
                songsList.add(newSong);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return songsList;
    }

    public ArrayList<Song> getAllSongsByStars (int starsFilter) {
        ArrayList<Song> songsList = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String conditions = COLUMN_YEAR + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_SINGERS + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_SONG, columns, conditions, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song newSong = new Song(id, title, singers, year, stars);
                songsList.add(newSong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songsList;
    }

    public int UpdateSong(Song data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String conditions = COLUMN_YEAR + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, conditions, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public ArrayList<String> getYears () {
        ArrayList<String> years = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_YEAR};

        Cursor cursor;
        cursor = db.query(true, TABLE_SONG, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                years.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return years;
    }

    public ArrayList<Song> getAllSongsByYear (int yearFilter) {
        ArrayList<Song> songsList = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String conditions = COLUMN_YEAR + "= ?";
        String[] args = {String.valueOf(yearFilter)};

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_SINGERS + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_SONG, columns, conditions, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song newSong = new Song(id, title, singers, year, stars);
                songsList.add(newSong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songsList;
    }




}
