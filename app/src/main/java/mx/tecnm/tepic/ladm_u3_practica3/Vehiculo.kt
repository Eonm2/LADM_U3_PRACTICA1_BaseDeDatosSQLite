package mx.tecnm.tepic.ladm_u3_practica1_sqlite

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import mx.tecnm.tepic.ladm_u3_practica3.BaseDatos
import mx.tecnm.tepic.ladm_u3_practica3.MainActivity3

class Vehiculo (p: Context){
    //LO MAS ABSTRACTO POSIBLE = CONTROLADOR
    //MODELO,       VISTA,          CONTROLADOR
    //BASEDATOS,    MAINACTIVITY    VEHICULO(INSERTAR, CONSULTAR, BORRAR, ACTUALIZAR)

    var placa = ""
    var marca = ""
    var modelo = ""
    var annio = 1
    var idconductor = 1
    val pnt = p

    fun insertar(): Boolean{
        val tablaVehiculo = BaseDatos(pnt,"pizzeria",null,1).writableDatabase
        val datos = ContentValues()
        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("annio",annio)
        datos.put("idconductor",idconductor)
        val resultado = tablaVehiculo.insert("VEHICULO",null,datos)
        //EL ,MÉTODO INSERT REGRESA UN ID ÚNICO DE RENGLÓN DE TABLA. REGRESA -1 SI NO PUDO
        if(resultado == -1L) {
            return false
        }
        return true
    }

    fun consultar(): ArrayList<String>{
        //SELECT * FROM CONDUCTOR
        val tablaVehiculo = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"), null,null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getInt(4).toString()+"\n"+cursor.getInt(5).toString()
                resultado.add(dato)
            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
        }
        return resultado
    }

    fun obtenerIDs(): ArrayList<Int>{
        //SELECT * FROM VEHICULO
        val tablaVehiculo = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"), null,null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                var dato = cursor.getInt(0)
                resultado.add(dato)
            }while(cursor.moveToNext())
        }
        return resultado
    }

    fun eliminar(idEliminar:Int):Boolean{
        val tablaVehiculo = BaseDatos(pnt,"pizzeria",null,1).writableDatabase
        val resultado = tablaVehiculo.delete("VEHICULO","IDVEHICULO=?", arrayOf(idEliminar.toString()))
        if(resultado==0) return false
        return true
    }

    fun consultar(idABuscar:String): Vehiculo {
        val tablaVehiculo = BaseDatos(pnt,"pizzeria",null,1).readableDatabase
        val cursor = tablaVehiculo.query("VEHICULO", arrayOf("*"),"IDVEHICULO=?", arrayOf(idABuscar),null,null,null)
        val vehiculo = Vehiculo(MainActivity3())

        if(cursor.moveToFirst()){
            vehiculo.placa = cursor.getString(1)
            vehiculo.marca = cursor.getString(2)
            vehiculo.modelo = cursor.getString(3)
            vehiculo.annio = cursor.getInt(4)
            vehiculo.idconductor = cursor.getInt(5)
        }
        return vehiculo
    }

    fun actualizar(idActualizar : String):Boolean{
        val tablaVehiculo = BaseDatos(pnt,"pizzeria",null,1).writableDatabase
        val datos = ContentValues()
        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("annio",annio)
        datos.put("idconductor",idconductor)
        val resultado = tablaVehiculo.update("VEHICULO",datos,"IDVEHICULO=?", arrayOf(idActualizar))
        if(resultado==0)return false
        return true
    }

    //Guardar los Vehiculos
}