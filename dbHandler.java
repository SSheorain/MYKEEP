//dbHandler.java
package com.example.sheoran.mykeep2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;


public class dbHandler extends SQLiteOpenHelper{

    private static final int VERSION=1;
    private static final String DATABASE_NAME="MyKeep.dp";
    public static final String TABLE_NAME="products";
    public static final String COLUMN_NAME="_name";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_AMOUNT="_amount";

    public dbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    //Creating table for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE "+TABLE_NAME+" ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_NAME + " TEXT , " +
                COLUMN_AMOUNT + " INTEGER);";
        db.execSQL(query);
    }

    //On making changes upgrading Versions
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    //Inserting a new Row to Table
    public void addBill(products product)
    {
        ContentValues list=new ContentValues();
        SQLiteDatabase db = getWritableDatabase();

        String query="SELECT * FROM "+TABLE_NAME+";";


        //Cursor to point to the location in your results
        Cursor c=db.rawQuery(query,null);
        //move to the first row in your results
        c.moveToFirst();
            int samAmount=0;
            int flag=0;
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_name")) == product.get_name()) ;
            {
                flag=1;
                samAmount=c.getInt(c.getColumnIndex("_id"));
            }
            c.moveToNext();
        }

        if(flag==0) {
            list.put(COLUMN_NAME, product.get_name());
            list.put(COLUMN_AMOUNT, product.get_amount());
            db.insert(TABLE_NAME, null, list);
            db.close();
        }
        else
        {
            String q="DELETE FROM "+TABLE_NAME+" WHERE " +COLUMN_NAME+"=\""+product.get_name()+"\";";
            db.execSQL(q);
String q1="INSERT INTO "+TABLE_NAME+" ( "+COLUMN_NAME+" , "+COLUMN_AMOUNT+" ) VALUES ( "+product.get_name()+", "+(product.get_amount()+samAmount)+");";
            db.execSQL(q1);
            /*ContentValues newValue=new ContentValues();
            newValue.put(COLUMN_AMOUNT,product.get_amount()+samAmount);
            //db.update(TABLE_NAME,newValue,nm,null);
            newValue.put(COLUMN_NAME, product.get_name());
            db.insert(TABLE_NAME,null,newValue);
            db.close();
*/
        }
    }

    //Deleting a Row From Table
    public void deleteBill(String product_name)
    {
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM "+TABLE_NAME+" WHERE " +COLUMN_NAME+"=\""+product_name+"\";";
        db.execSQL(query);
        db.close();
    }

    //Printing Data
    public String printBill()
    {
        String DBString="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+";";


        //Cursor to point to the location in your results
        Cursor c=db.rawQuery(query,null);
        //move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("_name"))!=null);
            {
                DBString += c.getString(c.getColumnIndex("_name"));
                DBString +="    ";
                DBString +=c.getString(c.getColumnIndex("_amount"));
                DBString +="\n";
            }
            c.moveToNext();
        }
        db.close();
        return DBString;
    }
}
