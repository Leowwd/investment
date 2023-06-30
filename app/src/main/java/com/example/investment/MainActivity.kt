package com.example.investment

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.investment.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.Manifest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var isPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                permission -> isPermissionGranted = permission ?: isPermissionGranted
        }
        requestPermission()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_item1 -> if (navController.currentDestination?.id == R.id.secondFragment) {
                    navController.navigate(R.id.action_secondFragment_to_firstFragment)
                } else if (navController.currentDestination?.id == R.id.thirdFragment) {
                    navController.navigate(R.id.action_thirdFragment_to_firstFragment)
                }
                R.id.nav_item2 -> if (navController.currentDestination?.id == R.id.firstFragment) {
                    navController.navigate(R.id.action_firstFragment_to_secondFragment)
                } else if (navController.currentDestination?.id == R.id.thirdFragment) {
                    navController.navigate(R.id.action_thirdFragment_to_secondFragment)
                }
                R.id.nav_item3 -> if (navController.currentDestination?.id == R.id.firstFragment) {
                    navController.navigate(R.id.action_firstFragment_to_thirdFragment)
                } else if (navController.currentDestination?.id == R.id.secondFragment) {
                    navController.navigate(R.id.action_secondFragment_to_thirdFragment)
                }
            }
            true
        }
    }

    private fun requestPermission() {
        isPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED

        var permissionRequest = ""

        if (!isPermissionGranted) {
            permissionRequest = Manifest.permission.SEND_SMS
        }
        if (permissionRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionRequest)
        }
    }
}