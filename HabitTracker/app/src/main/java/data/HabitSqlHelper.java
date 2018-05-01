package data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static data.habitContract.CREATE;
import static data.habitContract.SQL_DELETE;
/**
 * Created by Amal Alshaikh on 14/8/2017.
 */
public class HabitSqlHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Habit.db";
    public HabitSqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);}
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE);
        onCreate(sqLiteDatabase);}}