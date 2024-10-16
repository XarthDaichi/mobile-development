package unaeif411.delta.petmatch.utils

import android.content.Context
import android.content.SharedPreferences
import unaeif411.delta.petmatch.R
import unaeif411.delta.petmatch.model.Authority

class SessionManager (context: Context){
    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USERNAME = "username"
        const val USER_TOKEN = "user_token"
        const val USER_ROLE = "user_role"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveUserRole(role: Authority) {
        val editor = prefs.edit()
        editor.putString(USER_ROLE, role.authority)
        editor.apply()
    }

    fun saveUsername(username: String){
        val editor = prefs.edit()
        editor.putString(USERNAME, username)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchUserRole(): String? {
        return prefs.getString(USER_ROLE, null)
    }

    /**
     * Function to delete auth token
     */
    fun deleteAuthToken() {
        val editor = prefs.edit()
        editor.remove (USER_TOKEN)
        editor.apply()
    }
}