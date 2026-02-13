package dev.abdelilah.normalviewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import dev.abdelilah.normalviewpager2.R

// ViewPagerAdapter to display images using RecyclerView.Adapter
class ViewPagerAdapter(
    private val images: List<Int>  // List of image resource IDs
) : RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    // ViewHolder class for the ImageView
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    // Inflates the image layout for each item in the ViewPager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_pager, parent, false)
        return ImageViewHolder(view)
    }

    // Binds the image to the ImageView in the ViewHolder
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    // Returns the total number of images
    override fun getItemCount(): Int {
        return images.size
    }
}
