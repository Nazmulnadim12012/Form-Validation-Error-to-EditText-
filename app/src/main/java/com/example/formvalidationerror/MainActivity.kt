package com.example.formvalidationerror

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import kotlin.system.exitProcess

lateinit var firstNameEditText: EditText
lateinit var lastNameEditText: EditText
lateinit var loginEmailEditText: EditText
lateinit var passwordEditText: EditText
lateinit var cancelButton: Button
lateinit var submitButton: Button
lateinit var textInputLayout: TextInputLayout


var isAllfieldschecked = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firstNameEditText = findViewById(R.id.first_name)
        lastNameEditText = findViewById(R.id.last_name)
        loginEmailEditText = findViewById(R.id.login_email)
        passwordEditText = findViewById(R.id.login_password)
        cancelButton = findViewById(R.id.cancel_button)
        submitButton = findViewById(R.id.submit_button)
        textInputLayout = findViewById(R.id.textInputLayout)

       val materialEditText = textInputLayout.editText




        submitButton.setOnClickListener {

            isAllfieldschecked = allFieldsChecked()

            if (isAllfieldschecked){
                startActivity(Intent(this@MainActivity,Activity2::class.java))
                finish()
            }

        }

        cancelButton.setOnClickListener {
            finish()
            exitProcess(0)
        }

        materialEditText?.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {



            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val maxLength = 20
                if (s!=null && s.length > 20){
                    materialEditText.setText(s.substring(0,maxLength)) // Truncate the text to max length
                    materialEditText.setSelection(maxLength) // Move the cursor to the end of the truncated text
                }

            }
        })

    }


    private fun allFieldsChecked(): Boolean {

        if (firstNameEditText.length() == 0){
            firstNameEditText.error = "Enter first name"
            return false
        }

        if (lastNameEditText.length() == 0){
            lastNameEditText.error = "Enter last name"
            return false
        }
        if (loginEmailEditText.length() == 0){
            loginEmailEditText.error = "Enter valid email address "
            return false
        }
        if (passwordEditText.length() < 6){
            passwordEditText.error = "Password must be 6 character "
            return false
        }

        return true

    }
}