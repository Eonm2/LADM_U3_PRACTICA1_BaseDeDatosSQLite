package mx.tecnm.tepic.ladm_u3_practica1_sqlite

import android.content.ContentValues
import android.content.Context
import mx.tecnm.tepic.ladm_u3_practica3.BaseDatos
import mx.tecnm.tepic.ladm_u3_practica3.MainActivity2

class Conductor (p: Context){
    //LO MAS ABSTRACTO POSIBLE = CONTROLADOR
    //MODELO,       VISTA,          CONTROLADOR
    //BASEDATOS,    MAINACTIVITY    CONDUCTOR(INSERTAR, CONSULTAR, BORRAR, ACTUALIZAR)

    var nombre = ""
    var domicilio = ""
    var nolicencia = ""
    var vence = ""
    val pnt = p

    fun insertar(): Boolean{
        val tablaConductor = BaseDatos(pnt,"pizzeria",null,1).writableDatabase
        val datos = ContentValues()
        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        datos.put("vence",vence)
        val resultado = tablaConductor.insert("CONDUCTOR",null,datos)
        //EL ,MÉTODO INSERT REGRESA UN ID ÚNICO DE RENGLÓN DE TABLA. REGRESA -1 SI NO PUDO
        if(resultado == -1L) {
            return false
        }
        return true
    }

    fun consultar(): ArrayList<String>{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        val resultado = ArrayList<String>()
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"), null,null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(dato)
            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
        }
        return resultado
    }

    fun obtenerIDs(): ArrayList<Int>{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"), null,null,null,null,null)
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
        val tablaConductor = BaseDatos(pnt,"pizzeria",null,1).writableDatabase
        val resultado = tablaConductor.delete("CONDUCTOR","IDCONDUCTOR=?", arrayOf(idEliminar.toString()))
        if(resultado==0) return false
        return true
    }

    fun consultar(idABuscar:String): Conductor {
        val tablaConductor = BaseDatos(pnt,"pizzeria",null,1).readableDatabase
        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"),"IDCONDUCTOR=?", arrayOf(idABuscar),null,null,null)
        val conductor = Conductor(MainActivity2())
        if(cursor.moveToFirst()){
            conductor.nombre = cursor.getString(1)
            conductor.domicilio = cursor.getString(2)
            conductor.nolicencia = cursor.getString(3)
            conductor.vence = cursor.getString(4)
        }
        return conductor
    }

    fun actualizar(idActualizar : String):Boolean{
        val tablaConductor = BaseDatos(pnt,"pizzeria",null,1).writableDatabase
        val datos = ContentValues()

        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        datos.put("vence",vence)
        val resultado = tablaConductor.update("CONDUCTOR",datos,"IDCONDUCTOR=?", arrayOf(idActualizar))
        if(resultado==0)return false
        return true
    }

