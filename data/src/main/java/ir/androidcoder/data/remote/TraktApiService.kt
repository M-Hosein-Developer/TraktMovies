package ir.androidcoder.data.remote

import ir.androidcoder.data.model.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TraktApiService {

    @GET("oauth/authorize")
    fun getAuthorizationCode(
        @Query("response_type") responseType: String = "code",
        @Query("client_id") clientId: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("state") state: String? = null
    ): Response<Unit>

    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String = "https://zapp.com",
        @Field("grant_type") grantType: String = "authorization_code"
    ): AccessTokenResponse

    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun refreshAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = "refresh_token"
    ): AccessTokenResponse

}