package com.example.tasksign01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idEditText = findViewById<EditText>(R.id.et_ID)
        val pwEditText = findViewById<EditText>(R.id.et_PW)

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
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val Intent
            }
        }

        val id : String = idEditText.text.toString()
        val pw : String = pwEditText.text.toString()

        }
    }
}