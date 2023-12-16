package com.example.scrapit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.scrapit.model.FirebaseScrapModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
   lateinit var etCustomerName: EditText
    lateinit var etCustomerEmailAddress:EditText
    lateinit var etCustomerMobileNumber:EditText
    lateinit var btnRegister: Button
    lateinit var radioGroup: RadioGroup
    lateinit var address:EditText
    lateinit var etCustomerExpectedScrap:EditText

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
       radioGroup = findViewById(R.id.radioGroup)

        etCustomerName=findViewById(R.id.etEnterYourName)
        etCustomerEmailAddress=findViewById(R.id.etEnterEmailAddress)
        etCustomerMobileNumber=findViewById(R.id.etEnterMobileNumber)
        btnRegister=findViewById(R.id.btnRegister)
        address=findViewById(R.id.etEnterHomeAddress)
        etCustomerExpectedScrap=findViewById(R.id.etExpectedScrap)

        btnRegister.setOnClickListener {
            val customerName: String = etCustomerName.text.toString()
            val customerEmailAddress: String = etCustomerEmailAddress.text.toString()
            val selectedOption: String
            val homeAddress: String = address.text.toString()
            val expectedScrap: String=etCustomerExpectedScrap.text.toString()

            val selectedRadioButtonId: Int = radioGroup.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                selectedOption = selectedRadioButton.text.toString()

                database = FirebaseDatabase.getInstance().getReference("ScrapOrders")
                val scrap = FirebaseScrapModel(customerName, customerEmailAddress, expectedScrap, selectedOption, homeAddress)
                database.child(customerName).setValue(scrap).addOnSuccessListener {
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please select a radio button", Toast.LENGTH_LONG).show()
            }
        }
    }
}