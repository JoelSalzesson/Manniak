package com.example.manniak

import pl.droidsonroids.jspoon.annotation.Selector

internal class Page {
    @Selector("#title")
    var title: String? = null
    @Selector("li.a")
    var intList: List<Int>? = null
    @Selector(value = "#image1", attr = "src")
    var imageSource: String? = null
    @Selector(".list-files > script", defValue = "NO_TEXTTTT")
    var l3: String? = null
    @Selector(".list-files", defValue = "NO_TEXTTTT")
    var l4: String? = null
    @Selector(value = "#list-files > script")
    var l5: String? = null
}