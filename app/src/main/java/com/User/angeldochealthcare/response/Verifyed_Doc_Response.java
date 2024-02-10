package com.User.angeldochealthcare.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Verifyed_Doc_Response {
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
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("reg_number")
        @Expose
        private String regNumber;
        @SerializedName("council_number")
        @Expose
        private String councilNumber;
        @SerializedName("reg_year")
        @Expose
        private Integer regYear;
        @SerializedName("experience")
        @Expose
        private Integer experience;
        @SerializedName("reg_type")
        @Expose
        private String regType;
        @SerializedName("about")
        @Expose
        private Object about;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("profile")
        @Expose
        private Object profile;
        @SerializedName("profileName")
        @Expose
        private Object profileName;
        @SerializedName("signature")
        @Expose
        private Object signature;
        @SerializedName("signatureName")
        @Expose
        private Object signatureName;
        @SerializedName("expertise")
        @Expose
        private Object expertise;
        @SerializedName("associated_hospital")
        @Expose
        private Object associatedHospital;
        @SerializedName("degree_hospital")
        @Expose
        private Object degreeHospital;
        @SerializedName("degree")
        @Expose
        private Object degree;
        @SerializedName("e_medical")
        @Expose
        private Boolean eMedical;
        @SerializedName("telemedicine_fee")
        @Expose
        private Integer telemedicineFee;
        @SerializedName("in_persion_fee")
        @Expose
        private Integer inPersionFee;
        @SerializedName("e_board_fee")
        @Expose
        private Integer eBoardFee;
        @SerializedName("provide_telemedicine")
        @Expose
        private Boolean provideTelemedicine;
        @SerializedName("provide_in_persion")
        @Expose
        private Boolean provideInPersion;
        @SerializedName("provide_live_doctor")
        @Expose
        private Boolean provideLiveDoctor;
        @SerializedName("live_doctor_fee")
        @Expose
        private Integer liveDoctorFee;
        @SerializedName("expert_for_assessment")
        @Expose
        private Boolean expertForAssessment;
        @SerializedName("max_free_followup_days")
        @Expose
        private Integer maxFreeFollowupDays;
        @SerializedName("max_patient_limit")
        @Expose
        private Integer maxPatientLimit;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("verify")
        @Expose
        private Boolean verify;
        @SerializedName("accountId")
        @Expose
        private Integer accountId;
        @SerializedName("updatedBy")
        @Expose
        private String updatedBy;
        @SerializedName("stateId")
        @Expose
        private Integer stateId;
        @SerializedName("countryId")
        @Expose
        private Object countryId;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("account")
        @Expose
        private Account account;
        @SerializedName("doctorSchedule")
        @Expose
        private List<Object> doctorSchedule;
        @SerializedName("doctorSpecialization")
        @Expose
        private List<DoctorSpecialization> doctorSpecialization;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getRegNumber() {
            return regNumber;
        }

        public void setRegNumber(String regNumber) {
            this.regNumber = regNumber;
        }

        public String getCouncilNumber() {
            return councilNumber;
        }

        public void setCouncilNumber(String councilNumber) {
            this.councilNumber = councilNumber;
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

        public String getRegType() {
            return regType;
        }

        public void setRegType(String regType) {
            this.regType = regType;
        }

        public Object getAbout() {
            return about;
        }

        public void setAbout(Object about) {
            this.about = about;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public Object getSignatureName() {
            return signatureName;
        }

        public void setSignatureName(Object signatureName) {
            this.signatureName = signatureName;
        }

        public Object getExpertise() {
            return expertise;
        }

        public void setExpertise(Object expertise) {
            this.expertise = expertise;
        }

        public Object getAssociatedHospital() {
            return associatedHospital;
        }

        public void setAssociatedHospital(Object associatedHospital) {
            this.associatedHospital = associatedHospital;
        }

        public Object getDegreeHospital() {
            return degreeHospital;
        }

        public void setDegreeHospital(Object degreeHospital) {
            this.degreeHospital = degreeHospital;
        }

        public Object getDegree() {
            return degree;
        }

        public void setDegree(Object degree) {
            this.degree = degree;
        }

        public Boolean geteMedical() {
            return eMedical;
        }

        public void seteMedical(Boolean eMedical) {
            this.eMedical = eMedical;
        }

        public Integer getTelemedicineFee() {
            return telemedicineFee;
        }

        public void setTelemedicineFee(Integer telemedicineFee) {
            this.telemedicineFee = telemedicineFee;
        }

        public Integer getInPersionFee() {
            return inPersionFee;
        }

        public void setInPersionFee(Integer inPersionFee) {
            this.inPersionFee = inPersionFee;
        }

        public Integer geteBoardFee() {
            return eBoardFee;
        }

        public void seteBoardFee(Integer eBoardFee) {
            this.eBoardFee = eBoardFee;
        }

        public Boolean getProvideTelemedicine() {
            return provideTelemedicine;
        }

        public void setProvideTelemedicine(Boolean provideTelemedicine) {
            this.provideTelemedicine = provideTelemedicine;
        }

        public Boolean getProvideInPersion() {
            return provideInPersion;
        }

        public void setProvideInPersion(Boolean provideInPersion) {
            this.provideInPersion = provideInPersion;
        }

        public Boolean getProvideLiveDoctor() {
            return provideLiveDoctor;
        }

        public void setProvideLiveDoctor(Boolean provideLiveDoctor) {
            this.provideLiveDoctor = provideLiveDoctor;
        }

        public Integer getLiveDoctorFee() {
            return liveDoctorFee;
        }

        public void setLiveDoctorFee(Integer liveDoctorFee) {
            this.liveDoctorFee = liveDoctorFee;
        }

        public Boolean getExpertForAssessment() {
            return expertForAssessment;
        }

        public void setExpertForAssessment(Boolean expertForAssessment) {
            this.expertForAssessment = expertForAssessment;
        }

        public Integer getMaxFreeFollowupDays() {
            return maxFreeFollowupDays;
        }

        public void setMaxFreeFollowupDays(Integer maxFreeFollowupDays) {
            this.maxFreeFollowupDays = maxFreeFollowupDays;
        }

        public Integer getMaxPatientLimit() {
            return maxPatientLimit;
        }

        public void setMaxPatientLimit(Integer maxPatientLimit) {
            this.maxPatientLimit = maxPatientLimit;
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

        public Integer getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        public Object getCountryId() {
            return countryId;
        }

        public void setCountryId(Object countryId) {
            this.countryId = countryId;
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

        public List<Object> getDoctorSchedule() {
            return doctorSchedule;
        }

        public void setDoctorSchedule(List<Object> doctorSchedule) {
            this.doctorSchedule = doctorSchedule;
        }

        public List<DoctorSpecialization> getDoctorSpecialization() {
            return doctorSpecialization;
        }

        public void setDoctorSpecialization(List<DoctorSpecialization> doctorSpecialization) {
            this.doctorSpecialization = doctorSpecialization;
        }

    }

    public class Specialization {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("imageName")
        @Expose
        private String imageName;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("accountId")
        @Expose
        private Integer accountId;
        @SerializedName("updatedBy")
        @Expose
        private String updatedBy;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
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

        public Integer getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

    }

    public class Account {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("designation")
        @Expose
        private Object designation;
        @SerializedName("password")
        @Expose
        private Object password;
        @SerializedName("fcm_token")
        @Expose
        private String fcmToken;
        @SerializedName("session_id")
        @Expose
        private Object sessionId;
        @SerializedName("whatsapp")
        @Expose
        private Boolean whatsapp;
        @SerializedName("tnc")
        @Expose
        private Boolean tnc;
        @SerializedName("createdBy")
        @Expose
        private Object createdBy;
        @SerializedName("updatedBy")
        @Expose
        private Object updatedBy;
        @SerializedName("roles")
        @Expose
        private String roles;
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

        public Object getDesignation() {
            return designation;
        }

        public void setDesignation(Object designation) {
            this.designation = designation;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public Object getSessionId() {
            return sessionId;
        }

        public void setSessionId(Object sessionId) {
            this.sessionId = sessionId;
        }

        public Boolean getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(Boolean whatsapp) {
            this.whatsapp = whatsapp;
        }

        public Boolean getTnc() {
            return tnc;
        }

        public void setTnc(Boolean tnc) {
            this.tnc = tnc;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
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

    public class DoctorSpecialization {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("specializationId")
        @Expose
        private Integer specializationId;
        @SerializedName("doctorDetailId")
        @Expose
        private Integer doctorDetailId;
        @SerializedName("specialization")
        @Expose
        private Specialization specialization;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSpecializationId() {
            return specializationId;
        }

        public void setSpecializationId(Integer specializationId) {
            this.specializationId = specializationId;
        }

        public Integer getDoctorDetailId() {
            return doctorDetailId;
        }

        public void setDoctorDetailId(Integer doctorDetailId) {
            this.doctorDetailId = doctorDetailId;
        }

        public Specialization getSpecialization() {
            return specialization;
        }

        public void setSpecialization(Specialization specialization) {
            this.specialization = specialization;
        }

    }
}
