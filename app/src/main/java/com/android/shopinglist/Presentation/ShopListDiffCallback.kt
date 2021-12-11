package com.android.shopinglist.Presentation

import androidx.recyclerview.widget.DiffUtil
import com.android.shopinglist.Domain.ShopItem

class ShopListDiffCallback(
    private val oldList:List<ShopItem>,
    private val newList:List<ShopItem>
):DiffUtil.Callback() {

    override fun getOldListSize(): Int {
     return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {


       if (oldList[oldItemPosition].id==newList[newItemPosition].id){
           return true
       }else
           return false
       }



    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old=oldList[oldItemPosition]
        val new=newList[newItemPosition]
        return old == new

    }
}