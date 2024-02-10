package com.User.angeldochealthcare.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_Insert_Response {
        @SerializedName("profile")
        @Expose
        private Object profile;
        @SerializedName("profileName")
        @Expose
        private Object profileName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

        public Object getProfile() {
            return profile;
        }

        public void setProfile(Object profile) {
            this.profile = profile;
        }

        public Object getProfileName() {
            return profileName;
        }

        public void setProfileName(Object profileName) {
            this.profileName = profileName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
}
