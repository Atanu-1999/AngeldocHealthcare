package com.User.angeldochealthcare.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule_Response {
        @SerializedName("result")
        @Expose
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    public class Period {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("periodStatus")
        @Expose
        private Boolean periodStatus;
        @SerializedName("time_start")
        @Expose
        private String timeStart;
        @SerializedName("time_end")
        @Expose
        private String timeEnd;

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

        public Boolean getPeriodStatus() {
            return periodStatus;
        }

        public void setPeriodStatus(Boolean periodStatus) {
            this.periodStatus = periodStatus;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

    }
    public class Result {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("mode")
        @Expose
        private String mode;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("period")
        @Expose
        private List<Period> period;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Period> getPeriod() {
            return period;
        }

        public void setPeriod(List<Period> period) {
            this.period = period;
        }

    }
}
