package com.example.dictionary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
//Create a helper object to create, open, and/or manage a database.

import androidx.annotation.Nullable;

import com.example.dictionary.modules.Object;
import com.example.dictionary.modules.Type;

import java.util.ArrayList;
import java.util.List;

import static com.example.dictionary.db.Constants.SYNONYM;
import static com.example.dictionary.db.Constants.DATE;
import static com.example.dictionary.db.Constants.DB_NAME;
import static com.example.dictionary.db.Constants.DB_VERSION;
import static com.example.dictionary.db.Constants.ID;
import static com.example.dictionary.db.Constants.NAME;
import static com.example.dictionary.db.Constants.NOTE;
import static com.example.dictionary.db.Constants.DEFINITION;
import static com.example.dictionary.db.Constants.TABLE_NAME;
import static com.example.dictionary.db.Constants.ORIGIN;
import static com.example.dictionary.db.Constants.TYPE;
//The android.database.sqlite.SQLiteOpenHelper class is used for//
// database creation and version management.//

public class DatabaseHelper extends SQLiteOpenHelper {
//Database Helper class whose only function is to provide for the creation,//
// modification, and deletion of tables in the database.
    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DEFINITION + " TEXT, " +
                SYNONYM + " TEXT, " +
                ORIGIN + " TEXT, " +
                TYPE + " TEXT, " +
                DATE + " TEXT, " +
                NOTE + " TEXT);";
        db.execSQL(createTable);
    }
//onUpgrade. Called when the database needs to be upgraded.//
// The implementation should use this method to drop tables, //
// add tables, or do anything else it needs to upgrade to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
    }

    public void insert(Object object) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, object.getName());
        contentValues.put(DEFINITION, object.getDefinition());
        contentValues.put(SYNONYM, object.getSynonym());
        contentValues.put(ORIGIN, object.getOrigin());
        contentValues.put(DATE, object.getDate());
        contentValues.put(NOTE, object.getNote());

        switch (object.getType()) {
            case Arabicword: {
                contentValues.put(TYPE, "Arabicword");
                break;
            }
            case Russianword: {
                contentValues.put(TYPE, "Russianword");
                break;
            }
            case Latinword: {
                contentValues.put(TYPE, "Latinword");
                break;
            }
            case Spanishword: {
                contentValues.put(TYPE, "Spanishword");
                break;
            }
            case Frenchword: {
                contentValues.put(TYPE, "Frenchword");
                break;
            }
        }

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void delete(Object object) {
        SQLiteDatabase database = getReadableDatabase();
        database.delete(TABLE_NAME, NAME + " LIKE ?", new String[]{object.getName()});
        database.close();
    }

    public void update(Object object, int id) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, object.getName());
        contentValues.put(DEFINITION, object.getDefinition());
        contentValues.put(SYNONYM, object.getSynonym());
        contentValues.put(ORIGIN, object.getOrigin());
        contentValues.put(DATE, object.getDate());
        contentValues.put(NOTE, object.getNote());

        switch (object.getType()) {
            case Arabicword: {
                contentValues.put(TYPE, "Arabicword");
                break;
            }
            case Russianword: {
                contentValues.put(TYPE, "Russianword");
                break;
            }
            case Latinword: {
                contentValues.put(TYPE, "Latinword");
                break;
            }
            case Spanishword: {
                contentValues.put(TYPE, "Spanishword");
                break;
            }
            case Frenchword: {
                contentValues.put(TYPE, "Frenchword");
                break;
            }
        }

        database.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{String.valueOf(id)});
        database.close();
    }

    public List<Object> getByName(){

        List<Object> objectList = new ArrayList();
        String query = "SELECT * FROM " + TYPE + " ORDER BY " + NAME;
        SQLiteDatabase database = getWritableDatabase();
        Cursor rowQuery = database.rawQuery(query, null);
        if(rowQuery.moveToFirst()){
            do{
                int id = rowQuery.getInt(0);
                String name = rowQuery.getString(1);
                String definition = rowQuery.getString(2);
                String Synonym = rowQuery.getString(3);
                String origin = rowQuery.getString(4);
                Type type = Type.None;
                String date = rowQuery.getString(6);
                String note = rowQuery.getString(7);
                switch (rowQuery.getString(5)){
                    case "Arabicword": {
                        type = Type.Arabicword;
                        break;
                    }
                    case "Russianword": {
                        type = Type.Russianword;
                        break;
                    }
                    case "Latinword": {
                        type = Type.Latinword;
                        break;
                    }
                    case "Spanishword": {
                        type = Type.Spanishword;
                        break;
                    }
                    case "Frenchword": {
                        type = Type.Frenchword;
                        break;
                    }
                }
                Object object = new Object(id, name, definition, Synonym, origin, type, date, note);
                objectList.add(object);

            }while (rowQuery.moveToNext());

        }

        return objectList;
    }

    public List<Object> getObjects(){

        List<Object> objectList = new ArrayList();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = getWritableDatabase();
        Cursor rowQuery = database.rawQuery(query, null);
        if(rowQuery.moveToFirst()){
            do{
                int id = rowQuery.getInt(0);
                String name = rowQuery.getString(1);
                String definition = rowQuery.getString(2);
                String Synonym = rowQuery.getString(3);
                String origin = rowQuery.getString(4);
                String note = rowQuery.getString(7);
                Type type = Type.None;
                if(rowQuery.getString(5) != null) {
                    switch (rowQuery.getString(5)) {
                        case "Arabicword": {
                            type = Type.Arabicword;
                            break;
                        }
                        case "Russianword": {
                            type = Type.Russianword;
                            break;
                        }
                        case "Latinword": {
                            type = Type.Latinword;
                            break;
                        }
                        case "Spanishword": {
                            type = Type.Spanishword;
                            break;
                        }
                        case "Frenchword": {
                            type = Type.Frenchword;
                            break;
                        }
                    }
                }
                String date = rowQuery.getString(6);
                Object object = new Object(id, name, definition, Synonym, origin, type, date, note);
                objectList.add(object);

            }while (rowQuery.moveToNext());

        }

        return objectList;
    }
}
