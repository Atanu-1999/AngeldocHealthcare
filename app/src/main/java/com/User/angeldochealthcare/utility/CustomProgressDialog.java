package com.User.angeldochealthcare.utility;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.User.angeldochealthcare.R;

public class CustomProgressDialog extends Dialog {
    private CircleProgressBar progressBar;
    private TextView progressText;

    public CustomProgressDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_dialog);

        progressBar = findViewById(R.id.pbProgress);
        //progressText = findViewById(R.id.tvMessage);


        // Set properties for the dialog
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
    public void setMessage(String message) {
        progressText.setText(message);
    }

    public void showProgressDialog() {
        if (!isShowing()) {
            show();
        }
    }

    public void hideProgressDialog() {
        if (isShowing()) {
            dismiss();
        }
    }
}
