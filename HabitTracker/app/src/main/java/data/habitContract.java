package data;
import android.provider.BaseColumns;
/**
 * Created by Amal Alshaikh on 14/8/2017.
 */
//https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow
public final class habitContract {
    public static final String CREATE =
            "CREATE TABLE " + hapit.TABLE_NAME + " (" +
                    hapit._ID + " INTEGER PRIMARY KEY," +
                    hapit.Colum_HABIT_NAME + " TEXT," +
                    hapit.Colum_DAY + " TEXT," +
                    hapit.Colum_done + " TEXT," +
                    hapit.Colum_GOAL + " INTEGER," +
                    hapit.Colum_ACHIEVABLE + " INTEGER)";
    // Inner class
    public static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + hapit.TABLE_NAME;
    private habitContract() {}
    public static class hapit implements BaseColumns {
        public static final String TABLE_NAME = "habit";
        public static final String Colum_HABIT_NAME = "Name";
        public static final String Colum_DAY = "day";
        public static final String Colum_done = "done";
        public static final String Colum_GOAL = "Goal";
        public static final String Colum_ACHIEVABLE = "achievable";

        public static final int habit_not_done = 1;
        public static final int habit_done = 2;
        public static final int habit_ACHIEVed = 1;
        public static final int habit_almost_ACHIEVed = 2;
        public static final int habit_Not_ACHIEVed = 3;}}







