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

        //아이디 중복체크 위해 아이디 받아와 담을것
        var userIdList = listOf<String>()

        //이전에 회원가입 했다면 listSize가 0이 아님
        var gotListSize = intent.getIntExtra("listSize", 0)
        if(gotListSize!=0) {
            userIdList = intent.getStringExtra("userIDList")?.split(", ") ?: emptyList()
        }else{

        }

//        Toast.makeText(this, "리스트 전체 : ${userIdList} , 개수는 ${gotListSize}", Toast.LENGTH_LONG).show()

        var isIDOk = false

        var userNameInput = findViewById<EditText>(R.id.et_userName)
        var userIDInput = findViewById<EditText>(R.id.et_userID)
        var userPWInput = findViewById<EditText>(R.id.et_userPW)
        var userPWcheck = findViewById<EditText>(R.id.et_checkUserPW)
        var userAgeInput = findViewById<EditText>(R.id.et_userAge)
        var userMBTIInput = findViewById<EditText>(R.id.et_userMBTI)

        val createAccountButton = findViewById<Button>(R.id.btn_makeAccount)
        val checkIDButton = findViewById<Button>(R.id.btn_checkID)

        var newID = userIDInput.text.toString()

        checkIDButton.setOnClickListener {
            newID = userIDInput.text.toString()
            //기존 회원의 수가 0이 아니라면 gitListSize가 0보다 큼
            if(gotListSize>0){
                for(k in 0..gotListSize-1){
                    if(userIdList.contains(newID)){
                        isIDOk = false
                        //Toast.makeText(this,"다른 사람과 중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
                        break
                    }else{
                        isIDOk = true
                        continue
                    }
                }
            }else{
                isIDOk = true
            }

            if(isIDOk){
                Toast.makeText(this,"사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        createAccountButton.setOnClickListener {

            var newName = userNameInput.text.toString()
            //var newID = userIDInput.text.toString()
            var newPW = userPWInput.text.toString()
            var checkPW = userPWcheck.text.toString()
            var newAge = userAgeInput.text.toString()
            var newMBTI = userMBTIInput.text.toString()

            if(newName.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(이름)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newID.isEmpty()){
                Toast.makeText(this,"알맞지 않은 정보(아이디)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newPW.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(비밀번호)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(checkPW.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(비밀번호 확인)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newAge.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(나이)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newMBTI.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(MBTI)가 있습니다", Toast.LENGTH_SHORT).show()}


            if (newPW!=checkPW){
                Toast.makeText(this,"비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            } else if(!isIDOk){     //아이디 중복인데 무시하고 회원가입 누른 경우
                Toast.makeText(this, "ID 중복확인을 해주세요.", Toast.LENGTH_SHORT).show()
            } else if (newMBTI.length!=4){
                Toast.makeText(this,"MBTI가 잘못 입력되었습니다.", Toast.LENGTH_SHORT).show()
            }else{

                val userInfo = ("$newName, $newID, $newPW, $newAge, $newMBTI")

//
                //val numAge : Int = newAge.toInt()
                //val userInfo = ("$newName, $newID, $newPW, $numAge, $newMBTI")
//
                val objUserInfo: MutableMap<String, String> = mutableMapOf()
                objUserInfo["name"] = newName
                objUserInfo["id"] = newID
                objUserInfo["pw"] = newPW
                objUserInfo["age"] = newAge
                objUserInfo["mbti"] = newMBTI
                //object에 추가하기
                UserDataList.userDataList.add(objUserInfo)

                //Toast.makeText(this,"입력값 : $userInfo",Toast.LENGTH_SHORT).show()
                //Toast.makeText(this,"obj 입력값 : $objUserInfo",Toast.LENGTH_SHORT).show()

                val returnSIintent = Intent(this,SignInActivity::class.java)
                returnSIintent.putExtra("userInfo", userInfo)
                setResult(RESULT_OK, returnSIintent)
                finish()
            }
        }
    }
}