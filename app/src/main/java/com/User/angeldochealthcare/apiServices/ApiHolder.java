package com.User.angeldochealthcare.apiServices;

import com.User.angeldochealthcare.response.Banner_Response;
import com.User.angeldochealthcare.response.Get_Profile_Response;
import com.User.angeldochealthcare.response.Id_Specialization_Response;
import com.User.angeldochealthcare.response.LoginResponse;
import com.User.angeldochealthcare.response.Notification_Response;
import com.User.angeldochealthcare.response.OTP_Response;
import com.User.angeldochealthcare.response.Pages_Response;
import com.User.angeldochealthcare.response.Profile_Insert_Response;
import com.User.angeldochealthcare.response.Profile_Upadate_Response;
import com.User.angeldochealthcare.response.Spec_Search_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHolder {
    @POST("auth/mobile/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("loginId") String loginId,
                              @Field("whatsapp") String whatsapp,
                              @Field("tnc") String tnc,
                              @Field("deviceId") String deviceId);

    @POST("auth/user-verify")
    @FormUrlEncoded
    Call<OTP_Response> OTP_verify(@Field("loginId") String loginId,
                                  @Field("otp") String otp);

    @POST("user-details")
    @FormUrlEncoded
    Call<Profile_Insert_Response> profile_add(@Field("name") String name,
                                              @Field("nickName") String nickName,
                                              @Field("email") String email,
                                              @Field("contactNumber") String contactNumber,
                                              @Field("dob") String dob,
                                              @Field("gender") String gender,
                                              @Header("Authorization") String Token);

    @GET("user-details/profile")
    Call<Get_Profile_Response> get_profile(@Header("Authorization") String Token);

    @PUT("user-details/profile")
    @FormUrlEncoded
    Call<Profile_Upadate_Response> profile_update(@Field("name") String name,
                                                  @Field("nickName") String nickName,
                                                  @Field("email") String email,
                                                  @Field("contactNumber") String contactNumber,
                                                  @Field("dob") String dob,
                                                  @Field("gender") String gender,
                                                  @Header("Authorization") String Token);
    @GET("notification")
    Call<Notification_Response> get_notification(@Header("Authorization") String Token);
    @GET("Pages/{Id}")
    Call<Pages_Response> getPage(@Path("Id") String ID);
    @GET("banners/all")
    Call<Banner_Response> getBanner(@Query("type") String type);
    @GET("specialization")
    Call<Specialization_Response> Get_Spec(@Header("Authorization") String Token,
                                                @Query("limit") int limit,
                                                @Query("offset") int offset,
                                                @Query("keyword") String keyword,
                                                @Query("status") boolean status);
    @GET("doctor-details/specialization/{id}")
    Call<Id_Specialization_Response> Get_Id_Spec(@Path("id") int id,
                                                 @Header("Authorization") String Token,
                                                 @Query("limit") int limit,
                                                 @Query("offset") int offset,
                                                 @Query("keyword") String keyword);
    @GET("specialization")
    Call<Spec_Search_Response> Spec_Search(@Header("Authorization") String Token,
                                    @Query("limit") int limit,
                                    @Query("offset") int offset,
                                    @Query("keyword") String keyword,
                                    @Query("status") boolean status);
    @GET("doctor-details/verify")
    Call<Verifyed_Doc_Response> Get_verify_doc(@Header("Authorization") String Token,
                                         @Query("limit") int limit,
                                         @Query("offset") int offset,
                                         @Query("keyword") String keyword,
                                         @Query("verify") boolean verify);
}
