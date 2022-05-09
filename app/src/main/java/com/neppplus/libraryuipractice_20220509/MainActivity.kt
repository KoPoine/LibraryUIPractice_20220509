package com.neppplus.libraryuipractice_20220509

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents() {
        profileImg.setOnClickListener {
            val myIntent = Intent(this, ProfileActivity::class.java)
            startActivity(myIntent)
        }

        callBtn.setOnClickListener {

            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
                    val myUri = Uri.parse("tel:${phoneNumTxt.text}")
                    val myIntent = Intent (Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
//                    Toast.makeText(this@MainActivity, "권한이 거절 되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }

//            1. TedPermission 클래스 호출 (create())
            TedPermission.create()
//                    2. 상황에 따른 (~~Listener) 커스텀 해놓은 퍼미션 리스너를 가져와서 담아준다.
                .setPermissionListener(pl)
//                    3. 어떠한 권한을 획득할 건지 기입
                .setPermissions(Manifest.permission.CALL_PHONE)
//                    5. toast가 아닌 강하게 alert 계열로 유저에게 권한 요구 가능 - Google 보안정책에 반하는 내용.
                .setDeniedMessage("권한 획득 실패시 앱 사용이 불가능합니다.")
//                    4. check 함수를 통해 권한 확인 로직 진행
                .check()
        }
    }

    fun setValues() {

    }
}