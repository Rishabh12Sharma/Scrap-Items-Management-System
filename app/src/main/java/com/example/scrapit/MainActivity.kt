package com.example.scrapit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    private lateinit var etEnterYourName: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin:Button
    private lateinit var btnSignUp: Button

    private val validName="rishabh"
    private val validPassword="sharma"

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEnterYourName=findViewById(R.id.etEnterYourName)
        etPassword=findViewById(R.id.etEnterPassword)
        btnLogin=findViewById(R.id.btnLogin)
        btnSignUp=findViewById(R.id.btnSignUp)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            val name=etEnterYourName.text.toString()
            val password=etPassword.text.toString()
            if(validName==name && validPassword==password)
            {
                val intent=Intent(this@MainActivity,ScrapActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this@MainActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
        btnSignUp.setOnClickListener{
            val intent1=Intent(this@MainActivity,ActivitySignUpPage::class.java)
            startActivity(intent1)
        }

        val currentUser = auth.currentUser

        if (currentUser != null) {
            // The user is already signed in, navigate to MainActivity
            val intent = Intent(this, ScrapActivity::class.java)
            startActivity(intent)
            finish()
        }

        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this, ScrapActivity::class.java)
                    if (user != null) {
                        intent.putExtra("name",user.displayName)
                        intent.putExtra("email",user.email)
                        intent.putExtra("photo",user.photoUrl)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}