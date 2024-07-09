package ru.practicum.android.diploma.root.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding
import ru.practicum.android.diploma.db.data.db.AppDatabase

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btm_nav_view)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filterRegionFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }

                R.id.filterCountryFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }

                R.id.filterDepartmentFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }

                R.id.filterPlaceToWorkFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }

                R.id.filterPlaceToWorkFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }

                R.id.jobDetailsFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }

                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }
}
