package wa.filipe.finalproject.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static String nome="Warframe.db";
    private static String table1="CREATE TABLE User" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "username VARCHAR(20),"+
            "password VARCHAR(50),"+
            "favorite VARCHAR(20))";
    private static String table2="CREATE TABLE Build" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "slots INTEGER(11)," +
            "rank INTEGER(11)," +
            "catalyst INTEGER(11)," +
            "mods VARCHAR(500))";

    public DBHelper(Context contexto) {
        super(contexto, nome, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(table1);
        banco.execSQL(table2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int versaoAntiga, int versaoNova) {
        banco.execSQL("DROP TABLE User");

    }


}