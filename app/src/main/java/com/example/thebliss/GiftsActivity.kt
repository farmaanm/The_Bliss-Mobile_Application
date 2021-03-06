package com.example.thebliss

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.thebliss.databinding.ActivityGiftsBinding
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import android.content.Intent
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class
GiftsActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityGiftsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gifts)

        binding = ActivityGiftsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when (it.itemId) {

                R.id.nav_home -> beginHomeActivity()
                R.id.nav_cakes -> beginCakesActivity()
                R.id.nav_flowers -> beginFlowersActivity()
                R.id.nav_photography -> beginPhotographyActivity()
                R.id.nav_costumes -> beginCostumesActivity()
                R.id.nav_gifts -> beginGiftsActivity()
                R.id.nav_contact -> beginContactActivity()
                R.id.nav_about -> beginAboutActivity()
            }
            true
        }


        binding.btnCartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val but1 : Button = findViewById(R.id.button1)
        val but2 : Button = findViewById(R.id.button2)

        but1.setOnClickListener {

            val intent = Intent(this,CartActivity::class.java)
            intent.putExtra("ProductName","Baby Bathing Set")
            intent.putExtra("ProductImage","babywash1")
            intent.putExtra("ProductPrice","3000")
            startActivity(intent)

            Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show()
        }

        but2.setOnClickListener {

            val intent = Intent(this,CartActivity::class.java)
            intent.putExtra("ProductName","Baby Blankets")
            intent.putExtra("ProductImage","blankets1")
            intent.putExtra("ProductPrice","1000")
            startActivity(intent)

            Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show()
        }


    }

    private fun beginHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun beginCakesActivity() {
        val intent = Intent(this, CakesActivity::class.java)
        startActivity(intent)
    }


    private fun beginFlowersActivity() {
        val intent = Intent(this, FlowersActivity::class.java)
        startActivity(intent)
    }

    private fun beginPhotographyActivity() {
        val intent = Intent(this, PhotographyActivity::class.java)
        startActivity(intent)
    }

    private fun beginCostumesActivity() {
        val intent = Intent(this, CostumesActivity::class.java)
        startActivity(intent)
    }

    private fun beginGiftsActivity() {
        val intent = Intent(this, GiftsActivity::class.java)
        startActivity(intent)
    }

    private fun beginContactActivity() {
        val intent = Intent(this, ContactActivity::class.java)
        startActivity(intent)
    }

    private fun beginAboutActivity() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}