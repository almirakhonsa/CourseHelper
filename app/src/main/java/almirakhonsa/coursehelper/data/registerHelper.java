package almirakhonsa.coursehelper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by almirakhonsa on 11/12/2017.
 */

public class registerHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public registerHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sql_create_course_table = "CREATE TABLE " + registerContract.regEntry.TABLE_NAME + "("
                + registerContract.regEntry._ID + " INTEGER_PRIMARY_KEY_AUTO_INCREMENT, "
                + registerContract.regEntry.COLUMN_NAME + " TEXT_NOT_NULL, "
                + registerContract.regEntry.COLUMN_PASS + " TEXT_NOT_NULL, "
                + registerContract.regEntry.COLUMN_EMAIL + " TEXT_NOT_NULL);";
        db.execSQL(sql_create_course_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}