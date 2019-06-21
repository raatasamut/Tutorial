package com.appimake.simpletutorial

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import com.appimake.simpletutorial.models.TutorialModel

@SuppressLint("StaticFieldLeak")
object Tutorial {

    var pageList: ArrayList<TutorialModel> = ArrayList()

    var fontTypeFace: Typeface? = null
    var fontSize: Float = 18f
    var fontColor: Int = Color.WHITE

    var barTitle: String? = null
    var barHexColor: String? = null

    var background: Any? = null
    var closeText: String? = null
    var closeView: View? = null
    var skipText: String? = null

    var indicatorDeselectHex: String = ""
    var indicatorSelectHex: String = ""

    init {
        pageList = ArrayList()
    }

    fun addPage(page: TutorialModel): Tutorial {
        this.pageList.add(page)
        return this
    }

    fun addPageList(pageList: ArrayList<TutorialModel>): Tutorial {
        this.pageList.clear()
        this.pageList.addAll(pageList)
        return this
    }

    fun custom(
        fontTypeFace: Typeface? = null,
        fontSize: Float = 18f,
        fontColor: Int = Color.WHITE,
        indicatorDeselectHex: String = "#ecf1f3",
        indicatorSelectHex: String = "#86d8f7",
        background: Any? = null,
        closeText: String? = null,
        closeView: View? = null,
        skipText: String? = null,
        barHexColor: String? = null,
        barTitle: String? = null
    ): Tutorial {
        this.fontTypeFace = fontTypeFace
        this.fontSize = fontSize
        this.fontColor = fontColor
        this.indicatorDeselectHex = indicatorDeselectHex
        this.indicatorSelectHex = indicatorSelectHex
        this.background = background
        this.closeText = closeText
        this.closeView = closeView
        this.skipText = skipText
        this.barHexColor = barHexColor
        this.barTitle = barTitle
        return this
    }

    fun show(context: Context) {
        val intent = Intent(context, SimpleTutorial::class.java)
        context.startActivity(intent)
    }

}