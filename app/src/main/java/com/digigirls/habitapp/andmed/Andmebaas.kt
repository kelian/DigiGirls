package com.digigirls.habitapp.andmed

import android.content.Context
import android.content.SharedPreferences
import com.digigirls.habitapp.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Andmebaas(context: Context) {

    private var andmebaas: SharedPreferences = context.getSharedPreferences("Andmebaas", Context.MODE_PRIVATE)
    private var gson = Gson()

    private val HARJUMUSED_VÕTI = "VÕTI_HARJUMUSED"

    fun getHarjumused(): ArrayList<Harjumus> {
        return if (andmebaas.contains(HARJUMUSED_VÕTI)) {
            val harjumusedJSON = andmebaas.getString(HARJUMUSED_VÕTI, "")

            gson.fromJson(harjumusedJSON, object : TypeToken<ArrayList<Harjumus>>() {}.type)
        } else {
            ArrayList<Harjumus>()
        }
    }

    fun salvestaHarjumus(nimi: String) {
        val harjumus = Harjumus(Utils.getStringId(), nimi, 0, 0)
        val harjumused = getHarjumused()
        harjumused.add(harjumus)
        salvestaHarjumused(harjumused)
    }

    fun suurendaHeadTulemust(harjumusId: String) {
        val harjumused = getHarjumused()
        for (harjumus in harjumused) {
            if (harjumus.id == harjumusId) {
                val id = harjumused.indexOf(harjumus)
                harjumused[id].heaTulemus = harjumus.heaTulemus + 1
                salvestaHarjumused(harjumused)
                break
            }
        }
    }

    fun suurendaHalbaTulemust(harjumusId: String) {
        val harjumused = getHarjumused()
        for (harjumus in harjumused) {
            if (harjumus.id == harjumusId) {
                val id = harjumused.indexOf(harjumus)
                harjumused[id].halbTulemus = harjumus.halbTulemus + 1
                salvestaHarjumused(harjumused)
                break
            }
        }
    }

    fun kustutaHarjumus(harjumusId: String) {
        val harjumused = getHarjumused()
        for (harjumus in harjumused) {
            if (harjumus.id == harjumusId) {
                harjumused.remove(harjumus)
                salvestaHarjumused(harjumused)
                break
            }
        }
    }

    private fun salvestaHarjumused(harjumused: ArrayList<Harjumus>) {
        andmebaas.edit().putString(HARJUMUSED_VÕTI, gson.toJson(harjumused)).apply()
    }

}
