package com.example.scrapit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrapit.adapter.DashboardRecyclerAdapter
import com.example.scrapit.model.Scrap
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class ScrapActivity : AppCompatActivity() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var btnCheckInternet: Button

    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val scrapInfoList= arrayListOf<Scrap>(
        Scrap("NEWSPAPER","Recyclables","Rs. 18/KG","4.5",R.drawable.newspaper_image),
        Scrap("COPIES/BOOKS","Recyclables","Rs. 16/KG","4.6",R.drawable.copies_books),
        Scrap("CARDBOARD","Recyclables","Rs. 10/KG","4.8",R.drawable.cardboard_image),
        Scrap("PLASTIC","Recyclables","Rs. 10/KG","4.3",R.drawable.plastic_image),
        Scrap("IRON","Recyclables","Rs. 30/KG","5.0",R.drawable.iron_image),
        Scrap("STEEL","Recyclables","Rs. 37/KG","4.6",R.drawable.steel_image),
        Scrap("ALUMINIUM","Recyclables","Rs. 105/KG","4.9",R.drawable.aluminum_image),
        Scrap("BRASS","Recyclables","Rs. 305/KG","4.7",R.drawable.brass_image),
        Scrap("COPPER","Recyclables","Rs. 425/KG","4.6",R.drawable.copper_image),
        Scrap("IRON COOLER","Large Appliances","Rs. 30/KG","4.6", R.drawable.iron_cooler),
        Scrap("PLASTIC COOLER","Large Appliances","Rs. 15/KG","4.6",R.drawable.plastic_cooler),
        Scrap("WINDOW AC ALUMINIUM(1.5 TON)","Large Appliances","Rs. 2500/PIECE","4.6",R.drawable.air_conditioner),
        Scrap("FRONT LOAD FULLY AUTOMATIC WASHING MACHINE","Large Appliances","Rs. 800/PIECE","4.6",R.drawable.washing_machine),
        Scrap("TOP LOAD FULLY AUTOMATIC WASHING MACHINE","Large Appliances","Rs. 550/PIECE","4.6",R.drawable.washing_machine),
        Scrap("SEMI AUTOMATIC WASHING MACHINE (DOUBLE DRUM)","Large Appliances","Rs. 550/PIECE","4.6",R.drawable.washing_machine),
        Scrap("GEYSER","Large Appliances","Rs. 20/KG","4.6",R.drawable.geyser),
        Scrap("SINGLE DOOR FRIDGE ","Large Appliances","Rs. 750/KG","4.6",R.drawable.fridge),
        Scrap("DOUBLE DOOR FRIDGE ","Large Appliances","Rs. 1000/PIECE","4.6",R.drawable.fridge),
        Scrap("AC (2 TON)","Large Appliances","Rs. 4000/PIECE","4.6",R.drawable.air_conditioner),
        Scrap("AC COPPER (1.5 TON)","Large Appliances","Rs. 3500/PIECE","4.6",R.drawable.air_conditioner),
        Scrap("AC (1 TON)","Large Appliances","Rs. 2100/PIECE","4.6",R.drawable.air_conditioner)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap)


        recyclerDashboard=findViewById(R.id.recyclerDashboard)


        layoutManager= LinearLayoutManager(this)

        recyclerAdapter= DashboardRecyclerAdapter(this as Context,scrapInfoList)

        recyclerDashboard.adapter=recyclerAdapter

        recyclerDashboard.layoutManager=layoutManager

        recyclerDashboard.addItemDecoration(
            DividerItemDecoration(
                recyclerDashboard.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )

    }
}
