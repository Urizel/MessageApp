package com.spb.kbv.messageapp.services;

import com.google.gson.annotations.SerializedName;
import com.spb.kbv.messageapp.infrastructure.ServiceResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public interface WebService {


    //Account
    @POST("/api/token")
    @FormUrlEncoded
    void login(
        @Field("username") String username,
        @Field("password") String password,
        /*@Field("client_id") String clientId,
        @Field("grant_type") String grantType,*/
        Callback<LoginResponse> callback);

    @POST("/api/v1/account/external/token")
    void loginWithExternalToken(@Body Account.LoginWithExternalTokenRequest request,
                                Callback<Account.LoginWithExternalTokenResponse> callback);

    @POST("/api/v1/account")
    void register (@Body Account.RegisterRequest request, Callback<Account.RegisterResponse> callback);

    @POST("/api/v1/account/external")
    void registerExternal(@Body Account.RegisterWithExternalTokenRequest request, Callback<Account.RegisterWithExternalTokenResponse> callback);

    @GET("/api/v1/account")
    void getAccount(Callback<Account.LoginWithLocalTokenResponse> callback);

    @PUT("/api/v1/account")
    void updateProfile(@Body Account.UpdateProfileRequest request, Callback<Account.UpdateProfileResponse> callback);

    @Multipart
    @PUT("/api/v1/account/avatar")
    void updateAvatar(@Part("avatar")TypedFile avatar, Callback<Account.ChangeAvatarResponse> callback);

    @PUT("/api/v1/account/password")
    void updatePassword(@Body Account.ChangePasswordRequest request, Callback<Account.ChangePasswordResponse> callback);

    @PUT("/api/v1/account/gcm-registration")
    void updateGcmRegistration(@Body Account.UpdateGcmRegistrationRequest request, Callback<Account.UpdateGcmRegistrationResponse> callback);

    //----------------------------------------------------------------------------------------
    //Contacts
    @GET("/api/v1/users")
    void searchUsers(@Query("query") String query, Callback<Contacts.SearchUserResponse> callback);

    @POST("/api/v1/contact-requests/{user}")
    void sendContactRequest (@Path("user") int userId, Callback<Contacts.SendContactRequestResponse> callback);

    @PUT("/api/v1/contact-requests/{user}")
    void respondToContactRequest(@Path("user") int userId, @Body RespondToContactRequest request, Callback<Contacts.RespondToContactRequestResponse> callback);

    @DELETE("/api/v1/contacts/{user}")
    void removeContact (@Path("user") int userId, Callback<Contacts.RemoveContactResponse> callback);

    @GET("/api/v1/contact-requests/sent")
    void getContactRequestsFromUs(Callback<Contacts.GetContactRequestResponse> callback);

    @GET("/api/v1/contact-requests/received")
    void getCOntactRequestsToUs(Callback<Contacts.GetContactRequestResponse> callback);

    @GET("/api/v1/contacts")
    void getContacts(Callback<Contacts.GetContactResponse> callback);

    //---------------------------------------------------------------------
    //Messages
    @Multipart
    @POST("/api/v1/messages")
    void sendMessgaes(
            @Part("message")TypedString message,
            @Part("to")TypedString to,
            @Part("photo")TypedFile photo,
            Callback<Messages.SendMessageResponse> callback);

    @DELETE("/api/v1/messages/{id}")
    void deleteMessage(@Path("id") int messageId, Callback<Messages.DeleteMessageResponse> callback);

    @PUT("/api/v1/messages/{id}/is-read")
    void markMessageAsRead(@Path("id") int messageId, Callback<Messages.MarkMessageAsReadResponse> callback);

    @GET("/api/v1/messages")
    void searchMessages(
        @Query("contactId") int from,
        @Query("includeSent") boolean includeSent,
        @Query("includeReceived") boolean includeReceived,
        Callback<Messages.SearchMessageResponse> callback);

    @GET("/api/v1/messages")
    void searchMessages(
            @Query("includeSent") boolean includeSent,
            @Query("includeReceived") boolean includeReceived,
            Callback<Messages.SearchMessageResponse> callback);

    @GET("/api/v1/messages/{id}")
    void getMessageDetails(@Path("id") int id, Callback<Messages.GetMessageDetailResponse> callback);



    public class RespondToContactRequest {
        public String response;

        public RespondToContactRequest(String response) {
            this.response = response;
        }
    }

    public class LoginResponse extends ServiceResponse {

        @SerializedName(".expires")
        public String expires;

        @SerializedName(".issued")
        public String issued;

        @SerializedName("access_token")
        public String token;

        @SerializedName("error")
        public String error;

        @SerializedName("error_description")
        public String errorDescription;
    }
}


