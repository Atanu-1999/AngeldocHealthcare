package com.User.angeldochealthcare.interfacs;

import com.User.angeldochealthcare.response.Specialist_Response;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;

public interface Specialist_Listner {
    void onItemClickedItem(Specialist_Response.Result item, int position);
}
