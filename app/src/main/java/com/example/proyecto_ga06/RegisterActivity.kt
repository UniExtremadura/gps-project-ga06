package com.example.proyecto_ga06

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnBack: TextView
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvPasswordError: TextView
    private lateinit var etConfirmPassword: EditText
    private lateinit var tvConfirmPasswordError: TextView
    private lateinit var btnRegister: Button

    val uppercasePattern = ".*[A-Z].*".toRegex()
    val lowercasePattern = ".*[a-z].*".toRegex()
    val numberPattern = ".*[0-9].*".toRegex()
    val symbolPattern = ".*[@#$%^&+=].*".toRegex()
    val lengthPattern = ".{8,}".toRegex()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnBack = findViewById(R.id.btnBack)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tvPasswordError = findViewById(R.id.tvPasswordError)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tvConfirmPasswordError = findViewById(R.id.tvConfirmPasswordError)
        btnRegister = findViewById(R.id.btnRegister)

        btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val emailPattern = (
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
                        "((" +
                        "[0-1]?[0-9]{1,2}\\.[0-1]?[0-9]{1,2}\\.[0-1]?[0-9]{1,2}\\.[0-1]?[0-9]{1,2}" +
                        ")|(" +
                        "[a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4}" +
                        ")$"
                ).toRegex()

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().matches(emailPattern)) {
                    etEmail.error = getString(R.string.invalid_email)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                val requirements = mutableListOf<String>()

                if (!password.matches(uppercasePattern)) {
                    requirements.add(getString(R.string.check_uppercase))
                }
                if (!password.matches(lowercasePattern)) {
                    requirements.add(getString(R.string.check_lowercase))
                }
                if (!password.matches(numberPattern)) {
                    requirements.add(getString(R.string.check_number))
                }
                if (!password.matches(symbolPattern)) {
                    requirements.add(getString(R.string.check_symbol))
                }
                if (!password.matches(lengthPattern)) {
                    requirements.add(getString(R.string.check_length))
                }

                if (requirements.isNotEmpty()) {
                    tvPasswordError.text = requirements.joinToString(", ")
                    tvPasswordError.visibility = View.VISIBLE
                } else {
                    tvPasswordError.visibility = View.GONE
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                tvConfirmPasswordError.visibility = View.GONE
                if (etPassword.text.toString() != s.toString()) {
                    tvConfirmPasswordError.text = getString(R.string.passwords_do_not_match)
                    tvConfirmPasswordError.visibility = View.VISIBLE
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val onTouchListener = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP && event.rawX >= (v.right - (v as EditText).compoundDrawables[2].bounds.width())) {
                v.error = null
                if (v.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    v.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    v.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye_off, 0)
                } else {
                    v.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    v.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0)
                }
                v.setSelection(v.text.length)
                v.performClick() // Ya lo tenías
                return@OnTouchListener true
            } else {
                return@OnTouchListener false
            }
        }

        etPassword.setOnTouchListener(onTouchListener)
        etConfirmPassword.setOnTouchListener(onTouchListener)
    }
}