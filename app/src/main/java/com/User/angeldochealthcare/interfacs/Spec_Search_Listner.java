package com.User.angeldochealthcare.interfacs;

import com.User.angeldochealthcare.response.Spec_Search_Response;
import com.User.angeldochealthcare.response.Specialization_Response;

public interface Spec_Search_Listner {
    void onItemClickedItem(Spec_Search_Response.Result item, int position);
}