//-------------------------------------------------------------------------------------------------------

    fun consulta1(): ArrayList<String>{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        val resultado = ArrayList<String>()

        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"), "VENCE < date('now')",null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(dato)
            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
        }
        return resultado
    }
 //------------------------------------------------------------------------------------------------------------------------
 fun consulta2(): ArrayList<String>{
     //SELECT * FROM CONDUCTOR
     val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
     val resultado = ArrayList<String>()

     val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"), "IDCONDUCTOR NOT IN (SELECT IDCONDUCTOR FROM VEHICULO) ",null,null,null,null)
     //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
     if(cursor.moveToFirst()){
         //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
         do{
             //LEER LA DATA
             var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
             resultado.add(dato)
         }while(cursor.moveToNext())
     }else{
         //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
         resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
     }
     return resultado
 }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    fun consulta3(annio: String): ArrayList<String>{
    //SELECT * FROM CONDUCTOR
    val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
    val resultado = ArrayList<String>()

    val cursor = tablaConductor.query("VEHICULO", arrayOf("*"), "ANNIO <= date('now','start of year')-?",
        arrayOf(annio),null,null,null)
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

 //-------------------------------------------------------------------------------------------------------------------------------------------------------------
 fun consulta4(placa: String): ArrayList<String> {
     //SELECT * FROM CONDUCTOR
     val tablaConductor = BaseDatos(pnt, "pizzeria", null, 1).readableDatabase
     val resultado = ArrayList<String>()

     val cursor = tablaConductor.query(
         "CONDUCTOR",
         arrayOf("*"),
         "IDCONDUCTOR IN (Select IDCONDUCTOR FROM VEHICULO WHERE PLACA LIKE ?) ",
         arrayOf(placa),
         null,
         null,
         null
     )
     //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
     if (cursor.moveToFirst()) {
         //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
         do {
             //LEER LA DATA
             var dato =
                 cursor.getString(1) + "\n" + cursor.getString(2) + "\n" + cursor.getString(3) + "\n" + cursor.getString(
                     4
                 )
             resultado.add(dato)
         } while (cursor.moveToNext())
     } else {
         //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
         resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
     }
     return resultado
 }
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    fun consulta41(nombre: String): ArrayList<String>{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        val resultado = ArrayList<String>()

        val cursor = tablaConductor.query("VEHICULO", arrayOf("*"), "IDCONDUCTOR IN (Select IDCONDUCTOR  FROM CONDUCTOR WHERE NOMBRE LIKE ?)",
            arrayOf(nombre),null,null,null)
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




    /*Empieza-----------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    //-------------------------------------------------------------------------------------------------------

    fun consulta1CSV(): String{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        var resultado = ""

        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"), "VENCE < date('now')",null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                 resultado = cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4)+"\n"

            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado = "NO SE ENCONTRO DATA A MOSTRAR"
        }
        return resultado
    }
    //------------------------------------------------------------------------------------------------------------------------
    fun consulta2CSV(): String{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        var resultado = ""

        val cursor = tablaConductor.query("CONDUCTOR", arrayOf("*"), "IDCONDUCTOR NOT IN (SELECT IDCONDUCTOR FROM VEHICULO) ",null,null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                 resultado += cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4)+"\n"

            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado = "NO SE ENCONTRO DATA A MOSTRAR"
        }
        return resultado
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    fun consulta3CSV(annio: String): String{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        var resultado = ""

        val cursor = tablaConductor.query("VEHICULO", arrayOf("*"), "ANNIO <= date('now','start of year')-?",
            arrayOf(annio),null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                resultado += cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getInt(4).toString()+","+cursor.getInt(5).toString()+"\n"

            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado = "NO SE ENCONTRO DATA A MOSTRAR"
        }
        return resultado
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
    fun consulta4CSV(placa: String): String {
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria", null, 1).readableDatabase
        var resultado = ""

        val cursor = tablaConductor.query(
            "CONDUCTOR",
            arrayOf("*"),
            "IDCONDUCTOR IN (Select IDCONDUCTOR FROM VEHICULO WHERE PLACA LIKE ?) ",
            arrayOf(placa),
            null,
            null,
            null
        )
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if (cursor.moveToFirst()) {
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do {
                //LEER LA DATA
                    resultado +=
                    cursor.getString(1) + "," + cursor.getString(2) + "," + cursor.getString(3) + "," + cursor.getString(
                        4
                    )+"\n"

            } while (cursor.moveToNext())
        } else {
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado = "NO SE ENCONTRO DATA A MOSTRAR"
        }
        return resultado
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    fun consulta41CSV(nombre: String): String{
        //SELECT * FROM CONDUCTOR
        val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
        var resultado = ""

        val cursor = tablaConductor.query("VEHICULO", arrayOf("*"), "IDCONDUCTOR IN (Select IDCONDUCTOR  FROM CONDUCTOR WHERE NOMBRE LIKE ?)",
            arrayOf(nombre),null,null,null)
        //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
        if(cursor.moveToFirst()){
            //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
            do{
                //LEER LA DATA
                resultado += cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getInt(4).toString()+","+cursor.getInt(5).toString()

            }while(cursor.moveToNext())
        }else{
            //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
            resultado ="NO SE ENCONTRO DATA A MOSTRAR"
        }
        return resultado
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------
fun consultaConductorVehiculo(): String{
    //SELECT * FROM CONDUCTOR
    val tablaConductor = BaseDatos(pnt, "pizzeria",null,1).readableDatabase
    var resultado = ""

    val cursor = tablaConductor.query("CONDUCTOR as 'C',VEHICULO", arrayOf("*"), "C.IDCONDUCTOR  IN (SELECT IDCONDUCTOR FROM VEHICULO) ",null,null,null,null)
    //Cursor es un objeto tipo tabla dinamica que contiene los resultados de una consulta
    if(cursor.moveToFirst()){
        //Si se posiciona en 1er renglon resultado, si se obtuvo resultados
        do{
            //LEER LA DATA
            resultado += cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4)+","+cursor.getString(5)+","+cursor.getString(6)+","+cursor.getString(7)+","+cursor.getInt(8).toString()+","+cursor.getInt(9).toString()+"\n"

        }while(cursor.moveToNext())
    }else{
        //Si moveFirst regresa falso, entra al ELSE significando que no hay ni siquiera 1 resultado
        resultado ="NO SE ENCONTRO DATA A MOSTRAR"
    }
    return resultado
}
}