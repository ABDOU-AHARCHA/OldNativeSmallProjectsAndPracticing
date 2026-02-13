package dev.abdelilah.bottomnavigationviewwithnavigationcomponent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {



    lateinit var listner : NavController.OnDestinationChangedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        var navController = navHostFragment.navController

        var navigationview = findViewById<BottomNavigationView>(R.id.bottonnavigationview1)
        navigationview.setupWithNavController(navController)



        listner = NavController.OnDestinationChangedListener{ controller, destination, arguments ->
            if (destination.id == R.id.homeFragment) {
                navigationview?.background = ColorDrawable(getColor(R.color.ColorHome1))
            } else if (destination.id == R.id.profileFragment) {
                navigationview?.background = ColorDrawable(getColor(R.color.ColorProfile2))
            }else if (destination.id == R.id.settingFragment) {
                navigationview?.background = ColorDrawable(getColor(R.color.ColorSetting3))

            }
        }


        navController.addOnDestinationChangedListener(listner)






    }
}