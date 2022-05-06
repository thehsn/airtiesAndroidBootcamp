package com.example.sampleecommerceapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleecommerceapp.databinding.CardDesignBinding

class ProductsAdapter(var mContext:Context, var productsList:List<Products>)
    : RecyclerView.Adapter<ProductsAdapter.CardDesignTutucu>() {

    inner class CardDesignTutucu(tasarim:CardDesignBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardDesignBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardDesignBinding.inflate(layoutInflater,parent,false)
        return CardDesignTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardDesignTutucu, position: Int) {
        val product = productsList.get(position)
        val t = holder.tasarim

        t.imageViewProductImage.setImageResource(
            mContext.resources.getIdentifier(product.imageName, "drawable", mContext.packageName)
        )
        t.textViewProductCode.text = product.productCode
        t.textViewProductName.text = product.procuctName
        t.textViewProductPrice.text = product.productPrice.toString()
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}