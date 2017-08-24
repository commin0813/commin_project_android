package com.commin.pro.lecture.page;

import android.graphics.PorterDuff;

import com.commin.pro.lecture.R;
import com.commin.pro.lecture.model.Model2Course;
import com.commin.pro.lecture.model.Model2User;

import java.util.ArrayList;

/**
 * 해당 클래스는 static 자원을 관리하며,
 * 여러 액티비티에서 호출하여 사용하는 자원들을 모아두는곳입니다.
 */

public class ApplicationProperty {

    /////////////////////////////////////////////////
//  CODE
// //////////////////////////////////////////////
    public static final int REQUEST_CODE_SIGN_UP = 0x11;
    public static final int RESULT_CODE_SIGN_UP_OK = 0x12;
    public static final int RESULT_CODE_SIGN_UP_FAIL = 0x13;

    public static int REQUEST_CODE_FOR_LECTURE_EDIT = 0x14;
    public static int RESULT_LOGOUT = 0x15;

    public static int REQUEST_CODE_FOR_LECTURE_SEARCH = 0x16;


    /////////////////////////////////////////////////
//  USER
// //////////////////////////////////////////////

    /***************
     * 유저정보가 static으로 잠시 저장되어집니다. 그러니까 앱을 완전히 종료하기전까지는 계속해서
     * 유저정보를 가지고있습니다.
     */
    public static boolean isLogined = false;
    public static Model2User model2User = new Model2User();


/////////////////////////////////////////////////
//  Lecture
// //////////////////////////////////////////////

    public static ArrayList<Model2Course> LECTURE_TEMP_LIST = new ArrayList<Model2Course>();
    private static ArrayList<Model2Course> LECTURE_REGISTERED_LIST = null;

    /**********************
     * ArrayList를 여러곳에서 호출하기때문에 매번 생성하는것이 자원의 낭비라 SingleTone 패턴을 사용했습니다.
     * @return
     */
    public static synchronized ArrayList<Model2Course> getRegisteredList() {// SingleTone
        if (LECTURE_REGISTERED_LIST == null) {
            LECTURE_REGISTERED_LIST = new ArrayList<Model2Course>();
        }
        return LECTURE_REGISTERED_LIST;
    }

    public static synchronized void setRegisteredList(ArrayList<Model2Course> model) {// SingleTone
        LECTURE_REGISTERED_LIST = model;
    }


    public static String[] time_title = null;


    //////////////////////////////////////////////////
    /// Lecture Color
    /////////////////////////////////////////////////

    private static int[] Background_Colors = {
            R.color.backBackDDC, //0
            R.color.backBack84, //1
            R.color.backBackCB, //2
            R.color.backBack80, //3
            R.color.backBack83, //4
            R.color.backBack81, //5
            R.color.backBackD1, //6
            R.color.backBack85, //7
            R.color.backBackC3, //8
            R.color.backBackDD, //9
            R.color.backBackE8 //10
    };

    private static int COLOR_COUNT = -1; //min_value : 0 , max_value : 10

    public static int getBackgroundColor() {
        if (COLOR_COUNT >= 10) {
            COLOR_COUNT = 0;
        } else {
            COLOR_COUNT++; // 0~ 10 까지 순서대로 저장되게 됩니다. 그러니까 11개이상 등록하게되면 같은 색상이 있을 수 있습니다.
        }
        return Background_Colors[COLOR_COUNT];
    }

}
