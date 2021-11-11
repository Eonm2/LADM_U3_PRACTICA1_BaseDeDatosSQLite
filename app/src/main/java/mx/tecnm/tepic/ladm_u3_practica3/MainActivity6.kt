package mx.tecnm.tepic.ladm_u3_practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main6.*
import mx.tecnm.tepic.ladm_u3_practica1_sqlite.Vehiculo

class MainActivity6 : AppCompatActivity() {
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        var extra = intent.extras
        id = extra!!.getString("idActualizarN")!!
        Toast.makeText(this,"${id}", Toast.LENGTH_LONG).show()
        val vehiculo = Vehiculo(this).consultar(id)

         actualizarPlaca.setText(vehiculo.placa)
         actualizarMarca.setText(vehiculo.marca)
         actualizarModelo.setText(vehiculo.modelo)
         actualizarAnnio.setText(vehiculo.annio.toString())
         actualizarID.setText(vehiculo.idconductor.toString())

        button3.setOnClickListener {
            val vehiculoActualizar = Vehiculo(this)
            vehiculoActualizar.placa = actualizarPlaca.text.toString()
            vehiculoActualizar.marca = actualizarMarca.text.toString()
            vehiculoActualizar.modelo = actualizarModelo.text.toString()
            vehiculoActualizar.annio = actualizarAnnio.text.toString().toInt()
            vehiculoActualizar.idconductor = actualizarID.text.toString().toInt()
            val resulado = vehiculoActualizar.actualizar(id)
            if(resulado){
                Toast.makeText(this,"ÉXITO, SE ACTUALIZÓ", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"ERROR, NO SE ACTUALIZAR", Toast.LENGTH_LONG).show()
            }
        }

        button4.setOnClickListener {
            finish()
        }
    }
}