package mx.tecnm.tepic.ladm_u3_practica3

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p: SQLiteDatabase?) {
        p?.execSQL("CREATE TABLE CONDUCTOR(IDCONDUCTOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(200), DOMICILIO VARCHAR(200), NOLICENCIA VARCHAR(200), VENCE DATE)")
        p?.execSQL("CREATE TABLE VEHICULO(IDVEHICULO INTEGER PRIMARY KEY AUTOINCREMENT, PLACA VARCHAR(200), MARCA VARCHAR(200), MODELO VARCHAR(200), ANNIO INTEGER, IDCONDUCTOR INTEGER)")
        //p?.execSQL("INSERT INTO VEHICULO VALUES ('15265F','Honda','City',2020,1)")
    }

    override fun onUpgrade(p: SQLiteDatabase?, p1: Int, p2: Int) {
        //p?.execSQL("DROP TABLE IF EXISTS CONDUCTOR");
        //p?.execSQL("DROP TABLE IF EXISTS VEHICULO");
        //p?.execSQL("CREATE TABLE VEHICULO(IDVEHICULO INTEGER PRIMARY KEY AUTOINCREMENT, PLACA VARCHAR(200), MARCA VARCHAR(200), MODELO VARCHAR(200), ANNIO INTEGER, IDCONDUCTOR INTEGER, FOREIGN KEY(IDCONDUCTOR) REFERENCES CONDUCTOR(IDCONDUCTOR))")
        //onCreate(p);
        //p?.execSQL("INSERT INTO VEHICULO VALUES ('15265F','Honda','City',2020,1)")*/
    }

}