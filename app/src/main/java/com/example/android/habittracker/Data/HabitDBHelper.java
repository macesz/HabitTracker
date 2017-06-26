package com.example.android.habittracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.android.habittracker.Data.HabitContract.HabitEntry;
import com.example.android.habittracker.R;

import static com.example.android.habittracker.Data.HabitContract.HabitEntry.COLUMN_SPORT_CALORI;
import static com.example.android.habittracker.Data.HabitContract.HabitEntry.COLUMN_SPORT_DATE;
import static com.example.android.habittracker.Data.HabitContract.HabitEntry.COLUMN_SPORT_TIME;
import static com.example.android.habittracker.Data.HabitContract.HabitEntry.COLUMN_SPORT_TYPE;


/**
 * Created by orsi on 25/06/2017.
 */

public class HabitDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDBHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "gym.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDBHelper}.
     *
     * @param context of the app
     */
    public HabitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SPORT_TYPE + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_SPORT_DATE + " TEXT NOT NULL,"
                + HabitEntry.COLUMN_SPORT_TIME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_SPORT_CALORI + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String SQL_DROP_TABLE = "DROP TABLE " + HabitContract.HabitEntry.TABLE_NAME + ";";
        db.execSQL(SQL_DROP_TABLE);

        onCreate(db);
    }

    public void insertSport(Context context, ContentValues values) {
        // Gets the database in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //ID of the new row
        long newRowId = -1;

        // Check if input values are suited for format

        if (values.containsKey(COLUMN_SPORT_TYPE) &
                values.containsKey(COLUMN_SPORT_DATE) &
                values.containsKey(COLUMN_SPORT_TIME) &
                values.containsKey(COLUMN_SPORT_CALORI)) {
            newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        } else {
            Log.e("insertSport()", "Wrong input format");
        }

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            displayToast(context, context.getString(R.string.SAVE_ERROR));
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            displayToast(context, context.getString(R.string.SAVE_SUCESS) + newRowId);
        }
    }

    private void displayToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
