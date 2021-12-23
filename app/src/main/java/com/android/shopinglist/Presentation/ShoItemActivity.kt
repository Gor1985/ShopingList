package com.android.shopinglist.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.identity.AccessControlProfileId
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.shopinglist.Domain.ShopItem
import com.android.shopinglist.R
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class ShoItemActivity : AppCompatActivity() {
    private lateinit var viewModal: ShopItemViewModal
    private lateinit var titName:TextInputLayout
    private lateinit var titCount:TextInputLayout
    private lateinit var etName:EditText
    private lateinit var etCount:EditText
    private lateinit var buttonSave:Button
    private var screenMode=MODE_UKNOWN
    private var shopItemId=ShopItem.UNDEFINDEND_ID



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sho_item)
       parseIntent()
        viewModal = ViewModelProvider(this)[ShopItemViewModal::class.java]

        InitViews()
      addText()
        lounchRightMode()
        observViewModal()

    }
    private fun lounchRightMode(){
        when(screenMode){
            MODE_EDIT->launchEditMode()
            MODE_ADD->launchAddMode()
        }
    }
private fun observViewModal(){
    viewModal.errorInputCount.observe(this){
        val mesage=   if (it){
            "Пустое поле, заполните"
        }else{
            null
        }
        titCount.error=mesage
    }
    viewModal.errorInputName.observe(this){
        val mesage=   if (it){
            "Пустое поле, заполните"
        }else{
            null
        }
        titName.error=mesage
    }
}
    private fun addText(){
        etName.addTextChangedListener ( object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModal.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {

            }


        } )
        etCount.addTextChangedListener ( object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModal.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }


        } )
    }
     private fun launchEditMode(){
viewModal.getShopItem(shopItemId)
         viewModal.shopItem.observe(this){
             etName.setText(it.name)
             etCount.setText(it.count.toString())
         }


         buttonSave.text="Save"
buttonSave.setOnClickListener{
    viewModal.editShopItem(etName.text?.toString(), etCount.text?.toString())

}

         viewModal.shouldCloseScreen.observe(this){
             finish()
         }
}
    private fun launchAddMode(){
        buttonSave.text="Add"

        buttonSave.setOnClickListener{
            viewModal.addShopItem(etName.text?.toString(), etCount.text?.toString())


        }
}

     private fun parseIntent() {
         if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
             throw RuntimeException("Param screen mode is absent")
         }
         val mode=intent.getStringExtra(EXTRA_SCREEN_MODE)

         if (mode!== MODE_EDIT && mode!= MODE_ADD){
             throw RuntimeException("Uknown screen mode $mode")
         }
         screenMode=mode
         if (screenMode== MODE_EDIT) {
             if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                 throw RuntimeException("Param shop item id is absent")
             }
             shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID,ShopItem.UNDEFINDEND_ID )

         }
     }
    private fun InitViews(){
        titName=findViewById(R.id.til_name)
        titCount=findViewById(R.id.til_count)
        etName=findViewById(R.id.et_name)
        etCount=findViewById(R.id.et_count)
        buttonSave=findViewById(R.id.save_button)
    }
    companion object{
      private  const val EXTRA_SCREEN_MODE="extra_mode"
      private  const val EXTRA_SHOP_ITEM_ID="extra_shop_item_id"
         private   const val MODE_EDIT="mode_edit"
          private  const val MODE_ADD="mode_add"
          private  const val MODE_UKNOWN=""

        fun newIntentAddItem(context: Context): Intent {
       val intent=Intent(context,ShoItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE,MODE_ADD)
            return intent
        }
        fun newIntentEditItem(context: Context,shopItemId: Int):Intent{
            val intent=Intent(context,ShoItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE,MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID,shopItemId)

            return intent
        }
    }
}