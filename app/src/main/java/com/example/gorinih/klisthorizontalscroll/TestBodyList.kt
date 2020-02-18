package com.example.gorinih.klisthorizontalscroll


enum class TestBodyList(open var id : Int, open var item : String) {
    DB0(0,"белое"),
    DB1(0,"красное"),
    DB2(0,"розовое"),
    DB3(0,"сухое"),
    DB4(0,"полусухое"),
    DB5(1,"светлое"),
    DB6(1,"темное"),
    DB7(1,"лагер"),
    DB8(1,"ламбик"),
    DB9(1,"эль"),
    DB10(1,"стаут"),
    DB11(1,"трипель"),
    DB12(2,"черный"),
    DB13(2,"зеленый"),
    DB14(2,"белый"),
    DB15(2,"цветочный"),
    DB16(2,"пуэр"),
    DB17(2,"красный"),
    DB18(3,"арабика"),
    DB19(3,"лате"),
    DB20(3,"капучино"),
    DB21(3,"эспрессо"),
    DB22(3,"фраппе"),
    DB23(4,"газированная"),
    DB24(4,"минеральная"),
    DB25(4,"дистиллированная"),
    DB26(4,"родниковая"),
    DB27(4, "артезианская");

companion object {
   fun get(pos : Int) : Int{
       for (i in values())
            if (i.id == pos) return  i.ordinal
       return 0
    }
}
 }