package com.example.kolokwium;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHepler extends SQLiteOpenHelper {

    private String table_Name = "person";
    private String key_ID = "ID";
    private String key_Name = "Name";
    private String key_Lastname = "Lastname";
    private String key_City = "City";
    private SQLiteDatabase db;
    private Context conn;

    public DBHepler(Context context, String name, int version) {
        super(context, name, null, version);
        this.conn = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tworzenie bazy danych
        String sql = "CREATE TABLE " + table_Name + " ( " +
                key_ID + " INTEGER PRIMARY KEY, " +
                key_Name + " TEXT," +
                key_Lastname + " TEXT," +
                key_City  +" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(conn, "Aktualizacja bazy danych", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(conn, "Zmiany w bazach danych", Toast.LENGTH_SHORT).show();
    }

    public long insertPerson(String name, String lastname, String city){
        // przygotowanie danych do wstawienia do tabeli
        ContentValues content = new ContentValues();
        content.put(key_Name, name);
        content.put(key_Lastname, lastname);
        content.put(key_City, city);
        // otwarcie bazy danych do zapisu danych
        db = this.getWritableDatabase();
        // zapisanie danych w tabeli oraz zwrucenie id zapisanego rekordu
        long index = db.insert(table_Name, null, content);
        // zamknięcie połączenia z bazą danych
        db.close();
        return index;
    }

    public ArrayList<String> getAllPerson(){

        ArrayList<String> tempList = new ArrayList<>();
        // otwarcie połączenia z bazą danych
        db = this.getReadableDatabase();
        // odczytanie wszystkoch rekordów z bazy danych
        Cursor cursor = db.query(table_Name, new String[] {key_ID, key_Name, key_Lastname, key_City},
                null, null, null, null, null);

        // Alternatywne odczytanie wszystkich danych z tabeli
        // Cursor cursor = db.rawQuery("select * from person", null);

        // odczytanie rekordów z bazy danych od początku listy
        while(cursor.moveToNext()){
            tempList.add(cursor.getString(0) + ") " +
                    cursor.getString(cursor.getColumnIndex(key_Name)) + " " +
                    cursor.getString(cursor.getColumnIndex(key_Lastname)) + ", " +
                    cursor.getString(3));
        }
        // zamknięcie połączenia z bazą danych
        db.close();
        // zamknięcie połączenia z Cursor - em
        cursor.close();
        return tempList;
    }

    public ArrayList<String> getPerson( String name){

        ArrayList<String> tempList = new ArrayList<String>();
        // otwarcie bazy do odczytu danych
        db = this.getReadableDatabase();
        // zapytanie o konkretne rekordy
        Cursor cursor = db.query(table_Name, new String[] {key_ID, key_Name, key_Lastname, key_City},
                key_Name + " like ? ", new String[] {name}, null, null, null);
        // alternatywna metoda zwracająca rekordy z bazy danych
        //Cursor cursor = db.rawQuery("select ID, Lastname, City from person where Name = ?",
        //        new String[] {name});
        // odczytanie danych z Cursor - a
        while(cursor.moveToNext()){
            tempList.add(cursor.getString(0) + ") " +
                    cursor.getString(cursor.getColumnIndex(key_Name)) + " " +
                    cursor.getString(cursor.getColumnIndex(key_Lastname)) + ", " +
                    cursor.getString(3));
        }
        cursor.close();
        db.close();
        return tempList;
    }

    public long updatePerson(String index, String name){
        db = this.getWritableDatabase();
        // przygotowanie danych do aktualizacji
        ContentValues content = new ContentValues();
        content.put(key_Name, name);
        // aktualizowanie wybranych rekordów z tabeli oraz zwrucenie liczby zaktualizowanych rekordów
        long nr = db.update(table_Name,content, key_ID + " = ?", new String[] {index});
        // zamknięcie połączenia z bazą danych
        db.close();
        return nr;
    }

    public long deletePerson(String id){
        // otwarcie połączenia z bazą danych
        db = this.getWritableDatabase();
        // usunięcie rekordu z bazy danych
        long nr = db.delete(table_Name, key_ID + " = ? ", new String[] {id});
        // zamknięcie połączenia z bazą danych
        db.close();
        return nr;
    }
}
