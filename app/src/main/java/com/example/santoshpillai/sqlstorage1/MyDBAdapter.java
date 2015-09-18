package com.example.santoshpillai.sqlstorage1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Santosh Pillai on 1/19/2015.
 */
public class MyDBAdapter {

    MyHelper helper;

    MyDBAdapter(Context c){
        helper=  new MyHelper(c);
    }

    public long insertData(String name, String password){

        SQLiteDatabase db1 = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // type of a mapped data structure that takes key (Column name) and value (to be inserted)
        contentValues.put (helper.NAME, name);
        contentValues.put(helper.PASSWORD, password);
        long id =  db1.insert(helper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String allData(){

        SQLiteDatabase db2 = helper.getWritableDatabase();
        String[] columns = {MyHelper.UID, MyHelper.NAME, MyHelper.PASSWORD};
        Cursor cursor = db2.query(MyHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){  // the cursor moves down one row in a loop.

            // getting the index number of each column header.
            int index1 = cursor.getColumnIndex(MyHelper.UID);
            int index2 = cursor.getColumnIndex(MyHelper.NAME);
            int index3 = cursor.getColumnIndex(MyHelper.PASSWORD);

            //fetching the value from each column using the idex.
            int cid = cursor.getInt(index1);
            String name = cursor.getString(index2);
            String pass = cursor.getString(index3);

            buffer.append(cid +" "+ name +" "+ pass + "\n");

        }

        return buffer.toString();

    }

    public String getData(String name) {
        SQLiteDatabase db2 = helper.getWritableDatabase();
        String[] columns = { MyHelper.NAME, MyHelper.PASSWORD};
        Cursor cursor = db2.query(MyHelper.TABLE_NAME, columns, MyHelper.NAME +"= '"+name+"'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(MyHelper.NAME);
            String pname = cursor.getString(index1);
            int index2 = cursor.getColumnIndex(MyHelper.PASSWORD);
            String pass = cursor.getString(index2);

            buffer.append( name +" "+ pass + "\n");

        }

        return buffer.toString();

    }

    public int getID(String name1){
        int id= -2;
        SQLiteDatabase db2 = helper.getWritableDatabase();
        String[] columns = {MyHelper.UID};
        String selectionArgs[]= {name1};
        Cursor cursor = db2.query(MyHelper.TABLE_NAME, columns, MyHelper.NAME + " =? ", selectionArgs, null, null, null);

        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(MyHelper.UID);
             id = cursor.getInt(index1);


            //buffer.append( id + "\n");

        }

         return id;
        //return buffer.toString();


    }

    public void updateIt(int id){
        SQLiteDatabase db2 = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyHelper.NAME, "NewNAme");

        String [] args1 = {String.valueOf(id)};

        db2.update(MyHelper.TABLE_NAME, cv, MyHelper.UID +"=?", args1);
    }

    public void deleteIt(int id){
        SQLiteDatabase db2 = helper.getWritableDatabase();
        String [] args1 = {String.valueOf(id)};
        db2.delete(MyHelper.TABLE_NAME, MyHelper.UID +"=?", args1);
    }

 public class MyHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Mydatbase1";
    private static final String TABLE_NAME = "MyTable1";
    private static final int  DB_VERSION = 7;
    private static final String UID = "_id";
    private static final String NAME="Name";
    private static final String PASSWORD="Password";
    private static final String AGE = "Age";
    private static final String CREATE_TABLE = " CREATE TABLE "+TABLE_NAME+ " ("+UID+"  INTEGER PRIMARY KEY AUTOINCREMENT , "+NAME+" VARCHAR(255), "+PASSWORD+" VARCHAR(255), "+AGE+" VARCHAR(225));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;


    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        this.context = context;


        Message.messaage(context, "Constructor has been called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT , Name VARCHAR(255));
        Message.messaage(context, "onCreate has been called");
        db.execSQL(CREATE_TABLE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Message.messaage(context, "onUpgrade was called");
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }
}
        }

