package unaeif411.delta.petmatch.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import unaeif411.delta.petmatch.R
import unaeif411.delta.petmatch.databinding.ActivityLoginBinding
import unaeif411.delta.petmatch.model.Authority
import unaeif411.delta.petmatch.model.LoginRequest
import unaeif411.delta.petmatch.utils.SessionManager
import unaeif411.delta.petmatch.model.LoggedInUserView
import unaeif411.delta.petmatch.model.privelege.Privilege
import unaeif411.delta.petmatch.model.role.Role
import unaeif411.delta.petmatch.viewmodel.LoginViewModel
import unaeif411.delta.petmatch.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    // Definition of the binding variable
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // With View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LoginViewModelFactory
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResponse.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binding.loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)
        })

        binding.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                LoginRequest(
                    username = binding.username.text.toString(),
                    password = binding.password.text.toString()
                )
            )
        }

        binding.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    LoginRequest(
                        username = binding.username.text.toString(),
                        password = binding.password.text.toString()
                    )
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            LoginRequest(
                                username = binding.username.text.toString(),
                                password = binding.password.text.toString()
                            )
                        )
                }
                false
            }

            binding.login.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                loginViewModel.login(
                    LoginRequest(
                        username = binding.username.text.toString(),
                        password = binding.password.text.toString()
                    )
                )
            }
        }

        loginViewModel.loginDataChanged(
            LoginRequest(
                username = binding.username.text.toString(),
                password = binding.password.text.toString()
            )
        )

        //Adds the "brand" colors to PetMatch text in header
        val textView: TextView = binding.petMatchName
        textView.text= Html.fromHtml("<font color=#CFCFCF>Welcome to </font>" + "<font color=#538691>Pet</font>" +
                "<font color=#E58838>Match</font>")
    }

    /**
     * Success login to redirect the app to the next Screen
     */
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val username = model.username
        /*val roles = model.roles

        if (roles.contains(Authority("ROLE_ADMIN"))){
            // todo Cambiar por activity de admin?
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            //Codigo original es despues de este comentario
            // Initiate successful logged in experience
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

        // Initiate successful logged in experience
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        //Complete and destroy login activity once successful
        finish()

        Toast.makeText(
            applicationContext,
            "$welcome $username",
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Unsuccessful login
     */
    private fun showLoginFailed(@StringRes errorString: Int) {

        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
        // return login activity

    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}