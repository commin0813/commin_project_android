package com.commin.pro.comminproject.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


/**
 * Created by user on 2017-08-01.
 */
public class UtilDialog {

    private final static String POSITIVE_TEXT = "확인";
    private final static String NEGATIVE_TEXT = "취소";
    public static MaterialDialog.Builder basicDialog(Activity activity,String title, String message,MaterialDialog.SingleButtonCallback callback){
        return  new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .positiveText(POSITIVE_TEXT)
                .onPositive(callback);
    }
    public static MaterialDialog.Builder confirmDialog(Activity activity,String title, String message,MaterialDialog.SingleButtonCallback callback1,MaterialDialog.SingleButtonCallback callback2){
        return  new MaterialDialog.Builder(activity)
                .title(title)
                .content(message)
                .positiveText(POSITIVE_TEXT)
                .onPositive(callback1)
                .negativeText(NEGATIVE_TEXT)
                .onNegative(callback2);
    }


}
