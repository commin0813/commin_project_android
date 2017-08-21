package com.commin.pro.lecture.page;

import android.graphics.PorterDuff;

import com.commin.pro.lecture.model.Model2Course;
import com.commin.pro.lecture.model.Model2User;

import java.util.ArrayList;

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


/////////////////////////////////////////////////
//  Lecture
// //////////////////////////////////////////////

    public static ArrayList<Model2Course> LECTURE_TEMP_LIST = new ArrayList<Model2Course>();
    private static ArrayList<Model2Course> LECTURE_REGISTERED_LIST = null;

    public static synchronized ArrayList<Model2Course> getRegisteredList (){// SingleTone
        if(LECTURE_REGISTERED_LIST == null){
            LECTURE_REGISTERED_LIST = new ArrayList<Model2Course>();
        }
        return LECTURE_REGISTERED_LIST;
    }
    public static synchronized void setRegisteredList (ArrayList<Model2Course> model){// SingleTone
            LECTURE_REGISTERED_LIST = model;
    }


    public static String [] time_title = null;

}
