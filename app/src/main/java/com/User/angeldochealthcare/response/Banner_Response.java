package com.User.angeldochealthcare.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class Banner_Response {
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
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("title")
        @Expose
        private Object title;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("createdAr")
        @Expose
        private String createdAr;
        @SerializedName("updatedAr")
        @Expose
        private String updatedAr;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getCreatedAr() {
            return createdAr;
        }

        public void setCreatedAr(String createdAr) {
            this.createdAr = createdAr;
        }

        public String getUpdatedAr() {
            return updatedAr;
        }

        public void setUpdatedAr(String updatedAr) {
            this.updatedAr = updatedAr;
        }

    }
}
