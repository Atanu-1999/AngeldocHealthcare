package com.User.angeldochealthcare.interfacs;

import com.User.angeldochealthcare.response.Schedule_Response;
import com.User.angeldochealthcare.response.Spec_Search_Response;

public interface Schedule_Time_Listner {
    void onItemClickedItem(Schedule_Response.Period item, int position);
}
