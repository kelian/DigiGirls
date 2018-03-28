package com.digigirls.habitapp.vaated

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.digigirls.habitapp.*
import com.digigirls.habitapp.andmed.Andmebaas
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HarjumusAdapterCallback {

    private lateinit var vaateAdapter: HarjumusAdapter
    private lateinit var vaateHaldur: RecyclerView.LayoutManager

    private lateinit var andmebaas: Andmebaas

    private val LISA_VAATE_PÄRING = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        andmebaas = Andmebaas(this)
        vaateHaldur = LinearLayoutManager(this)
        vaateAdapter = HarjumusAdapter(this, andmebaas.getHarjumused(), this)

        list.apply {
            layoutManager = vaateHaldur
            adapter = vaateAdapter
        }

        // 1. Kutsu meetod, et lisa nupu vajutusel avaneks uus vaade
        lisaNupp.setOnClickListener{ /* meetodi nimi */ }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> false
        }
    }

    override fun onActivityResult(päringuKood: Int, resultCode: Int, data: Intent?) {
        if (päringuKood == LISA_VAATE_PÄRING) {
            vaateAdapter.setUuedHarjumused(andmebaas.getHarjumused())
        }
    }

    private fun lisaNupuKlikk() {
        startActivityForResult(Intent(this, AddActivity::class.java), LISA_VAATE_PÄRING)
    }

    override fun heaTulemuseKlikk(id: String) {
        andmebaas.suurendaHeadTulemust(id)
    }

    override fun halvaTulemuseKlikk(id: String) {
        andmebaas.suurendaHalbaTulemust(id)
    }

    override fun kustutaHarjumus(id: String) {
        andmebaas.kustutaHarjumus(id)
    }
}
