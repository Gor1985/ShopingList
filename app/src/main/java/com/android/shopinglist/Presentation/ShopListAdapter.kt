package com.android.shopinglist.Presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.shopinglist.Domain.ShopItem
import com.android.shopinglist.R
import java.lang.RuntimeException

class ShopListAdapter:RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var shopList = listOf<ShopItem>()

            set(value) {
        field = value
        notifyDataSetChanged()

    }

var onShopItemLongClickListenerr:((ShopItem)->Unit)?=null
 var intentCkickk:((ShopItem)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layot = when (viewType) {

            VIEWTYPE_ENABLED ->R.layout.item_shop_enabled
            VIEWTYPE_DESABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("UknownViewType $viewType")

        }


      val view=LayoutInflater.from(parent.context).inflate(layot,parent,false)
        return ShopItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
    val shopItem=shopList[position]


        holder.view.setOnLongClickListener {
            onShopItemLongClickListenerr?.invoke(shopItem)
           true
        }
        holder.view.setOnClickListener {
            intentCkickk?.invoke(shopItem)
        }
        holder.tvName.text=  shopItem.name
        holder.tvCount.text=shopItem.count.toString()


    }

    override fun getItemCount(): Int {
        return shopList.size
    }




    override fun getItemViewType(position: Int): Int {
        val item=shopList[position]
       return if (item.enabled){
VIEWTYPE_ENABLED
        }else
VIEWTYPE_DESABLED
    }

    class  ShopItemViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val tvName =view.findViewById<TextView>(R.id.tv_name)
        val tvCount=view.findViewById<TextView>(R.id.tv_count)
    }
    interface onShopItemLongClickListener{
        fun onShopItemLongClick(shopItem: ShopItem)
    }
    interface intentCkick{
        fun intentClick(shopItem: ShopItem)
    }
companion object{
    const val VIEWTYPE_ENABLED=100
    const val VIEWTYPE_DESABLED=101
    const val MAXPOOLSIZE=15
}

}
