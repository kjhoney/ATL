package com.example.alltimeline;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String EVENTID = "eventid";
        public static final String NAME = "name";
        public static final String YEAR = "year";
        public static final String LENGTH = "length";
        public static final String _TABLENAME0 = "categorytable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +EVENTID+" text not null , "
                +NAME+" text not null , "
                +YEAR+" integer not null , "
                +LENGTH+" integer not null );";
    }
}
