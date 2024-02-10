package com.User.angeldochealthcare.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
        @SerializedName("latest")
        @Expose
        private Boolean latest;
        @SerializedName("message")
        @Expose
        private String message;

        public Boolean getLatest() {
            return latest;
        }

        public void setLatest(Boolean latest) {
            this.latest = latest;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
