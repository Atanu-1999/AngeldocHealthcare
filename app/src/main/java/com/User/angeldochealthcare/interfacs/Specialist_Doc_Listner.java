package com.User.angeldochealthcare.interfacs;

import com.User.angeldochealthcare.response.Specialist_Doctor_Response;
import com.User.angeldochealthcare.response.Specialist_Response;

public interface Specialist_Doc_Listner {
    void onItemClickedItem(Specialist_Doctor_Response.Result item, int position);
}
