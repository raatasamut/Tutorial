package com.appimake.simpletutorial.intf

interface ITutorialCallback  {
    fun updated(tag: String, code: Int, data: Any)
}