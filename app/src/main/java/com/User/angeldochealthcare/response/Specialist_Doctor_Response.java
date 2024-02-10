package com.User.angeldochealthcare.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specialist_Doctor_Response {
        @SerializedName("result")
        @Expose
        private List<Result> result;
        @SerializedName("total")
        @Expose
        private Integer total;

        public List<Result> getResult() {
            return result;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }
    public class Result {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("reg_year")
        @Expose
        private Integer regYear;
        @SerializedName("experience")
        @Expose
        private Integer experience;
        @SerializedName("associated_hospital")
        @Expose
        private Object associatedHospital;
        @SerializedName("degree")
        @Expose
        private Object degree;
        @SerializedName("fee")
        @Expose
        private Integer fee;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("verify")
        @Expose
        private Boolean verify;
        @SerializedName("updatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("account")
        @Expose
        private Account account;
        @SerializedName("doctorSpecialization")
        @Expose
        private List<DoctorSpecialization> doctorSpecialization;
        @SerializedName("doctorSpecialist")
        @Expose
        private List<DoctorSpecia> doctorSpecialist;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Integer getRegYear() {
            return regYear;
        }

        public void setRegYear(Integer regYear) {
            this.regYear = regYear;
        }

        public Integer getExperience() {
            return experience;
        }

        public void setExperience(Integer experience) {
            this.experience = experience;
        }

        public Object getAssociatedHospital() {
            return associatedHospital;
        }

        public void setAssociatedHospital(Object associatedHospital) {
            this.associatedHospital = associatedHospital;
        }

        public Object getDegree() {
            return degree;
        }

        public void setDegree(Object degree) {
            this.degree = degree;
        }

        public Integer getFee() {
            return fee;
        }

        public void setFee(Integer fee) {
            this.fee = fee;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Boolean getVerify() {
            return verify;
        }

        public void setVerify(Boolean verify) {
            this.verify = verify;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
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

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public List<DoctorSpecialization> getDoctorSpecialization() {
            return doctorSpecialization;
        }

        public void setDoctorSpecialization(List<DoctorSpecialization> doctorSpecialization) {
            this.doctorSpecialization = doctorSpecialization;
        }

        public List<DoctorSpecia> getDoctorSpecialist() {
            return doctorSpecialist;
        }

        public void setDoctorSpecialist(List<DoctorSpecia> doctorSpecialist) {
            this.doctorSpecialist = doctorSpecialist;
        }

    }
    public class Specialist {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    public class Account {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
    public class DoctorSpecia {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("specialist")
        @Expose
        private Specialist specialist;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Specialist getSpecialist() {
            return specialist;
        }

        public void setSpecialist(Specialist specialist) {
            this.specialist = specialist;
        }

    }
    public class DoctorSpecialization {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("specialization")
        @Expose
        private Specialization specialization;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Specialization getSpecialization() {
            return specialization;
        }

        public void setSpecialization(Specialization specialization) {
            this.specialization = specialization;
        }

    }
    public class Specialization {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
