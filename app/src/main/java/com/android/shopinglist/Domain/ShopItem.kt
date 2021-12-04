package com.android.shopinglist.Domain

data class ShopItem(

    val name:String,
    val count:Int,
    val enabled:Boolean,
    var id:Int= UNDEFINDEND_ID
){
    companion object{
        const val UNDEFINDEND_ID=-1
    }

}
