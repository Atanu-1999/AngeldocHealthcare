package com.User.angeldochealthcare.interfacs;

import com.User.angeldochealthcare.response.Specialization_Response;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;

public interface Verify_Doc_Listner {
    void onItemClickedItem(Verifyed_Doc_Response.Result item, int position);
}
