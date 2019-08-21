package com.appimake.tutorial

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.appcompat.app.AppCompatActivity
import android.text.Layout
import android.widget.*
import com.appimake.simpletutorial.Tutorial
import com.appimake.simpletutorial.models.*
import com.appimake.simpletutorial.util.dpToPx
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show.setOnClickListener {
            show()
        }
        show()
    }

    private fun show(){

        val iv = ImageView(this)
        iv.setImageDrawable(resources.getDrawable(R.drawable.bn_logo, null))
        val pram = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(72f).toInt())
        pram.setMargins(0, dpToPx(16f).toInt(), 0, 0)
        iv.layoutParams = pram

        val btClose = Button(this)
        btClose.text = "CLOSE"

        Tutorial.addPageList(ArrayList<TutorialModel>().apply {
            add(
                CardImageWithDescModel(
                    "https://static1.squarespace.com/static/59bc2760ccc5c50c96e69ac8/t/5a99ab6953450a7571906336/1520020362808/KWCF+Belonging+Report+2014.png",
                    "Title",
                    "Sub",
                    "DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC DESC"
                )
            )
            add(
                WeMoStyleModel(
                    headerView = iv,
                    footerText = "Just a few tap that you can manage by yourself easily.",
                    img = ResourcesCompat.getDrawable(resources, R.drawable.t1, null),
                    bottomBackground = Color.parseColor("#2f425d"),
                    custom = CustomWeMoStyleModel().apply {
                        fontColor = Color.WHITE
                        fontSize = 24f
                        fontTypeFace = ResourcesCompat.getFont(this@MainActivity, R.font.lamoon_bold)
                    }
                )
            )
            add(
                SimpleModel(
                    img = resources.getDrawable(R.drawable.t1, null),
                    title = "Simple Style",
                    desc = "DESC DESC DESC DESC",
                    custom = CustomSimpleModel().apply {
                        fontSizeTitle = 26f
                        fontSizeDesc = 20f
                        fontTypeFaceForTitle = ResourcesCompat.getFont(this@MainActivity, R.font.lamoon_bold)
                        fontTypeFaceForDesc = ResourcesCompat.getFont(this@MainActivity, R.font.lamoon)
                    }
                )
            )
            add(
                WeMoStyleModel(
                    headerText = "We are ready to respond skillfully and professionally to any and all customer.",
                    footerText = "Just a few tap that you can manage by yourself easily.",
                    img = ResourcesCompat.getDrawable(resources, R.drawable.t1, null),
                    bottomBackground = Color.parseColor("#2f425d"),
                    custom = CustomWeMoStyleModel().apply {
                        fontColor = Color.WHITE
                        fontSize = 24f
                        fontTypeFace = ResourcesCompat.getFont(this@MainActivity, R.font.lamoon_bold)
                    }
                )
            )
        }).custom(
            fontSize = 24f,
            fontColor = Color.parseColor("#b9bcbf"),
            fontTypeFace = ResourcesCompat.getFont(this, R.font.lamoon_bold),
            indicatorSelectHex = "#ffffff",
            indicatorDeselectHex = "#ffb8c9",
            background = ResourcesCompat.getDrawable(resources, R.drawable.bg_home, null), // Color | Drawable
//            closeText = "START",
            closeView = btClose,
            skipText = "SKIP",
            barTitle = "TITLE",
            barHexColor = "#00FFAA"
        ).show(this)
    }
}
