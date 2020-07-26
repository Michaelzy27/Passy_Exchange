package com.michael.hng_board.Utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Helper {
    ProgressDialog progressDialog;
    Context context;

    public Helper(Context context){
        this.context = context;
    }

    public void progressDialogStart(String titleMessage, String detailMessage){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(titleMessage);
        progressDialog.setMessage(detailMessage);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(true);
    }

    public void progressDialogEnd(){
        progressDialog.dismiss();
    }
}
