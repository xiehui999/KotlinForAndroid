package com.code4android.kotlinforandroid

/**
 *Created by Code4Android on 2017/5/8.
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //UI().setContentView(this@LoginActivity)
    }

    inner class UI : AnkoComponent<LoginActivity> {
        override fun createView(ui: AnkoContext<LoginActivity>): View {
            return with(ui) {
                verticalLayout {
                    val textView = textView("我是一个TextView") {
                        textSize = sp(17).toFloat()
                        textColor = context.resources.getColor(R.color.red)
                    }.lparams {
                        margin = dip(10)
                        height = dip(40)
                        width = matchParent
                    }
                    val name = editText("EditText")
                    button("Button") {
                        onClick { view ->
                            click()
                        }
                    }
                }
            }
        }
    }

    private fun click() {
/*        toast("Hello, ${name.text}!")
        alert("我是Dialog") {
            yesButton { toast("yes") }
            noButton { toast("no") }
        }.show()*/
        doAsync {
            Thread.sleep(3000)
            uiThread { toast("线程${Thread.currentThread().name}") }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
        }
    }
}

