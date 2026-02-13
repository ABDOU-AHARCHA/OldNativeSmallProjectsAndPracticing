package dev.abdelilah.practicingnavigationcomponent

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {



    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var navController: NavController
    lateinit var appbarConfiguration: AppBarConfiguration
    lateinit var navHostFragment: NavHostFragment
    lateinit var listener: NavController.OnDestinationChangedListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        enableEdgeToEdge()






        navigationView = findViewById(R.id.navigationview2)

        // Set up the Toolbar as the ActionBar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
//        var hostfragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView4)



//        navController = findNavController(R.id.fragmentContainerView4)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView4) as NavHostFragment
//        navController = findNavController()
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView4) as NavHostFragment
        navController = navHostFragment.navController
//        navController = findNavController(R.id.fragmentContainerView4)
        drawerLayout = findViewById(R.id.main)
        navigationView.setupWithNavController(navController)
        appbarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appbarConfiguration)


        listener = NavController.OnDestinationChangedListener{ controller, destination, arguments ->
            if (destination.id == R.id.firstFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.ColorFragment1)))
            } else if (destination.id == R.id.secondFragment) {
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.ColorFragment2)))
            }
        }

//        navController.addOnDestinationChangedListener(listener)




    }


    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
//        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show()

    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
//        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appbarConfiguration) || super.onSupportNavigateUp()
    }
}