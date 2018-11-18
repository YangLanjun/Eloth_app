package cn.yanglj65.www.ecloth_app.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlterUtil {
    public static void makeAlter(Context context,String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("确定", positiveClick);
        AlertDialog checkDialog = alertDialogBuilder.create();
        checkDialog.show();
    }

    private static DialogInterface.OnClickListener positiveClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface arg0, int arg1) {
            arg0.cancel();
        }
    };
}
