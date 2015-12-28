package kevinwilde.unitconverter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kevin Wilde on 12/27/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UnitConverter.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_RECENT_CONVERSIONS = "Recent_Conversions";
    public static final String TABLE_SAVED_CONVERSIONS = "Saved_Conversions";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONVERSION_STRING = "conversion_string";
    public static final String COLUMN_CONVERSION_DATE = "conversion_date";
    public static final String COLUMN_DELETED = "deleted";

    private static DbHelper singleton = null;

    public synchronized static DbHelper getInstance(Context context){
        if (singleton == null){
            singleton = new DbHelper(context.getApplicationContext());
        }
        return singleton;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_RECENT_CONVERSIONS
                + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_CONVERSION_STRING + " TEXT, "
                        + COLUMN_CONVERSION_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                        + COLUMN_DELETED + " BOOLEAN DEFAULT 0"
                + ")"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_SAVED_CONVERSIONS
                        + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_CONVERSION_STRING + " TEXT, "
                        + COLUMN_CONVERSION_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                        + COLUMN_DELETED + " BOOLEAN DEFAULT 0"
                        + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECENT_CONVERSIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_CONVERSIONS);
        onCreate(db);
    }
}
