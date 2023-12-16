package com.example.scrapit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.scrapit.model.Scrap
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NextActivity : AppCompatActivity() {
    lateinit var txtScrapNameNext: TextView
    lateinit var txtScrapTypeNext: TextView
    lateinit var txtScrapPriceNext: TextView
    lateinit var txtScrapRatingNext: TextView
    lateinit var imgScrapImageNext: ImageView
    lateinit var btnRegister: TextView

    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        txtScrapNameNext=findViewById(R.id.txtScrapNameNext)
        txtScrapTypeNext=findViewById(R.id.txtScrapTypeNext)
        txtScrapPriceNext=findViewById(R.id.txtScrapPriceNext)
        txtScrapRatingNext=findViewById(R.id.txtScrapRatingNext)
        imgScrapImageNext=findViewById(R.id.imgScrapImageNext)
        btnRegister=findViewById(R.id.btnRegister)

        txtScrapNameNext.text=intent.extras?.getString("ScrapName").toString()
        txtScrapTypeNext.text=intent.extras?.getString("ScrapType").toString()
        txtScrapPriceNext.text=intent.extras?.getString("ScrapPrice").toString()
        txtScrapRatingNext.text=intent.extras?.getString("ScrapRating").toString()
        imgScrapImageNext.id= intent.extras?.getInt("ScrapImage")!!.toInt()

        btnRegister.setOnClickListener{
            val ScrapName=txtScrapNameNext.text.toString()
            val ScrapType=txtScrapTypeNext.text.toString()
            val ScrapPrice=txtScrapPriceNext.text.toString()
            val ScrapRating=txtScrapRatingNext.text.toString()
            val ScrapImage=imgScrapImageNext.imageAlpha.toInt()

            val intent1= Intent(this@NextActivity,RegisterActivity::class.java)
            startActivity(intent1)
        }
    }
}