package ru.practicum.android.diploma.root.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ActivityRootBinding
import ru.practicum.android.diploma.network.HeadHunterRepository
import ru.practicum.android.diploma.network.api.Locale
import ru.practicum.android.diploma.utils.Resource

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.navigation_graph)
        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.HH_ACCESS_TOKEN)

        //NetworkArea+++++
        val repo = HeadHunterRepository()
        lifecycleScope.launch {
            repo.getLocales()
                .collect { result ->
                    if (result is Resource.Success) {
                        (result.data as List<Locale>)
                            .forEach {
                                Log.d("HHTOKEN", it.name.toString())
                            }
                    }
                }
        }


        //NetworkArea-----
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
