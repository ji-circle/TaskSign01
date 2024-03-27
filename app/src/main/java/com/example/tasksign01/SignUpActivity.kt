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

        var userInfoList = mutableListOf<List<String>>()
        // 여러 명의 데이터를 관리하려면 아예 전역으로 써야 하나...? 이상태면 실행할때마다 리셋되는거같은데

        var gotListSize = intent.getIntExtra("listSize", 0)
        if(gotListSize!=0) {
            for (i in 0..gotListSize - 1) {
                val tempReceive = intent.getStringExtra("userInfo $i ")?.split(", ") ?: emptyList()
                userInfoList.add(tempReceive)
//            val tempReceive = intent.getStringExtra("userInfo $i ")?.split(", ")?: emptyList()
//            receivedInfoList.add(tempReceive)
            }
            Toast.makeText(this, "리스트 전체 : ${userInfoList.toString()} , 개수는 ${gotListSize}", Toast.LENGTH_LONG).show()
        }
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

//        var newName = userNameInput.text.toString()
        var newID = userIDInput.text.toString()
//        var newPW = userPWInput.text.toString()
//        var checkPW = userPWcheck.text.toString()
//        var newAge = userAgeInput.text
//        var newMBTI = userMBTIInput.text.toString()


        checkIDButton.setOnClickListener {
            //@@@@@@@ 질문 : userIDInput을 다시 받아오지 않아도 되나? 연결하는거라 한번만 해도 되는건가?
            // 이 setOnClickListener도 onCreate 밖에 써도 되는거 맞나?
            newID = userIDInput.text.toString()
            if(gotListSize>0){
                for(k in 0..gotListSize-1){
                    if(userInfoList[k][1].contains(newID)){
                        isIDOk = false
                        Toast.makeText(this,"다른 사람과 중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
                        break
                    }else{
                        isIDOk = true
                        continue
                    }
                }
            }else if(idsList.contains(newID)){
                isIDOk = false
//                Toast.makeText(this,"중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
            }else{
                isIDOk = true
            }
            if(isIDOk){
                Toast.makeText(this,"사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
            }
        }

//        var isAgeEmpty = newAge == null

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
                Toast.makeText(this,"입력되지 않은 정보(아이디)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newPW.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(비밀번호)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(checkPW.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(비밀번호 확인)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newAge.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(나이)가 있습니다", Toast.LENGTH_SHORT).show()}
            if(newMBTI.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보(MBTI)가 있습니다", Toast.LENGTH_SHORT).show()}

//            if(userNameInput.text.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보(이름)가 있습니다", Toast.LENGTH_SHORT).show()}
//            if(userIDInput.text.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보(아이디)가 있습니다", Toast.LENGTH_SHORT).show()}
//            if(userPWInput.text.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보(비밀번호)가 있습니다", Toast.LENGTH_SHORT).show()}
//            if(userPWcheck.text.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보(비밀번호 확인)가 있습니다", Toast.LENGTH_SHORT).show()}
//            if(userAgeInput.text.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보(나이)가 있습니다", Toast.LENGTH_SHORT).show()}
//            if(userMBTIInput.text.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보(MBTI)가 있습니다", Toast.LENGTH_SHORT).show()}


//            if(newName.isEmpty() || newID.isEmpty() || newPW.isEmpty() || checkPW.isEmpty()|| newAge.isEmpty()|| newMBTI.isEmpty()){
//                Toast.makeText(this,"입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
//            }else {}
//
            if (newPW!=checkPW){
                Toast.makeText(this,"비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            } else if(!isIDOk){
                Toast.makeText(this, "중복된 아이디입니다. 수정해주세요.", Toast.LENGTH_SHORT).show()
            } else if (newMBTI.length!=4){
                Toast.makeText(this,"MBTI가 잘못 입력되었습니다.", Toast.LENGTH_SHORT).show()
            }else{
                idsList.add(newID)
                val numAge : Int = newAge.toInt()
                val userInfo = ("$newName, $newID, $newPW, $numAge, $newMBTI")
                //userInfoList.add(userInfo)

                Toast.makeText(this,"입력값 : $userInfo",Toast.LENGTH_SHORT).show()
//                Toast.makeText(this,"리스트 전체 : ${userInfoList}", Toast.LENGTH_SHORT).show()

                val returnSIintent = Intent(this,SignInActivity::class.java)
                //for(i in 0..userInfoList.size-1){
                //    returnSIintent.putExtra("userInfo $i ", userInfoList[i])
                //}
                //returnSIintent.putExtra("listSize",userInfoList.size)
                returnSIintent.putExtra("userInfo", userInfo)
                startActivity(returnSIintent)
                finish()
            }
        }





    }
}