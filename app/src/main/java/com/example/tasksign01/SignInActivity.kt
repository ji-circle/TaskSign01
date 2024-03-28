package com.example.tasksign01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

//회원가입한 정보들을 공유...
object UserDataList{
    var userDataList = mutableListOf<Map<String, String>>()
}
class SignInActivity : AppCompatActivity() {

    lateinit var idEditText : EditText
    lateinit var pwEditText : EditText

    var oneList : List<String> = emptyList()
    var infoFromUp : ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK) {
            oneList = it.data?.getStringExtra("userInfo")?.split(", ") ?: emptyList()
            //Toast.makeText(this, "받은 reg : $oneList", Toast.LENGTH_SHORT).show()
        }else{
            oneList = emptyList()
        }
        //Toast.makeText(this, "전체 reg : ${UserDataList.userDataList}", Toast.LENGTH_SHORT).show()

        //회원가입 한 적이 있다면, 직전에 입력한 정보를 자동입력
        if(oneList.isNotEmpty()){
            //Toast.makeText(this,"${oneList[1]} 아이디 ${oneList[2]}", Toast.LENGTH_SHORT).show()
            idEditText.setText(oneList[1])
            pwEditText.setText(oneList[2])
        }else{}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        idEditText = findViewById<EditText>(R.id.et_ID)
        pwEditText = findViewById<EditText>(R.id.et_PW)

        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {

            //Toast.makeText(this, "${oneList.isNotEmpty()} onelist notempty?", Toast.LENGTH_SHORT).show()

            //자동완성될 정보 없고 빈칸으로 로그인 눌렀을 때
            if(idEditText.text.isEmpty()){
                Toast.makeText(this, "아이디를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            if(pwEditText.text.isEmpty()){
                Toast.makeText(this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }

            if(idEditText.text.isNotEmpty() && pwEditText.text.isNotEmpty()){
                var objIndex = 0
                var id = idEditText.text.toString()
                var pw = pwEditText.text.toString()
                var isMatched = 0

                UserDataList.userDataList.forEach{ user->
                    val objID = user["id"]
                    val objPW = user["pw"]

                    //Toast.makeText(this, "$objID , $objPW , ${objID==id} , ${objPW == pw}", Toast.LENGTH_SHORT).show()

                    if(objID == id){
                        if(objPW == pw){
                            isMatched += 2
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val thisUser = UserDataList.userDataList[objIndex].values.joinToString(", ")
                            val loginIntent = Intent(this, HomeActivity::class.java)
                            loginIntent.putExtra("userInfo", thisUser)
                            //Toast.makeText(this, "sent : ${thisUser}", Toast.LENGTH_SHORT).show()
                            startActivity(loginIntent)
                        }else{
                        }
                    }else{
                    }
                    //몇 번째 사람의 정보인지 확인하려고... 인덱스 어떻게 받지ㅠ
                    objIndex += 1
                }
                //when(isMatched){
                //    0 -> Toast.makeText(this,"아이디/비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                    //2 -> Toast.makeText(this, "ISMATCHED 로그인 성공", Toast.LENGTH_SHORT).show()
                //}
                if(isMatched == 0){
                    Toast.makeText(this,"아이디/비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val signupIntent = Intent(this, SignUpActivity::class.java)

        val signupButton = findViewById<Button>(R.id.btn_signup)
        signupButton.setOnClickListener {
            if(oneList.isNotEmpty()){   //처음이 아니라면 0이 아님
                //id 부분만 받아서 리스트로 만들고, 그걸 string으로 바꿔서 intent로 보냄
                val idList = UserDataList.userDataList.flatMap { it["id"]?.split(",")?: emptyList() }
                signupIntent.putExtra("userIDList", idList.toString().replace("[","").replace("]",""))
            }
            //처음인 경우와 아닌 경우를 구분하려고 보냄... 0인지 0이 아닌지로
            signupIntent.putExtra("listSize", oneList.size)

            infoFromUp.launch(signupIntent)
        }
    }
}
