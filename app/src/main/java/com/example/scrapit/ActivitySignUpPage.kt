package com.example.scrapit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.scrapit.model.signupName
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivitySignUpPage : AppCompatActivity() {
    lateinit var etEnterYourName: EditText
    lateinit var etEnterPassword:EditText
    lateinit var etEnterEmailAddress:EditText
    lateinit var etEnterMobileNumber:EditText
    lateinit var btnSignUp:Button

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        etEnterYourName=findViewById(R.id.etEnterYourName)
        etEnterPassword=findViewById(R.id.etEnterPassword)
        etEnterEmailAddress=findViewById(R.id.etEnterEmailAddress)
        etEnterMobileNumber=findViewById(R.id.etEnterMobileNumber)
        btnSignUp=findViewById(R.id.btnSignUp)


        btnSignUp.setOnClickListener{
            val entername=etEnterYourName.text.toString()
            val enterpassword=etEnterPassword.text.toString()
            val phonenumber=etEnterMobileNumber.text.toString()
            val message="Scrap Items"

            database= FirebaseDatabase.getInstance().getReference("signup")
            val Scrap= signupName(entername,enterpassword,phonenumber)
            database.child(entername).setValue(Scrap).addOnSuccessListener {
                Toast.makeText(this,"Sucessfully Saved",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }


            if(entername!=null && enterpassword!=null)
            {
                val intent2=Intent(this@ActivitySignUpPage,ScrapActivity::class.java)
                startActivity(intent2)
            }

        }
    }
}