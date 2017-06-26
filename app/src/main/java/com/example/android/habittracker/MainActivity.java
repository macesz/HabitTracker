package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.habittracker.Data.HabitContract;
import com.example.android.habittracker.Data.HabitDBHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDBHelper(this);

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_SPORT_TYPE, HabitContract.HabitEntry.TYPE_YOGA);
        values.put(HabitContract.HabitEntry.COLUMN_SPORT_DATE, "June 30");
        values.put(HabitContract.HabitEntry.COLUMN_SPORT_TIME, "1 hour");
        values.put(HabitContract.HabitEntry.COLUMN_SPORT_CALORI, "250");

        mDbHelper.insertSport(this, values);
    }

    private Cursor readData(SQLiteDatabase db){
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_SPORT_TYPE,
                HabitContract.HabitEntry.COLUMN_SPORT_DATE,
                HabitContract.HabitEntry.COLUMN_SPORT_TIME,
                HabitContract.HabitEntry.COLUMN_SPORT_CALORI
        };

        return db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }


    @Override
    protected void onStart() {
        super.onStart();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = readData(db);

        displayDatabaseInfo(cursor);
    }


    private void displayDatabaseInfo(Cursor cursor) {

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The sport table contains <number of rows in Cursor> sports.
            // _id - type - date - time - calories
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The sport table contains " + cursor.getCount() + " sport.\n\n");
            displayView.append(HabitContract.HabitEntry._ID + " - " +
                    HabitContract.HabitEntry.COLUMN_SPORT_TYPE + " - " +
                    HabitContract.HabitEntry.COLUMN_SPORT_DATE + " - " +
                    HabitContract.HabitEntry.COLUMN_SPORT_TIME + " - " +
                    HabitContract.HabitEntry.COLUMN_SPORT_CALORI + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int typeColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_SPORT_TYPE);
            int dateColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_SPORT_DATE);
            int timeColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_SPORT_TIME);
            int caloriColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_SPORT_CALORI);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentType = cursor.getString(typeColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentTime = cursor.getString(timeColumnIndex);
                int currentCalori = cursor.getInt(caloriColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentType + " - " +
                        currentDate + " - " +
                        currentTime + " - " +
                        currentCalori));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
