package com.digigirls.habitapp.vaated

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import com.digigirls.habitapp.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            tekst.text = Html.fromHtml("<div>Icons made by <a href=\"http://www.freepik.com\" " +
                    "title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" " +
                    "title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=" +
                    "\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" " +
                    "target=\"_blank\">CC 3.0 BY</a></div>", Html.FROM_HTML_MODE_COMPACT)
        else
            tekst.text = Html.fromHtml("<div>Icons made by <a href=\"http://www.freepik.com\" " +
                    "title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" " +
                    "title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=" +
                    "\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" " +
                    "target=\"_blank\">CC 3.0 BY</a></div>")
    }
}
