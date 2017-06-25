package com.example.android.habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by orsi on 25/06/2017.
 */

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract(){

    }

    /**
     * Inner class that defines constant values for the Sport database table.
     * Each entry in the table represents a single sport class in a gym.
     */

    public static abstract class HabitEntry implements BaseColumns {

        /** Name of database table for sports */

        public static final String TABLE_NAME = "Sport";
        /**
         * Unique ID number for the sport (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Type of the sport.
         *
         * The only possible values are {@link #TYPE_YOGA}, {@link #TYPE_PILATES, {@link #Type_Zumba},
         *  or {@link #Type_TRX}, {@link #Type_Interwal}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_SPORT_TYPE ="type";

        /**
         * Time of the sport.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SPORT_TIME = "time";

        /**
         * Date of the sport.
         *
         * Type: TEXT
         */

        public final static String COLUMN_SPORT_DATE = "date";

        /**
         * Calori burn buy the sport.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SPORT_CALORI = "calories";


        /**
         * Possible values for the gender of the pets.
         */
        public static final int TYPE_YOGA = 0;
        public static final int TYPE_PILATES = 1;
        public static final int Type_TRX = 2;
        public static final int Type_ZUMBA = 3;
        public static final int Type_INTERWAL = 4;
    }
}
