package com.example.thebliss

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.thebliss.databinding.ActivityCheckoutBinding
import com.example.thebliss.databinding.ActivityContactBinding
import com.google.firebase.database.FirebaseDatabase

class CheckoutActivity : AppCompatActivity() {

    lateinit var txtCusName: EditText
    lateinit var txtCusAddress: EditText
    lateinit var txtCusEmail: EditText
    lateinit var txtCusPhone: EditText
    lateinit var txtCusDescription: EditText
    lateinit var btnPlaceOrder: Button


    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtCusName = findViewById(R.id.txtCusName)
        txtCusAddress = findViewById(R.id.txtCusAddress)
        txtCusEmail = findViewById(R.id.txtCusEmail)
        txtCusPhone = findViewById(R.id.txtCusPhone)
        txtCusDescription = findViewById(R.id.txtCusDescription)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)

        val cancel: Button = findViewById(R.id.btnCancel)
        //val placeOrder: Button = findViewById(R.id.btnPlaceOrder)

        cancel.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        //val pname = intent.getStringExtra("ProductName")
        //val pqty = intent.getStringExtra("ProductQty")
        //val total = intent.getStringExtra("Total")

        btnPlaceOrder.setOnClickListener {
            saveCheckout()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveCheckout() {

        val pname = intent.getStringExtra("ProductName").toString()
        val pqty = intent.getStringExtra("ProductQty").toString()
        val total = intent.getStringExtra("Total").toString()

        val cusName = txtCusName.text.toString().trim()
        val cusAddress = txtCusAddress.text.toString().trim()
        val cusEmail = txtCusEmail.text.toString().trim()
        val cusPhone = txtCusPhone.text.toString().trim()
        val cusDescription = txtCusDescription.text.toString().trim()

        val ref = FirebaseDatabase.getInstance().getReference("Checkout")
        val checkoutId = ref.push().key.toString()

        val checkout = Checkout(checkoutId, cusName, cusAddress, cusEmail, cusPhone,cusDescription,pname,pqty,total)

        ref.child(checkoutId).setValue(checkout).addOnCompleteListener {
            Toast.makeText(
                applicationContext,
                "Your Message is sent successfully",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}