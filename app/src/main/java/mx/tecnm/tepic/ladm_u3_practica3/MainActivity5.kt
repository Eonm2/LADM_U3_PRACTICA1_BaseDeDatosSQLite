package mx.tecnm.tepic.ladm_u3_practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main5.*
import mx.tecnm.tepic.ladm_u3_practica1_sqlite.Conductor

class MainActivity5 : AppCompatActivity() {
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        var extra = intent.extras
        id = extra!!.getString("idActualizar")!!
        val conductor = Conductor(this).consultar(id)

        nombre.setText(conductor.nombre)
        domicilio.setText(conductor.domicilio)
        nolicencia.setText(conductor.nolicencia)
        vence.setText(conductor.vence)

        button.setOnClickListener {
            val conductorActualizar = Conductor(this)
            conductorActualizar.nombre = nombre.text.toString()
            conductorActualizar.domicilio = domicilio.text.toString()
            conductorActualizar.nolicencia = nolicencia.text.toString()
            conductorActualizar.vence = vence.text.toString()
            val resulado = conductorActualizar.actualizar(id)
            if(resulado){
                Toast.makeText(this,"ÉXITO, SE ACTUALIZÓ", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"ERROR, NO SE ACTUALIZAR", Toast.LENGTH_LONG).show()
            }
        }

        button2.setOnClickListener {
            finish()
        }
    }
}