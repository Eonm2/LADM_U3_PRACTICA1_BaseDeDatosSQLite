package mx.tecnm.tepic.ladm_u3_practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main4.*
import mx.tecnm.tepic.ladm_u3_practica1_sqlite.Conductor
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

var accion=0
class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)




        consulta1.setOnClickListener {
            accion=1
            mostrarConductoresVencidos()
        }
        consulta2.setOnClickListener {
            accion=2
            mostrarConsulta2()
        }
        consulta3.setOnClickListener {
            accion=3
            mostrarConsulta3()
        }
        consulta4.setOnClickListener {
            accion=4
            mostrarConsulta4Placa()
        }
        consulta41.setOnClickListener {
            accion=5
            mostrarConsultaNombre()
        }
        transformar.setOnClickListener {
            exportarCVS()
        }
        exporarConductorVehiculo.setOnClickListener {
            mostrarConductoresVehiculos()
        }
    }

    fun exportarCVS(){
        var exportar=""
        if (accion==1){
             exportar = Conductor(this).consulta1CSV()
        }
        else if(accion==2){
            exportar = Conductor(this).consulta2CSV()
        }else if(accion==3){
            exportar = Conductor(this).consulta3CSV(txtConsulta3.text.toString())
        }
        else if(accion==4){
            exportar = Conductor(this).consulta4CSV(txtConsulta4.text.toString())
        }
        else if(accion==5){
            exportar = Conductor(this).consulta41CSV(txtConsulta41.text.toString())
        }


            try {
                val tarjeta = getExternalFilesDir(null);
                val file = File(tarjeta?.getAbsolutePath(), "Consultas.csv")
                val osw = OutputStreamWriter(FileOutputStream(file))
                osw.write(exportar)

                osw.flush()
                osw.close()
                Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()

            } catch (ioe: IOException) {
                Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show()
            }

    }


    fun mostrarConductoresVehiculos(){
        val arregloConductor = Conductor(this).consultaConductorVehiculo()
        //listaConsultas.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)


        try {
            val tarjeta = getExternalFilesDir(null);
            val file = File(tarjeta?.getAbsolutePath(), "Conductor-Vehiculo.csv")
            val osw = OutputStreamWriter(FileOutputStream(file))
            osw.write(arregloConductor)

            osw.flush()
            osw.close()
            Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()

        } catch (ioe: IOException) {
            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show()
        }
    }



    fun mostrarConductoresVencidos(){
        val arregloConductor = Conductor(this).consulta1()
        listaConsultas.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)
    }
    fun mostrarConsulta2(){
        val arregloConductor = Conductor(this).consulta2()
        listaConsultas.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)
    }
    fun mostrarConsulta3(){
        val arregloConductor = Conductor(this).consulta3(txtConsulta3.text.toString())
        listaConsultas.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)
    }
    fun mostrarConsulta4Placa(){
        val arregloConductor = Conductor(this).consulta4(txtConsulta4.text.toString())
        listaConsultas.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)

    }
    fun mostrarConsultaNombre(){
        val arregloConductor = Conductor(this).consulta41(txtConsulta41.text.toString())
        listaConsultas.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)

    }
}