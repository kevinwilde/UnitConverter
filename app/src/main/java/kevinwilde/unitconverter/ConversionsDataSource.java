package kevinwilde.unitconverter;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Kevin Wilde on 12/27/2015.
 */
public class ConversionsDataSource {

    private DbHelper mDbHelper;

    public ConversionsDataSource(Context context){
        mDbHelper = DbHelper.getInstance(context);
    }

    public void InsertRecentConversion(String conversion){
        mDbHelper.getWritableDatabase().execSQL(
                "INSERT INTO " + mDbHelper.TABLE_RECENT_CONVERSIONS
                        + " (" + mDbHelper.COLUMN_CONVERSION_STRING + ")"
                        + " VALUES (?)",
                new String[] { conversion }
        );
    }

    public void InsertSavedConversion(String conversion){
        mDbHelper.getWritableDatabase().execSQL(
                "INSERT INTO " + mDbHelper.TABLE_SAVED_CONVERSIONS
                        + " (" + mDbHelper.COLUMN_CONVERSION_STRING + ")"
                        + " VALUES (?)",
                new String[] { conversion }
        );
    }

    public void DeleteRecentConversion(){

    }

    public void DeleteSavedConversion(){

    }

    public Cursor GetRecentConversions(){
        return mDbHelper.getReadableDatabase().rawQuery(
                "SELECT " + mDbHelper.COLUMN_ID + ", " + mDbHelper.COLUMN_CONVERSION_STRING
                        + " FROM " + mDbHelper.TABLE_RECENT_CONVERSIONS
                        + " WHERE DELETED = 0"
                        + " ORDER BY " + mDbHelper.COLUMN_CONVERSION_DATE,
                null
        );
    }

    //TODO: Add column for conversion type to database, so you can organize by conversion type as well...
    public Cursor GetSavedConversions(){
        //return mDbHelper.getReadableDatabase().query(mDbHelper.TABLE_SAVED_CONVERSIONS, allColumns, null, null, null, null, null);
        return mDbHelper.getReadableDatabase().rawQuery(
                "SELECT " + mDbHelper.COLUMN_ID + ", " + mDbHelper.COLUMN_CONVERSION_STRING
                        + " FROM " + mDbHelper.TABLE_SAVED_CONVERSIONS
                        + " WHERE DELETED = 0"
                        + " ORDER BY " + mDbHelper.COLUMN_CONVERSION_DATE,
                null
        );
    }
}
