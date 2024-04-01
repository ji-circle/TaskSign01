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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.time.Duration.Companion.microseconds


class SignUpViewModel : ViewModel() {

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    private val _correctMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> get() = _errorMsg
    val correctMsg: LiveData<String> get() = _correctMsg

    var isIDok: Boolean = false


    fun register(
        newName: String,
        newID: String,
        newPW: String,
        checkPW: String,
        newAge: String,
        newMBTI: String,
        newEmail: String
    ) {
        if (newName.isEmpty()) {
            _errorMsg.value = "입력되지 않은 정보(이름)가 있습니다"
            return
        }
        if (newID.isEmpty()) {
            _errorMsg.value = "알맞지 않은 정보(아이디)가 있습니다"
            return
        }
        if (newPW.isEmpty()) {
            _errorMsg.value = "입력되지 않은 정보(비밀번호)가 있습니다"
            return
        }
        if (checkPW.isEmpty()) {
            _errorMsg.value = "입력되지 않은 정보(비밀번호 확인)가 있습니다"
            return
        }
        if (newAge.isEmpty()) {
            _errorMsg.value = "입력되지 않은 정보(나이)가 있습니다"
            return
        }
        if (newMBTI.isEmpty()) {
            _errorMsg.value = "입력되지 않은 정보(MBTI)가 있습니다"
            return
        }

        if (newPW != checkPW) {
            _errorMsg.value = "비밀번호가 일치하지 않습니다"
            return
            //}if(!isSafePW(newPW)){
            //    Toast.makeText(this,"지금 비밀번호 : $newPW",Toast.LENGTH_SHORT).show()
            //    Toast.makeText(this,"비밀번호가 강력하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
        if (!isIDok) {     //아이디 중복인데 무시하고 회원가입 누른 경우
            _errorMsg.value = "ID 중복확인을 해주세요."
            return
        }
        if (newMBTI.length != 4) {
            _errorMsg.value = "MBTI가 잘못 입력되었습니다."
            return
        }
        if (!newEmail.contains('@')) {
            _errorMsg.value = "이메일 형식이 올바르지 않습니다."
            return
        }

        val objUserInfo: MutableMap<String, String> = mutableMapOf()
        objUserInfo["name"] = newName
        objUserInfo["id"] = newID
        objUserInfo["pw"] = newPW
        objUserInfo["age"] = newAge
        objUserInfo["mbti"] = newMBTI
        objUserInfo["email"] = newEmail
        //object에 추가하기
        UserDataList.userDataList.add(objUserInfo)
        _correctMsg.value = "$newName, $newID, $newPW"

//        val newUser = User(
//            name = newName,
//            id = newID,
//            pw = newPW,
//            age = newAge,
//            mbti = newMBTI,
//            email = newEmail
//        )
//        UserDataList.userDataList.add(newUser)

    }

    fun checkID(idOk: Boolean) {
        isIDok = idOk
        if (idOk) {
            _correctMsg.value = "사용 가능한 아이디입니다."
        } else {
            _errorMsg.value = "중복된 아이디입니다."
        }
    }
}
//
//fun isSafePW(password: String): Boolean {
////        val upperCase = Regex("[A-Z]]")
//    val textSymbol = Regex("[^A-Za-z0-9]")
////
////
////        val isUpperExist = password.contains(upperCase)
//    val isSymbolExist = textSymbol.matches(password)
////        val isLengthOK = password.length >= 4
////
//    return isUpperExist && isSymbolExist && isLengthOK
////    }
//}