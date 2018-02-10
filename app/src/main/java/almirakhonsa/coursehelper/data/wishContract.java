package almirakhonsa.coursehelper.data;

import android.provider.BaseColumns;

/**
 * Created by almirakhonsa on 11/12/2017.
 */

public final class wishContract {
    private wishContract(){}

    public static final class wishEntry implements BaseColumns{
        public final static String TABLE_NAME = "wish";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_UNIV = "univ";
        public final static String COLUMN_COURSE = "course";
        public final static String COLUMN_CITY = "city";

        public final static String COURSE = "Unknown";
        public final static String COURSE_LIA = "Lembaga Indonesia Merika (LIA)";
        public final static String COURSE_EF = "English First";
        public final static String COURSE_GO = "Ganesha Operation";
        public final static String COURSE_NEUTRON = "Neutron";
    }
}
