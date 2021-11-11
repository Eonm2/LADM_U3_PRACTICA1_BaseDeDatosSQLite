package mx.tecnm.tepic.ladm_u3_practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.insertar
import kotlinx.android.synthetic.main.activity_main3.*
import mx.tecnm.tepic.ladm_u3_practica1_sqlite.Conductor
import mx.tecnm.tepic.ladm_u3_practica1_sqlite.Vehiculo

class MainActivity3 : AppCompatActivity() {
    var idVehiculo = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        mostrarVehiculosCapturados()

        insertar.setOnClickListener {
            val vehiculo = Vehiculo(this)

            vehiculo.placa = placa.text.toString()
            vehiculo.marca = marca.text.toString()
            vehiculo.modelo = modelo.text.toString()
            vehiculo.annio = annio.text.toString().toInt()
            vehiculo.idconductor = idconductor.text.toString().toInt()
            val resultado = vehiculo.insertar()//regresa true si se insertó, false si no se insertó
            if (resultado) {
                //positivo
                Toast.makeText(this, "ÉXITO, SE INSERTO", Toast.LENGTH_LONG).show()
                placa.text.clear()
                marca.text.clear()
                modelo.text.clear()
                annio.text.clear()
                idconductor.text.clear()
                mostrarVehiculosCapturados()
            } else {
                //negativo
                Toast.makeText(this, "ERROR NO SE INSERTO", Toast.LENGTH_LONG).show()
            }
        }
        nuevo.setOnClickListener {
            finish()
        }

    }
    fun mostrarVehiculosCapturados(){
        val arregloVehiculos = Vehiculo(this).consultar()
        listaVehiculos.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloVehiculos)
        idVehiculo.clear()
        idVehiculo = Vehiculo(this).obtenerIDs()
        activarEvento(listaVehiculos)
    }

    private fun activarEvento(listaVehiculo : ListView){
        listaVehiculo.setOnItemClickListener{ adapterView, view, indiceSeleccionado, l ->
            val idSeleccionado = idVehiculo[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿QUÉ DESEA HACER CON EL CONDUCTOR")
                .setPositiveButton("EDITAR"){d,i->actualizar(idSeleccionado)}
                .setNegativeButton("ELIMINAR"){d,i->eliminar(idSeleccionado)}
                .setNeutralButton("CANCELAR"){d,i-> d.cancel() }
                .show()
        }
    }

    private fun eliminar(idSeleccionado:Int){
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("¿ESTA SEGURO QUE DESEA ELIMINAR ESTE ID ${idSeleccionado}")
            .setPositiveButton("SI"){d,i->
                val resultado = Vehiculo(this).eliminar(idSeleccionado)
                if(resultado){
                    Toast.makeText(this,"SE ELIMINÓ CON ÉXITO", Toast.LENGTH_LONG).show()
                    mostrarVehiculosCapturados()
                }else{
                    Toast.makeText(this,"ERROR, NO SE LOGRÓ ELIMINAR", Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("NO"){d,i-> d.cancel() }
            .show()
    }

    private fun actualizar(idSeleccionado:Int){
        val intento = Intent(this,MainActivity6::class.java)
        //Toast.makeText(this,"${idSeleccionado}",Toast.LENGTH_LONG).show()
        intento.putExtra("idActualizarN",idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this).setMessage("¿DESEAS ACTUALIZAR LA LISTA?")
            .setPositiveButton("SI"){d,i->mostrarVehiculosCapturados()}
            .setNegativeButton("NO"){d,i-> d.cancel()}
            .show()
    }
}