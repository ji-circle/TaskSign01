package com.example.tasksign01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        var receivedInfoList = mutableListOf<List<String>>()
//        var receivedInfoList = mutableListOf<String>()

        val idEditText = findViewById<EditText>(R.id.et_ID)
        val pwEditText = findViewById<EditText>(R.id.et_PW)

        var id = idEditText.text.toString()
        var pw = pwEditText.text.toString()

        //var j = 0

        val tempReceive= intent.getStringExtra("userInfo")?.split(", ")?:0
        if(tempReceive !is Int) {
            receivedInfoList.add(tempReceive as List<String>)
        }
        //var receivedSize = intent.getIntExtra("listSize", 0)
        //for(i in 0..receivedSize-1){
//            val tempReceive = intent.getStringExtra("userInfo")?.split(", ")?:emptyList()
//            receivedInfoList.add(tempReceive)
//            val tempReceive = intent.getStringExtra("userInfo $i ")?.split(", ")?: emptyList()
//            receivedInfoList.add(tempReceive)
        //}

        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {

            Toast.makeText(this,"받은 리스트 ${receivedInfoList}", Toast.LENGTH_SHORT).show()

            if(idEditText.text.isEmpty()){
                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pwEditText.text.isEmpty()){
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!idEditText.text.isEmpty()&&!pwEditText.text.isEmpty()){
                for(j in 0..receivedInfoList.size-1){
                    id = idEditText.text.toString()
                    pw = pwEditText.text.toString()
                    if(receivedInfoList[j][1] == id){
                        if(receivedInfoList[j][2] == pw){
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val thisUser = receivedInfoList[j].toString()
                            val loginIntent = Intent(this, HomeActivity::class.java)
                            loginIntent.putExtra("userInfo", thisUser)
                            Toast.makeText(this, "sent : ${thisUser}", Toast.LENGTH_SHORT).show()
                            startActivity(loginIntent)
                        }else{
                            Toast.makeText(this,"비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this,"아이디를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                    }
                }



            }
        }
        val signupButton = findViewById<Button>(R.id.btn_signup)
        signupButton.setOnClickListener {
            val signupIntent = Intent(this, SignUpActivity::class.java)
            //signupIntent.putExtra("userInfoList", receivedInfoList)
            if(receivedInfoList.size>=1) {
                for (i in 0..receivedInfoList.size - 1) {
                    signupIntent.putExtra("userInfo $i ", receivedInfoList[i].toString())
                }

                // 여기까지
            }
            if(tempReceive !is Int){
                signupIntent.putExtra("listSize", receivedInfoList.size)
            }else {
                signupIntent.putExtra("listSize", 0)
            }


            startActivity(signupIntent)
        }


    }
}
