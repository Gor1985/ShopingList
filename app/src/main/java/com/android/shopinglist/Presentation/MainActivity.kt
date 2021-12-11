package com.android.shopinglist.Presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.shopinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModal: MainViewModal

    // private lateinit var lllinearLayout: LinearLayout
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        viewModal = ViewModelProvider(this)[MainViewModal::class.java]
        viewModal.shoplist.observe(this) {
            shopListAdapter.submitList(it)
            // showList(it)
        }

    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        rvShopList.adapter = shopListAdapter
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEWTYPE_ENABLED,
            ShopListAdapter.MAXPOOLSIZE
        )
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEWTYPE_DESABLED,
            ShopListAdapter.MAXPOOLSIZE
        )


        /* private fun showList(List: List<ShopItem>) {
        for (shopItem in List) {
            val layotId = if (shopItem.enabled) {
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
        }
        fun showList(list: List<ShopItem>) {
            lllinearLayout.removeAllViews()
            for (shopItem in list) {
                val layoutId = if (shopItem.enabled) {
                    R.layout.item_shop_enabled
                } else {
                    R.layout.item_shop_disabled
                }
                val view = LayoutInflater.from(this).inflate(layoutId, lllinearLayout, false)
                val tvName = view.findViewById<TextView>(R.id.tv_name)
                val tvCount = view.findViewById<TextView>(R.id.tv_count)
                tvName.text = shopItem.name
                tvCount.text = shopItem.count.toString()
                view.setOnLongClickListener {
                    viewModal.changeEnable(shopItem)
                    true
                }
                lllinearLayout.addView(view)
            */
        setupLongListener()
        setupClickListener()

        setupSwipeListener(rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModal.deleteShopList(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
        shopListAdapter.intentCkickk = {


        }
    }

    private fun setupLongListener() {
        shopListAdapter.onShopItemLongClickListenerr = {

            viewModal.changeEnable(it)
        }
    }

}










