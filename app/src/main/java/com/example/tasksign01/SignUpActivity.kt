package com.example.tasksign01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.time.Duration.Companion.microseconds

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var userInfoList = mutableListOf<String>()
        var idsList = mutableListOf<String>()
        var isIDOk = false

        var userNameInput = findViewById<EditText>(R.id.et_userName)
        var userIDInput = findViewById<EditText>(R.id.et_userID)
        var userPWInput = findViewById<EditText>(R.id.et_userPW)
        var userPWcheck = findViewById<EditText>(R.id.et_checkUserPW)
        var userAgeInput = findViewById<EditText>(R.id.et_userAge)
        var userMBTIInput = findViewById<EditText>(R.id.et_userMBTI)

        val createAccountButton = findViewById<Button>(R.id.btn_makeAccount)
        val checkIDButton = findViewById<Button>(R.id.btn_checkID)

        var newName : String = userNameInput.text.toString()
        var newID : String = userIDInput.text.toString()
        var newPW : String = userPWInput.text.toString()
        var checkPW : String = userPWcheck.text.toString()
        var newAge : Int = userAgeInput.text.toString().toInt()
        var newMBTI : String = userMBTIInput.toString()

        checkIDButton.setOnClickListener {
            //@@@@@@@ 질문 : userIDInput을 다시 받아오지 않아도 되나? 연결하는거라 한번만 해도 되는건가?
            // 이 setOnClickListener도 onCreate 밖에 써도 되는거 맞나?
            newID = userIDInput.text.toString()
            if(idsList.contains(newID)){
                isIDOk = false
                Toast.makeText(this,"중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
            }else{
                isIDOk = true
                idsList.add(newID)
            }
        }

        var isAgeEmpty = newAge == null

        createAccountButton.setOnClickListener {
            if(newName.isEmpty() || newID.isEmpty() || newPW.isEmpty() || checkPW.isEmpty()|| isAgeEmpty|| newMBTI.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }else if ( newPW!=checkPW ){
                Toast.makeText(this,"비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            } else if(!isIDOk){
                Toast.makeText(this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
            } else if (newMBTI.length>4){
                Toast.makeText(this,"MBTI가 잘못 입력되었습니다.", Toast.LENGTH_SHORT).show()
            }else{
                val userInfo = ("$newName, $newID, $newPW, $newAge, $newMBTI")
                userInfoList.add(userInfo)

                Toast.makeText(this,"$userInfo",Toast.LENGTH_SHORT).show()
//
//                val returnSIintent = Intent(this,SignInActivity::class.java)
//                for(i in 0..userInfoList.size-1){
//                    returnSIintent.putExtra("userInfo $i ", userInfoList[i])
//                }
//                returnSIintent.putExtra("listSize",userInfoList.size)
                finish()
            }
        }





    }
}