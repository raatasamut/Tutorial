package com.appimake.simpletutorial.models

import android.graphics.Color
import android.graphics.Typeface
import android.view.View

data class CardImageWithDescModel(
    val image: Any,
    val title: String,
    val subTitle: String,
    val desc: String
) : TutorialModel()

data class WeMoStyleModel(
    val headerView: View? = null,
    val headerText: String = "",
    val footerText: String = "",
    val img: Any?,
    val bottomBackground: Any,
    val custom: CustomWeMoStyleModel = CustomWeMoStyleModel()
) : TutorialModel()

data class CustomWeMoStyleModel(
    var fontSize: Float = 20f,
    var fontColor: Int = Color.WHITE,
    var fontTypeFace: Typeface? = null
)

data class SimpleModel(
    val img: Any,
    val title: String,
    val desc: String,
    val custom: CustomSimpleModel = CustomSimpleModel()
) : TutorialModel()

data class CustomSimpleModel(
    var fontSizeTitle: Float = 26f,
    var fontSizeDesc: Float = 20f,
    var fontTypeFaceForTitle: Typeface? = null,
    var fontTypeFaceForDesc: Typeface? = null
)

open class TutorialModel() {
    var tag: Any? = null
}