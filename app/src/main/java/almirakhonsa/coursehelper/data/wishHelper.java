package almirakhonsa.coursehelper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import almirakhonsa.coursehelper.data.wishContract.wishEntry;
/**
 * Created by almirakhonsa on 11/12/2017.
 */

public class wishHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "shelter.db";
        private static final int DATABASE_VERSION = 1;

        public wishHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            String sql_create_course_table = "CREATE TABLE " + wishEntry.TABLE_NAME + "("
                    + wishEntry._ID + " INTEGER_PRIMARY_KEY_AUTO_INCREMENT, "
                    + wishEntry.COLUMN_NAME + " TEXT_NOT_NULL, "
                    + wishEntry.COLUMN_UNIV + " TEXT, "
                    + wishEntry.COLUMN_COURSE + " INTEGER_NOT_NULL, "
                    + wishEntry.COLUMN_CITY + " TEXT);";
            db.execSQL(sql_create_course_table);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_create_course_table = "DROP TABLE IF EXISTS " + wishEntry.TABLE_NAME;
        db.execSQL(sql_create_course_table);
        onCreate(db);
    }
}
