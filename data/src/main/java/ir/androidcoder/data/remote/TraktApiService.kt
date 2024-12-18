package ir.androidcoder.data.remote

import ir.androidcoder.data.model.AccessTokenResponse
import okhttp3.internal.Util
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TraktApiService {

    //get token
    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String = "movieshosein://zapp.com/code",
        @Field("grant_type") grantType: String = "authorization_code"
    ): Response<AccessTokenResponse>

    //refresh token
    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun refreshAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = "refresh_token"
    ): AccessTokenResponse

    //revoke token - logout
    @POST("oauth/revoke")
    @FormUrlEncoded
    suspend fun revokeToken(
        @Field("token") accessToken : String,
        @Field("client_id") clientId : String,
        @Field("client_secret") clientSecret : String
    ) : Response<Util>

}