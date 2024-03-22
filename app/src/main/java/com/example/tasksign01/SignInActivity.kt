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

        val idEditText = findViewById<EditText>(R.id.et_ID)
        val pwEditText = findViewById<EditText>(R.id.et_PW)

        var id : String = idEditText.text.toString()
        var pw : String = pwEditText.text.toString()

        var receivedSize = intent.getIntExtra("listSize", 0)
        for(i in 0..receivedSize-1){
            val tempReceive = intent.getStringExtra("userInfo $i ")?.split(", ")?: emptyList()
            receivedInfoList.add(tempReceive)
        }

        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            if(idEditText.text.isEmpty()){
                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pwEditText.text.isEmpty()){
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else{
                for(j in 0..receivedSize-1){
                    id = idEditText.text.toString()
                    pw = pwEditText.text.toString()
                    if(receivedInfoList[j][1] == id){
                        if(receivedInfoList[j][2] == pw){
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val loginIntent = Intent(this, HomeActivity::class.java)
                            loginIntent.putExtra("userInfo", receivedInfoList[j].toString())
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
            startActivity(signupIntent)
        }


    }
}
