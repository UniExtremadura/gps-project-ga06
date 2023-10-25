package com.example.proyecto_ga06


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Declaración de vistas
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicialización de vistas
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        // Establecer listeners para los botones
        loginButton.setOnClickListener { login() }
        registerButton.setOnClickListener { register() }
    }

    private fun login() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            //TODO Cuando alguno de los campos este vacio (se puede añadir que no sea correcto)
            showToast("Por favor, rellena todos los campos.")
            return
        }

        //TODO Lógica de autenticación con el servidor.
        showToast("Intentando iniciar sesión...")
    }

    private fun register() {
        // Redirigir a la pantalla de Registro
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        //TODO Borrar antes de la version final
        showToast("Redirigiendo a la pantalla de registro...") //Mensaje de Desarrollo
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
