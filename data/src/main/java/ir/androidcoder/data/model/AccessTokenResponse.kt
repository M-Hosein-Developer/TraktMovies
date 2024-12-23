package ir.androidcoder.data.model

data class AccessTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
    val created_at: Long,
    val error : String?,
    val error_description : String?
)
