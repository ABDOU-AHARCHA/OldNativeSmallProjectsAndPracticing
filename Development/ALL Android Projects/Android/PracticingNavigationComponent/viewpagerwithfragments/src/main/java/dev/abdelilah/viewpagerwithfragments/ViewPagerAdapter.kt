package dev.abdelilah.viewpagerwithfragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Return the fragment associated with the position
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> Fragment3()
            3 -> Fragment4()
            4 -> Fragment5()
            else -> FirstFragment() // Default to FirstFragment if out of bounds
        }
    }

    // Return the total number of fragments (pages)
    override fun getItemCount(): Int {
        return 5 // Since we have 2 fragments
    }
}