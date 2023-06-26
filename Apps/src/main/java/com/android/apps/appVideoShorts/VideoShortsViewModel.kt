package com.android.apps.appVideoShorts

import com.android.commons.base.BaseViewModel

class VideoShortsViewModel : BaseViewModel() {
    fun initializeData(): MutableList<VideoShortItem> {
        val data = ArrayList<VideoShortItem>()

        val videoShort1 = VideoShortItem(
            videoURL = "https://rr4---sn-npoldne7.googlevideo.com/videoplayback?expire=1687704468&ei=NP-XZMSZGIf11gLl9ayYDA&ip=2a01:4f8:191:9063::2&id=o-AMpNTlsmRpo9iKguTEnlQgsIyH-n7zf2fJeRL27jeb0n&itag=22&source=youtube&requiressl=yes&sc=yes&siu=1&vprv=1&prv=1&mime=video/mp4&cnr=14&ratebypass=yes&dur=3657.142&lmt=1687531986884596&fexp=24007246,24350018,24362687,51000022&beids=24350018&txp=6218224&sparams=expire,ei,ip,id,itag,source,requiressl,siu,vprv,prv,mime,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRgIhANdftnCE93EfUcQzXQRGdiVoDP6Wz52mBKTbHTbQPCWjAiEAu4g2Mx-T6ia8jJHpcRdthKChcGaLvWVq-GzXxseaiQ0=&redirect_counter=1&rm=sn-4g5ed67e&req_id=680f05613d02a3ee&cms_redirect=yes&cmsv=e&ipbypass=yes&mh=bW&mip=2001:ee0:4161:b47a:2408:9a60:4dfb:20d5&mm=32&mn=sn-npoldne7&ms=su&mt=1687684118&mv=m&mvi=4&pl=53&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRgIhAPBtl-cX8_2M4OqYsJd1iOyP2mdDlq68NJExJBACvE-dAiEAqQmJbJC_GxqrrrqzEhGiOK0f98Y8NgISZFzLHo8Z22Q%3D",
            videoTitle = "Azumi Kirino",
            videoDescription = "Vợ cùng nhân tình đụ nhau ngay trước di ảnh của chồng"
        )
        data.add(videoShort1)

        val videoShort2 = VideoShortItem(
            videoURL = "https://rr2---sn-npoe7ne7.googlevideo.com/videoplayback?expire=1687457662&ei=HjuUZI_JBbjpx_APpp-RiAo&ip=2a01:4f8:191:9063::2&id=o-AByEKlBy8qN8w0XfPrNsHfgQ2QXWiPtRkS00K9BRHLIA&itag=22&source=youtube&requiressl=yes&sc=yes&siu=1&vprv=1&prv=1&mime=video/mp4&cnr=14&ratebypass=yes&dur=7066.528&lmt=1687008222078232&fexp=24007246,24350018,24362685,24362687,51000022&beids=24350018&txp=6218224&sparams=expire,ei,ip,id,itag,source,requiressl,siu,vprv,prv,mime,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRAIgZMUBTrv1ysBEhnDgc7WOLsMhCx-bY2lsRtYRrZHWNRoCIDasUYQlkgRGUxngkGssXULFNGueEmLdhHGVYijq6JPQ&redirect_counter=1&rm=sn-4g5err76&req_id=2a65528f115fa3ee&cms_redirect=yes&ipbypass=yes&mh=FO&mip=2001:ee0:4161:b47a:f9df:b9c8:63a1:f33b&mm=32&mn=sn-npoe7ne7&ms=su&mt=1687445098&mv=m&mvi=2&pl=53&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRgIhAP5LLf5Y5_39lqiSffU8WAz-fGWoKpRUhBnpGrjNK7GwAiEA4RJutaSy6B8KVye3LaadPLvBxD6OW1GXv7N9cBJ_Lso%3D",
            videoTitle = "Minami",
            videoDescription = "Cô chủ tiệm massage thiếu thốn tình dục và tên khách dâm số hưởng"
        )
        data.add(videoShort2)

        val videoShort3 = VideoShortItem(
            videoURL = "https://rr3---sn-npoldn76.googlevideo.com/videoplayback?expire=1687453518&ei=7iqUZPOmEYv8-ga6iY6wBg&ip=2a01:4f8:191:9063::2&id=o-ABTzwuYHogu6FAeO1VekGLz2BKxQHMqKgKpz_1XQ1W5X&itag=22&source=youtube&requiressl=yes&sc=yes&siu=1&vprv=1&prv=1&mime=video/mp4&cnr=14&ratebypass=yes&dur=8930.835&lmt=1686809332137502&fexp=24007246,24350018,24362687,51000011,51000022&beids=24350018&txp=6218224&sparams=expire,ei,ip,id,itag,source,requiressl,siu,vprv,prv,mime,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRQIhANvFvXbF7tB9OT2_PdqDecpy1vGZ1ojo0_6JWmaQ8tQHAiBQfTi6GZZ9-fuMvJKqW3xLXytVPa9y9APIxuVy87O4oA==&redirect_counter=1&rm=sn-4g5erd7z&req_id=674c68dcdf54a3ee&cms_redirect=yes&ipbypass=yes&mh=-s&mip=2001:ee0:4161:b47a:f9df:b9c8:63a1:f33b&mm=32&mn=sn-npoldn76&ms=su&mt=1687445098&mv=m&mvi=3&pl=53&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRQIhAKj10HESKBm6zcXvkDXMcrxarj45EXHwG4jaap9_G6tMAiAsT0RRTDFrxvStluWA-Go9Bb8mvC85syV7hUap069p8A%3D%3D",
            videoTitle = "Kana Morisawa",
            videoDescription = "Để vợ đi làm người mẫu và cái kết - Kana Morisawa"
        )
        data.add(videoShort3)


        val videoShort4 = VideoShortItem(
            videoURL = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
            videoTitle = "Song Advertisement",
            videoDescription = "This is a song advertisement"
        )
        data.add(videoShort4)

        val videoShort5 = VideoShortItem(
            videoURL = "https://rr1---sn-npoe7ney.googlevideo.com/videoplayback?expire=1687457443&ei=QzqUZO2mFp-g6dsPy_W4oAc&ip=2a01:4f8:191:9063::2&id=o-AOCucRURWSrMqG_P484_pqS-9kseifuLXgemTwZScdKg&itag=22&source=youtube&requiressl=yes&sc=yes&siu=1&vprv=1&prv=1&mime=video/mp4&cnr=14&ratebypass=yes&dur=1906.636&lmt=1641749194152842&fexp=24007246,24350017&beids=24350017&txp=6211224&sparams=expire,ei,ip,id,itag,source,requiressl,siu,vprv,prv,mime,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRQIgY4euKB0wULZrtElpMVzw903S7gNvvIwAapDgX2lpkYgCIQDvT6S9wP3zVPh5EH7uOBazcdDSa6gtDXhZVMNdg0o4Yg==&redirect_counter=1&rm=sn-4g5ed776&req_id=7e01f4944314a3ee&cms_redirect=yes&ipbypass=yes&mh=Ho&mip=2001:ee0:4161:b47a:d0d1:d884:23c2:1bd8&mm=32&mn=sn-npoe7ney&ms=su&mt=1687445817&mv=m&mvi=1&pl=53&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRQIhALKsfEMIeh89JnlzZ6B91ha7NOolPK3yY8PqqdFUHG_oAiAm7cz1K0yWl-IfBpMJno7Qtgdd8BxTOWJaAguiW5BJgA%3D%3D",
            videoTitle = "Aria Sky",
            videoDescription = "Rủ em Tây cực xinh vào khách sạn"
        )
        data.add(videoShort5)

        val videoShort6 = VideoShortItem(
            videoURL = "https://rr1---sn-npoeenlk.googlevideo.com/videoplayback?expire=1687458665&ei=CD-UZOmgO9mt1gKj5IDIAg&ip=2a01:4f8:191:9063::2&id=o-AK1JBZiAyjDNvEbuDkldFG6nUXCdgNGxGf6G2Iyofe0Z&itag=22&source=youtube&requiressl=yes&sc=yes&siu=1&vprv=1&prv=1&mime=video/mp4&cnr=14&ratebypass=yes&dur=3660.997&lmt=1635386250080738&fexp=24007246,24350018,24362688,51000023&beids=24350018&txp=6211224&sparams=expire,ei,ip,id,itag,source,requiressl,siu,vprv,prv,mime,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRQIhAOsEoWiztde8qXZzx77OCnfEG5sKOjkCA4kaDSrH6uwDAiA9Xdf4jB1mwtkbwYfz9IIlu1nPB5cqCpmjhkZXzlGBJQ==&redirect_counter=1&rm=sn-4g5e677s&req_id=4eff9d894935a3ee&cms_redirect=yes&ipbypass=yes&mh=Ry&mip=2001:ee0:4161:b47a:d0d1:d884:23c2:1bd8&mm=32&mn=sn-npoeenlk&ms=su&mt=1687446058&mv=m&mvi=1&pl=53&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRQIgPvvSqVKdMSIXB9u-V0L-GHJKfwksZMg033Akzkm4alsCIQDHB4-3ZWNC65MwbdTQYhgEE-1UL3_KeREnPRdmEQziiQ%3D%3D",
            videoTitle = "Yuna Sasaki",
            videoDescription = "Tranh thủ vợ đi làm, chồng ở nhà gạ tình cô giúp việc"
        )
        data.add(videoShort6)

        val videoShort7 = VideoShortItem(
            videoURL = "https://rr3---sn-npoeens7.googlevideo.com/videoplayback?expire=1687467767&ei=l2KUZOreJIOz1gLM1I_AAw&ip=2a01:4f8:191:9063::2&id=o-ANNY3p4GZdnl0Clkk5u1ClgilrOT7eaogEb9EkmqTGSN&itag=22&source=youtube&requiressl=yes&sc=yes&siu=1&vprv=1&prv=1&mime=video/mp4&cnr=14&ratebypass=yes&dur=7232.806&lmt=1678139790537503&fexp=24007246&txp=6218224&sparams=expire,ei,ip,id,itag,source,requiressl,siu,vprv,prv,mime,cnr,ratebypass,dur,lmt&sig=AOq0QJ8wRAIgPvFIhxOL2G0Z6ZMDyFlSl5EofzukwTVMnwe8-BstmzECICUEmAiIXi9NnD74wkmye1nP39AwcnOfyWGzmzVtdLvp&redirect_counter=1&rm=sn-4g5err7z&req_id=23543c5cd6eca3ee&cms_redirect=yes&ipbypass=yes&mh=PT&mip=2001:ee0:4161:b47a:d0d1:d884:23c2:1bd8&mm=32&mn=sn-npoeens7&ms=su&mt=1687446058&mv=m&mvi=3&pl=53&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRAIgNqTYrS4HixeMYIFzM1D7Y8hqxl_XTaPet2yAxVnF-aYCIGM9h5m8Egpz6n1Py_ZHOIHTcmObFAm1yAJ7TsjUQFeA",
            videoTitle = "Song Advertisement",
            videoDescription = "Thầy ơi! Đụ em có sướng không?"
        )
        data.add(videoShort7)


        return data
    }
}