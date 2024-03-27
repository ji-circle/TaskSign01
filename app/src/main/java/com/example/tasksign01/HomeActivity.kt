package com.example.tasksign01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tempReceive = intent.getStringExtra("userInfo")?.split(", ")?: emptyList()
        var receivedUserInfoList = tempReceive

        val viewName = findViewById<TextView>(R.id.tv_thisName)
        val viewID = findViewById<TextView>(R.id.tv_thisID)
        val viewPW = findViewById<TextView>(R.id.tv_thisPW)
        val viewAge = findViewById<TextView>(R.id.tv_thisAge)
        val viewMBTI = findViewById<TextView>(R.id.tv_thisMBTI)
        val imageHome = findViewById<ImageView>(R.id.homeImage)

        viewName.setText(tempReceive[0].toString())
        viewID.setText(tempReceive[1].toString())
        viewPW.setText(tempReceive[2].toString())
        viewAge.setText(tempReceive[3].toString())
        viewMBTI.setText(tempReceive[4].toString())

        val random = Random
        val num = random.nextInt(5)+1

        when(num){
            1->imageHome.setImageResource(R.drawable.home01)
            2->imageHome.setImageResource(R.drawable.home02)
            3->imageHome.setImageResource(R.drawable.home03)
            4->imageHome.setImageResource(R.drawable.home04)
            5->imageHome.setImageResource(R.drawable.home05)

        }

        val btn_homefinish = findViewById<Button>(R.id.btn_finish)
        btn_homefinish.setOnClickListener{
            finish()
        }


    }
}