package com.example.thebliss

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.thebliss.databinding.ActivityCartBinding
import com.google.android.material.navigation.NavigationView
import java.util.*


class CartActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityCartBinding

    val productName: MutableList<String> = ArrayList()
    val productImage: MutableList<String> = ArrayList()
    val productPrice: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        binding = ActivityCartBinding.inflate(layoutInflater)
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


        val txtViewName: TextView = findViewById(R.id.viewName)
        val viewImage: ImageView = findViewById(R.id.viewImage)
        val txtViewPrice: TextView = findViewById(R.id.viewPrice)
        val txtQty: TextView = findViewById(R.id.Quantity)
        val lblTotal: TextView = findViewById(R.id.grandTotal)

        val qty :Int = 1
        var grandTotal :Int = 0

        /***********data from the other page****************/
        val pname = intent.getStringExtra("ProductName")
        val pimage = intent.getStringExtra("ProductImage")
        val pprice = intent.getStringExtra("ProductPrice")

        /***************appending data to list**************/
        productName.add(pname.toString())
        productImage.add(pimage.toString())
        productPrice.add(pprice.toString())

        /*********assigning and displaying data**************/
        for (i in 0 until productName.size) {

            val variableValue: String = productImage[i]
            viewImage.setImageResource(resources.getIdentifier(variableValue, "drawable", packageName))

            txtViewName.text = productName[i]
            txtViewPrice.text = productPrice[i]
            txtQty.text = qty.toString()

            grandTotal += productPrice[i].toInt() * qty
        }

        lblTotal.append(grandTotal.toString())

        txtQty.setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                //do what you want on the press of 'done'


                for (i in 0 until productName.size) {
                    grandTotal += productPrice[i].toInt() * qty
                }

                lblTotal.text = "Grand Total: "
                lblTotal.append(grandTotal.toString())
            }
            false
        }

        binding.btnCheckout.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("ProductName",productName[0])
            intent.putExtra("ProductQty",qty.toString())
            intent.putExtra("Total",grandTotal.toString())
            startActivity(intent)
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