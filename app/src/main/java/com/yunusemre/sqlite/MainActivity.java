package com.yunusemre.sqlite;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //SQL DATABASE
        //try-catch

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("ExampleData", MODE_PRIVATE, null); //veritabanı çağır

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS exampledata(id INTEGER PRIMARY KEY,country VARCHAR,city VARCHAR,population INTEGER)"); //veri tabanı değerleri ve klasör oluştur

            //Veri EKLEME
            //sqLiteDatabase.execSQL("INSERT INTO exampledata(country,city,population) VALUES('Türkiye','Ardahan',57000)");


            //VERİ SİLME
            //sqLiteDatabase.execSQL("DELETE  FROM exampledata WHERE city='Balıkesir' ");


            //VERİ GÜNCELLEME
            // sqLiteDatabase.execSQL("UPDATE exampledata SET population=15848000 WHERE id=1");


            //VERİ GÖRÜNTÜLEME
            // Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM exampledata ",null); //veri görüntüleme --> WHERE ile filtreleme yapabilirsin.
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM exampledata WHERE id > 12 ", null);

            //Değer sütunlarını eşleme ve string çevirme
            int countryIx = cursor.getColumnIndex("country");
            int cityIx = cursor.getColumnIndex("city");
            int populationIx = cursor.getColumnIndex("population");
            int idIx = cursor.getColumnIndex("id");

            //while loop ile değer verilen verileri yazdırma,,
            while (cursor.moveToNext()) {
                System.out.println("id: " + cursor.getInt(idIx));
                System.out.println("country: " + cursor.getString(countryIx));
                System.out.println("city: " + cursor.getString(cityIx));
                System.out.println("population: " + cursor.getInt(populationIx));
                System.out.println("---------------------");
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
}