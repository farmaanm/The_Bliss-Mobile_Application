package com.example.thebliss

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.thebliss.databinding.ActivityContactBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.FirebaseDatabase

class ContactActivity : AppCompatActivity() {


    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityContactBinding

    lateinit var txtCustomerName: EditText
    lateinit var txtCustomerEmail: EditText
    lateinit var txtSubject: EditText
    lateinit var txtMessage: EditText
    lateinit var btnSendMessage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        txtCustomerName = findViewById(R.id.txtCustomerName)
        txtCustomerEmail = findViewById(R.id.txtCustomerEmail)
        txtSubject = findViewById(R.id.txtSubject)
        txtMessage = findViewById(R.id.txtMessage)
        btnSendMessage = findViewById(R.id.btnSendMessage)


        binding = ActivityContactBinding.inflate(layoutInflater)
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

        binding.btnContactIcon.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:0759168916")
            startActivity(callIntent)
        }

        //***************************************************SUBMIT CONTACT MESSAGE************************************************

        binding.btnSendMessage.setOnClickListener {
            saveContact()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        //***************************************************SOCIAL MEDIA************************************************
        binding.btnGmail.setOnClickListener {
            val openGmail = Intent(Intent.ACTION_VIEW)
            openGmail.data = Uri.parse("thebliss60@gmail.com")
            startActivity(openGmail)
        }

        binding.btnFacebook.setOnClickListener {
            val openFacebook = Intent(Intent.ACTION_VIEW)
            openFacebook.data = Uri.parse("https://web.facebook.com/The-Bliss-103466258740385/")
            startActivity(openFacebook)
        }

        binding.btnInstagram.setOnClickListener {
            val viewIntent = Intent("android.intent.action.VIEW", Uri.parse("https://www.instagram.com/_.the.bliss._/"))
            startActivity(viewIntent)
        }

        binding.btnTwitter.setOnClickListener {
            val viewIntent = Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/_The_Bliss_"))
            startActivity(viewIntent)
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

    private fun saveContact() {
        val customerName = binding.txtCustomerName.text.toString().trim()
        val customerEmail = binding.txtCustomerEmail.text.toString().trim()
        val customerSubject = binding.txtSubject.text.toString().trim()
        val message = binding.txtMessage.text.toString().trim()


        val ref = FirebaseDatabase.getInstance().getReference("Contact")
        val contactId = ref.push().key.toString()

        val contact = Contact(contactId, customerName, customerEmail, customerSubject, message)

        ref.child(contactId).setValue(contact).addOnCompleteListener {
            Toast.makeText(
                applicationContext,
                "Your Message is sent successfully",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}



