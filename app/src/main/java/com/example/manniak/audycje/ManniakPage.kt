package com.example.manniak.audycje

import pl.droidsonroids.jspoon.annotation.Selector

class ManniakPage {
    @Selector(".articles-count-12")
    var posts: List<ManniakAudycja>? = null
}

class ManniakAudycja {
    @Selector(".article > a", attr = "href")
    var url: String? = null
    @Selector(value = ".article > a", attr = "title")
    var title: String? = null
}

//html body#ctl00_ctl00_ctl00_ContentPlaceHolder1_bodyCtrl.id4757.trojka.polskieradio div#ajax-wrap-body form#aspnetForm div#ajax-wrap-page div.container.ArticleMaster div.row div.col-md-8.twothirds.art-body.Section00 div.zaiks aside#box-sounds.clearfix.list-files
//html body#ctl00_ctl00_ctl00_ContentPlaceHolder1_bodyCtrl.id4757.trojka.polskieradio div#ajax-wrap-body form#aspnetForm div#ajax-wrap-page div.container.ArticleMaster div.row div.col-md-8.twothirds.art-body.Section00 div.zaiks aside#box-sounds.clearfix.list-files div#player-main-audycja_2422547.player-main-audycja.active div#jwplayer-wrapper.jwplayer.jw-reset.jw-state-paused.jw-skin-seven.jw-stretch-uniform.jw-flag-controls-hidden.jw-flag-media-audio
class ManniakStreamMp3 {
    @Selector(".player-main-audycja > *")
    var l1: String? = null
    @Selector(".player-main-audycja > jwplayer > jwmedia > jwvideo", attr = "src")
    var l2: String? = null
    @Selector(".list-files > script", defValue = "NO_TEXTTTT")
    var l3: String? = null
    @Selector(value = ".clearfix list-files > script")
    var mp3Url: String? = null
}
