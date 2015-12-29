package kevinwilde.unitconverter;

import android.content.ContentValues;
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

    public long InsertRecentConversion(String conversion){
//        mDbHelper.getWritableDatabase().execSQL(
//                "INSERT INTO " + mDbHelper.TABLE_RECENT_CONVERSIONS
//                        + " (" + mDbHelper.COLUMN_CONVERSION_STRING + ")"
//                        + " VALUES (?)",
//                new String[] { conversion }
//        );
        ContentValues values = new ContentValues();
        values.put(mDbHelper.COLUMN_CONVERSION_STRING, conversion);
        return mDbHelper.getWritableDatabase().insert(mDbHelper.TABLE_RECENT_CONVERSIONS, null, values);
    }

    public long InsertSavedConversion(String conversion){
//        mDbHelper.getWritableDatabase().execSQL(
//                "INSERT INTO " + mDbHelper.TABLE_SAVED_CONVERSIONS
//                        + " (" + mDbHelper.COLUMN_CONVERSION_STRING + ")"
//                        + " VALUES (?)",
//                new String[] { conversion }
//        );
        ContentValues values = new ContentValues();
        values.put(mDbHelper.COLUMN_CONVERSION_STRING, conversion);
        return mDbHelper.getWritableDatabase().insert(mDbHelper.TABLE_SAVED_CONVERSIONS, null, values);
    }

    public void DeleteRecentConversion(long id){
        String whereClause = mDbHelper.COLUMN_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        mDbHelper.getWritableDatabase().delete(mDbHelper.TABLE_RECENT_CONVERSIONS, whereClause, whereArgs);
    }

    public void DeleteSavedConversion(long id){
//        mDbHelper.getWritableDatabase().execSQL(
//                "DELETE FROM " + mDbHelper.TABLE_SAVED_CONVERSIONS
//                        + " WHERE " + mDbHelper.COLUMN_ID + " = ?",
//                new Object[] { id }
//        );
        String whereClause = mDbHelper.COLUMN_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        mDbHelper.getWritableDatabase().delete(mDbHelper.TABLE_SAVED_CONVERSIONS, whereClause, whereArgs);
    }

    public Cursor GetRecentConversions(){
        return mDbHelper.getReadableDatabase().rawQuery(
                "SELECT " + mDbHelper.COLUMN_ID + ", " + mDbHelper.COLUMN_CONVERSION_STRING
                        + " FROM " + mDbHelper.TABLE_RECENT_CONVERSIONS
                        + " WHERE DELETED = 0"
                        + " ORDER BY " + mDbHelper.COLUMN_CONVERSION_DATE + " DESC",
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
                        + " ORDER BY " + mDbHelper.COLUMN_CONVERSION_DATE + " DESC",
                null
        );
    }
}
