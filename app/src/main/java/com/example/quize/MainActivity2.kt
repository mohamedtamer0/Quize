package com.example.quize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class MainActivity2 : AppCompatActivity() {

    var val_qu : Array<String>? = null
    var val_ans : Array<String>? = null
    var index:Int? =null
    var def = "Press A Button for Answer"
    var txtS:TextToSpeech? =null
    var res:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main2)

        txtS = TextToSpeech(this,object : TextToSpeech.OnInitListener{
            override fun onInit(p0: Int) {
              res =  txtS!!.setLanguage(Locale.ENGLISH)
            }

        })

        val_qu = resources.getStringArray(R.array.qu)
        val_ans = resources.getStringArray(R.array.ans)


        index = 0

        tv_q.text = "${val_qu!![index!!]}"
        tv_ans.text = def

        tv_xx.text = "${index!! + 1}"
        tv_yy.text = "/${val_qu!!.size}"


    }


    fun showAction(v:View?) {
        when(v?.id) {
            R.id.btn_back -> {
              try {
                  index = index!! - 1
                  tv_q.text = "${val_qu!![index!!]}"
                  tv_xx.text = (index!! + 1).toString()
                  tv_ans.text = def
              }catch (ex:Exception) {
                  index = val_qu!!.size  -1
                  tv_q.text = "${val_qu!![index!!]}"
                  tv_xx.text = (index!! + 1).toString()
              }
            }

            R.id.btn_ans -> {
                tv_ans.text = "${val_ans!![index!!]}"
            }

            R.id.btn_front -> {
                try {
                    index = index!! + 1
                    tv_q.text = "${val_qu!![index!!]}"
                    tv_xx.text = (index!! + 1).toString()
                    tv_ans.text = def
                }catch (ex:Exception){
                    index = 5 - val_ans!!.size
                    tv_q.text = "${val_qu!![index!!]}"
                    tv_xx.text = (index!! + 1).toString()
                }
            }

            R.id.btn_Voice -> {
                if (res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show()
                }else {
                    txtS!!.speak(tv_q.text, TextToSpeech.QUEUE_FLUSH,null,null)
                }
            }
        }
    }


}