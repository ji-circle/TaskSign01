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
import androidx.lifecycle.ViewModelProvider
import kotlin.time.Duration.Companion.microseconds

class SignUpActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by lazy {
        ViewModelProvider(this)[SignUpViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //아이디 중복체크 위해 아이디 받아와 담을것
        var userIdList = listOf<String>()

        //이전에 회원가입 했다면 listSize가 0이 아님
        var gotListSize = intent.getIntExtra("listSize", 0)
        if (gotListSize != 0) {
            userIdList = intent.getStringExtra("userIDList")?.split(", ") ?: emptyList()
        } else {
        }

        var isIDOk = false

        var userNameInput = findViewById<EditText>(R.id.et_userName)
        var userIDInput = findViewById<EditText>(R.id.et_userID)
        var userPWInput = findViewById<EditText>(R.id.et_userPW)
        var userPWcheck = findViewById<EditText>(R.id.et_checkUserPW)
        var userAgeInput = findViewById<EditText>(R.id.et_userAge)
        var userMBTIInput = findViewById<EditText>(R.id.et_userMBTI)
        var userEmailInput = findViewById<EditText>(R.id.et_userEmail)

        val createAccountButton = findViewById<Button>(R.id.btn_makeAccount)
        val checkIDButton = findViewById<Button>(R.id.btn_checkID)

        var newID = userIDInput.text.toString()

        checkIDButton.setOnClickListener {
            newID = userIDInput.text.toString()
            //기존 회원의 수가 0이 아니라면 gitListSize가 0보다 큼
            if (gotListSize > 0) {
                for (k in 0..gotListSize - 1) {
                    if (userIdList.contains(newID)) {
                        isIDOk = false
                        break
                    } else {
                        isIDOk = true
                        continue
                    }
                }
            } else {
                isIDOk = true
            }
            viewModel.checkID(isIDOk)
        }

        createAccountButton.setOnClickListener {
            var newName = userNameInput.text.toString()
            var newPW = userPWInput.text.toString()
            var checkPW = userPWcheck.text.toString()
            var newAge = userAgeInput.text.toString()
            var newMBTI = userMBTIInput.text.toString()
            var newEmail = userEmailInput.text.toString()

            viewModel.register(newName, newID, newPW, checkPW, newAge, newMBTI, newEmail)

            val userInfo = ("$newName, $newID, $newPW, $newAge, $newMBTI, $newEmail")

            val returnSIintent = Intent(this, SignInActivity::class.java)
            returnSIintent.putExtra("userInfo", userInfo)
            setResult(RESULT_OK, returnSIintent)

            //Toast.makeText(this, "$newName, $newID, $newPW", Toast.LENGTH_SHORT).show()

            if (!isFinishing)
                finish()
        }
        viewModel.correctMsg.observe(this){
            if(!it.isNullOrBlank())
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
        viewModel.errorMsg.observe(this) {
            if (!it.isNullOrBlank())
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}