package com.digigirls.habitapp.vaated

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.digigirls.habitapp.R
import com.digigirls.habitapp.andmed.Andmebaas
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var andmebaas: Andmebaas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add)
        andmebaas = Andmebaas(this)

        /*
        * 1. Loo uus tühi meetod samasse klassi
        * 2. Kutsu meetod välja
        * 3. Mine res -> layout -> activity_add.xml ja leia EditTexti id
        * 4. Prindi tekst konsooli -> println(*tekst*)
        * 5. Salvesta andmebaasi harjumus
        * 6. Lõpeta vaade
        * */
        valmis.setOnClickListener { /* meetodi nimi */ }
    }

}
