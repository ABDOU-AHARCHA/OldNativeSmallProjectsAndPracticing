package dev.abdelilah.normalviewpager2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Initialize the ViewPager2
        viewPager = findViewById(R.id.viewPgae21)

        // List of image resource IDs (replace with your actual image resources)
        val images = listOf(
            R.drawable.cair_2,
            R.drawable.chair_1,
            R.drawable.chair_3,
            R.drawable.fluuter_icon_to_the__branch_4_1,
            R.drawable.learning_icon,
            R.drawable.mobile_icon,
            R.drawable.notification_flat,
            R.drawable.notification_icon,
            R.drawable.android_icon,
            R.drawable.android_icon,
            R.drawable.chair_3,
        )

        // Set up the ViewPagerAdapter
        adapter = ViewPagerAdapter(images)

        // Attach the adapter to the ViewPager2
        viewPager.adapter = adapter

        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL




    }
}