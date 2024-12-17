package ir.androidcoder.data.util

import android.content.Context

class TokenManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("TraktPrefs", Context.MODE_PRIVATE)

    fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit().putString("access_token", accessToken).apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }

    fun saveRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString("refresh_token", refreshToken).apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString("refresh_token", null)
    }

    fun clearTokens() {
        sharedPreferences.edit().clear().apply()
    }

}