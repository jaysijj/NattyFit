package com.example.nattyfit.DataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    // Responsável pela criação e e atualização do Banco de Dados

    private static final String DATABASE_NAME = "nattyfitDB.db";
    private static final int DATABASE_VERSION = 2; // Incrementando a versão do banco de dados

    //Definição das tabelas
    public static final String TABLE_NAME = "pessoa";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_ALTURA = "altura";
    public static final String COLUMN_PESCOCO = "pescoco";
    public static final String COLUMN_CINTURA = "cintura";
    public static final String COLUMN_QUADRIL = "quadril";
    public static final String COLUMN_SEXO = "sexo";
    public static final String COLUMN_RESULTADO = "resultado";

    // Script de Criação do Banco de Dados
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME +
            "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NOME + " text not null, " +
            COLUMN_ALTURA + " real not null, " +
            COLUMN_PESCOCO + " real not null, " +
            COLUMN_CINTURA + " real not null, " +
            COLUMN_QUADRIL + " real not null, " +
            COLUMN_SEXO + " text not null, " +
            COLUMN_RESULTADO + " real not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

