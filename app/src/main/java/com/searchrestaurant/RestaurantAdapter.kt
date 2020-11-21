package com.searchrestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.searchrestaurant.common.GlideApp
import com.searchrestaurant.models.Restaurant
import kotlinx.android.synthetic.main.msearch_item.view.*

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.SHolder>() {
    private val mData = ArrayList<Restaurant>()

    class SHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SHolder {
        return SHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.msearch_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SHolder, position: Int) {
        try {
            val restaurant = mData.get(position)
            GlideApp.with(holder.itemView.icon).load(restaurant.icon)
                .placeholder(R.drawable.place_holder).error(R.drawable.place_holder)
                .centerCrop()
                .transform(RoundedCorners(holder.itemView.resources.getDimensionPixelSize(R.dimen._10sdp)))
                .into(holder.itemView.icon)
            holder.itemView.name.setText(restaurant.name)
            holder.itemView.address.setText(restaurant.vicinity)
            holder.itemView.rating.setText(restaurant.rating)
            holder.itemView.types.setText(
                restaurant.types.toString().replace("[", "").replace("]", "")
            )
            holder.itemView.open.setText(if (restaurant.opening_hours != null && restaurant.opening_hours.open_now) "Open" else "Closed")
        } catch (ignore: Exception) {
        }
    }

    fun updateValues(values: ArrayList<Restaurant>) {
        mData.clear()
        mData.addAll(values)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}