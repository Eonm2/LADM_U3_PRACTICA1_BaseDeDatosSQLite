package mx.tecnm.tepic.ladm_u3_practica3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conductor.setOnClickListener {
            val intento = Intent(this,MainActivity2::class.java)
            startActivity(intento)
        }

        vehiculo.setOnClickListener {
            val intento = Intent(this,MainActivity3::class.java)
            startActivity(intento)
        }

        consultas.setOnClickListener {
            val intento = Intent(this,MainActivity4::class.java)
            startActivity(intento)
        }
    }
}