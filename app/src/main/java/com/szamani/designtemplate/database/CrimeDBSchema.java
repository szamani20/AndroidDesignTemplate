package com.szamani.designtemplate.database;

/**
 * Created by Szamani on 3/17/2016.
 *
 * Nothing special. Just the schema of the database
 *
 */
public class CrimeDBSchema {
    public static final class CrimeTable {
        public static final String NAME = "crimes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
