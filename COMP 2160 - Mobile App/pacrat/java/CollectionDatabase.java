//COMP 3450: Riley Hall, Nathan Chorlton, Martin Atanacio
package com.example.pacrat_good_empty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CollectionDatabase extends SQLiteOpenHelper {
    public static final int Database_Version = 1;
    public static final String DATABASE_NAME = "collectionDatabase";
    private static CollectionDatabase database;
    public static String TABLE_NAME = "collection_1";
    public static final String NAME = "NAME";
    public static final String RELEASED = "Date_Released";
    public static final String PURCHASED = "Date_Purchased";
    public static final String PHOTO = "PHOTO";
    private static final String ID = "ID";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static int counter = 0;
    private static Bitmap map;


    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    public CollectionDatabase(Context context ) {

        super(context, DATABASE_NAME, null, Database_Version);



    }

    public static CollectionDatabase instanceOfDatabase(Context context) {
        if (database == null) {
            database = new CollectionDatabase(context);

        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql;
        if(database ==null && TABLE_NAME!="") {
            String query = "CREATE TABLE " + TABLE_NAME + " ( "
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME + " TEXT , "
                    + PURCHASED + " DATE, "
                    + RELEASED + " DATE, "
                    + PHOTO + " BLOB,"
                    + DESCRIPTION + " TEXT)";
            db.execSQL(query);
            Log.d("query2", "onCreate: " + query);
        }




    }

    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT , "
                + PURCHASED + " DATE, "
                + RELEASED + " DATE, "
                + PHOTO + " BLOB,"
                + DESCRIPTION + " TEXT)";
        db.execSQL(query);
        Log.d("query2", "onCreate: createTable " + query);

        Log.d("hello", "createTable:  " + query);


    }

    public String [] getTableNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<String>();

//
//        String query = "SELECT * FROM " + DATABASE_NAME + " Where type = 'table'";
//
//        db.execSQL(query);

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='android_metadata' AND name!='sqlite_sequence' order by name", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                list.add(c.getString(c.getColumnIndexOrThrow("name")));
                c.moveToNext();
            }
        }
        String [] arr = new String[list.size()];

        for(int i = 0 ; i<list.size() ; i++) {
            arr[i] = list.get(i);
        }
        return  arr;

    }


    public void addNewItem(String name ,String released , String purchased , String description , Bitmap map ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();

        Bitmap bmp = map;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        bmp.recycle();

//        String sql = "INSERT INTO " + TABLE_NAME + " ( "+NAME+ " , "+PURCHASED+" , "+RELEASED+" , "+DESCRIPTION+" )"+
//                " VALUES ( "+ name+" , " + purchased +" , "+ released +" , " + description+ " )";
        val.put(NAME ,  name);
        val.put(PURCHASED , purchased);
        val.put(RELEASED,released);
        val.put(DESCRIPTION,description);

//        Log.d("query2", "addNewItem: INSERT STATMENT   " + db.insert(TABLE_NAME,null,val));

        db.insert(TABLE_NAME,null,val);

        db.close();

//        db.execSQL(sql);

//        String sql =
//                "INSERT or replace INTO " + TABLE_NAME + " ( "+NAME + " , " + RELEASED +" , "+ PURCHASED +" , "+ DESCRIPTION+" ) "+ " VALUES ( "+ name +" , " + released +
//                " , " + purchased + " , " + description + " )";
//        db.execSQL(sql);

    }

    public ArrayList<individual_collection_items> readFromDB(){
        ArrayList <individual_collection_items> list = new ArrayList<individual_collection_items>();

        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("query2", "readFromDB: " + TABLE_NAME);

        Cursor cursor = db.rawQuery("Select * FROM " + TABLE_NAME , null);

        Cursor count = db.rawQuery("Select count(*) from " + TABLE_NAME,null);

        Log.d("hello", "readFromDB: COUNT OF ROWS  " + count.getCount());



        if(cursor.moveToFirst()) {
            do{
                String external  = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/" +cursor.getString(cursor.getColumnIndexOrThrow(NAME))+".png";
                Bitmap bm = BitmapFactory.decodeFile(external);

                individual_collection_items items = new individual_collection_items(cursor.getString(cursor.getColumnIndexOrThrow(NAME)) , cursor.getString(cursor.getColumnIndexOrThrow(RELEASED)) ,
                        cursor.getString(cursor.getColumnIndexOrThrow(PURCHASED)) ,bm ,cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))  );
//                dict.remove(dict.keys());
//                dict.put("Name" , );
//                dict.put("Purchased" , );
//                dict.put("Released" , );
//                dict.put("Desc" , );
//                byte [] byt = cursor.getBlob(cursor.getColumnIndexOrThrow(PHOTO));
//                Bitmap bitmap= BitmapFactory.decodeByteArray(byt , 0 , byt.length);
//                Log.d("hell", "readFromDB: .dfadfa   " + cursor.getBlob(cursor.getColumnIndexOrThrow(PHOTO)));
                Log.d("query2", "readFromDB: .dfadfa  nulll  " +cursor.getString(cursor.getColumnIndexOrThrow(PURCHASED)));
//                dict.put("photo" , bitmap);
                list.add(items);

            }while (cursor.moveToNext());
        }


        return list;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        switch (oldVersion) {
//            case 1:
//                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT ");
//            case 2:
//                db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + " TEXT ");
//        }


    }



}
