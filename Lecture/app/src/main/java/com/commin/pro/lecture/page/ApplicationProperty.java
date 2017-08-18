package com.commin.pro.lecture.page;

import android.graphics.PorterDuff;

import com.commin.pro.lecture.model.Model2User;

/**
 * Created by K on 2017-08-13.
 */

public class ApplicationProperty {

/////////////////////////////////////////////////
//  CODE
// //////////////////////////////////////////////
    public static final int REQUEST_CODE_SIGN_UP= 0x11;
    public static final int RESULT_CODE_SIGN_UP_OK= 0x12;
    public static final int RESULT_CODE_SIGN_UP_FAIL= 0x13;

    public static int REQUEST_CODE_FOR_LECTURE_EDIT = 0x14;
    public static int RESULT_LOGOUT = 0x15;

    public static int REQUEST_CODE_FOR_LECTURE_SEARCH = 0x16;



/////////////////////////////////////////////////
//  USER
// //////////////////////////////////////////////
    public static boolean isLogined = false;
    public static Model2User model2User = new Model2User();

}
