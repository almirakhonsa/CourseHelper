package almirakhonsa.coursehelper.data;

import android.provider.BaseColumns;

/**
 * Created by almirakhonsa on 11/12/2017.
 */

public class registerContract {
    private registerContract(){}

    public static final class regEntry implements BaseColumns {
        public final static String TABLE_NAME = "reg";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_PASS = "pass";
        public final static String COLUMN_EMAIL = "email";
    }
}
