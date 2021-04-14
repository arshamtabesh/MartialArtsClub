package com.arman.martialartsclub.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabseSQL = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "%s TEXT, %s REAL, %s TEXT )", MARTIAL_ARTS_TABLE, ID_KEY, NAME_KEY, PRICE_KEY, COLOR_KEY);

        db.execSQL(createDatabseSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public void addMartialArt(MartialArt martialArtObject) {
        SQLiteDatabase mDatabase = getWritableDatabase();
        String addMartialArtsSQLCommand =
                String.format("INSERT INTO %s VALUES(null,'%s','%2f','%s')",
                        MARTIAL_ARTS_TABLE,
                        martialArtObject.getMartialArtName(),
                        martialArtObject.getMartialArtPrice(),
                        martialArtObject.getMartialArtColor());

        mDatabase.execSQL(addMartialArtsSQLCommand);
        mDatabase.close();

    }

    public void deleteMarschalArtObjectByID(int id) {
        SQLiteDatabase mSQLiteDatabase = getWritableDatabase();
        String deleteMarshalArtSQLCommand =
                String.format("DELETE FROM %s WHERE %s = %d", MARTIAL_ARTS_TABLE, ID_KEY, id);
        mSQLiteDatabase.execSQL(deleteMarshalArtSQLCommand);
        mSQLiteDatabase.close();
    }

    public void modifyMarshalArtObject(int marshalArtID, String martialArtName,
                                       double martialArtPrice,
                                       String martialArtColor) {

        SQLiteDatabase mDatabase = getWritableDatabase();
        String modifyMarshalArtSQLCommand = String.format("UPDATE %s set %s = '%s', %s= '%2f', %s = '%s' WHERE %s = %d",
                MARTIAL_ARTS_TABLE, NAME_KEY, martialArtName, PRICE_KEY
                , martialArtPrice, COLOR_KEY, martialArtColor, ID_KEY, marshalArtID);

        mDatabase.execSQL(modifyMarshalArtSQLCommand);
        mDatabase.close();
    }

    public ArrayList<MartialArt> returnAllMartialArtObjects() {
        SQLiteDatabase mDatabase = getWritableDatabase();
        String sqlQueryCommand = "SELECT * FROM " + MARTIAL_ARTS_TABLE;
        Cursor mCursor = mDatabase.rawQuery(sqlQueryCommand, null);
        ArrayList<MartialArt> mMartialArts = new ArrayList<>();

        while (mCursor.moveToNext()) {
            MartialArt currentMartialArtObject = new MartialArt(mCursor.getInt(0),
                    mCursor.getString(1),
                    mCursor.getDouble(2),
                    mCursor.getString(3));

            mMartialArts.add(currentMartialArtObject);

        }
        mDatabase.close();
        return mMartialArts;
    }

    public MartialArt returnMartialArtObjectByID(int id) {
        SQLiteDatabase mDatabase = getWritableDatabase();
        String sqlQueryCommand = String.format("SELECT * FROM %s WHERE %s = %d",
                MARTIAL_ARTS_TABLE, ID_KEY, id);

        Cursor mCursor = mDatabase.rawQuery(sqlQueryCommand, null);

        if (!mCursor.moveToFirst() ) return null;
        MartialArt mMartialArt = new MartialArt(mCursor.getInt(0),
                mCursor.getString(1),
                mCursor.getDouble(2),
                mCursor.getString(3));

        mDatabase.close();
        return mMartialArt;

    }
}
