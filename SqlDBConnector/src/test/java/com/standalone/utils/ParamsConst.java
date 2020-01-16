package com.standalone.utils;

public class ParamsConst {

    private ParamsConst() {

    }

    public final static String driver = "org.sqlite.JDBC";
    public  final static String dbName = "sample";
    public  final static String connectionString = String.format("jdbc:sqlite:%s.db", dbName);
}
