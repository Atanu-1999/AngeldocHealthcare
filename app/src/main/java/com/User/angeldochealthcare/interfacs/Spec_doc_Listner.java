package com.User.angeldochealthcare.interfacs;

import com.User.angeldochealthcare.response.Id_Specialization_Response;
import com.User.angeldochealthcare.response.Specialization_Response;

public interface Spec_doc_Listner {
    void onItemClickedItem(Id_Specialization_Response.Result item, int position);
}
