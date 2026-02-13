package dev.abdelilah.viewpagerwithfragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding


import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.abdelilah.viewpagerwithfragments.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            tabLayout.updatePadding(top = systemBars.top)
            viewPager.updatePadding(bottom = systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }



        // Initialize ViewPager2
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Initialize the ViewPagerAdapter
        viewPagerAdapter = ViewPagerAdapter(this)

        // Set the adapter to the ViewPager2
        viewPager.adapter = viewPagerAdapter



        tabLayout.setBackgroundColor(Color.TRANSPARENT)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Fragment 1"
                1 -> "Fragment 2"
                2 -> "Fragment 3"
                3 -> "Fragment 4"
                4 -> "Fragment 5"
                else -> ""
            }
        }.attach()



        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Change the TabLayout background color based on the selected fragment
                when (position) {
                    0 -> {
                        // Set background color based on the first fragment
                        tabLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.Fragment1_background))
                    }
                    1 -> {
                        // Set background color based on the second fragment
                        tabLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.Fragment2_background))
                    }
                    2 -> {
                        // Set background color based on the third fragment
                        tabLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.Fragment3_background))
                    }
                    3 -> {
                        // Set background color based on the third fragment
                        tabLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.Fragment4_background))
                    }
                    4 -> {
                        // Set background color based on the third fragment
                        tabLayout.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.Fragment5_background))
                    }
                }
            }
        })
    }
}






//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        // Set up other UI components and functionality here
//
//
//    }
//}