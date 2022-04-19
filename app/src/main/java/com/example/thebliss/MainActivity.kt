package com.example.thebliss

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){

                /*R.id.nav_contact -> val intent = Intent(this, ContactActivity::class.java)
                                    startActivity(intent)*/

                /*R.id.nav_contact ->Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()*/

                                     /*Intent homepage = new Intent(MainActivity.this, ContactActivity.class)
                                      startActivity(homepage)*/

                R.id.nav_contact -> beginActivity(ContactActivity())
            }
            true
        }

        //val contact_button = findViewById<>(nav_contact)

    }

    private fun beginActivity(activity: Activity) {
        val intent = Intent(this, Activity::class.java).apply{

        }
        startActivity(intent)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item) /*== R.id.nav_contact*/) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}