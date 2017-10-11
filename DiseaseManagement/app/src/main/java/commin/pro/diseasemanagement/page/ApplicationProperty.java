package commin.pro.diseasemanagement.page;

/**
 * Created by user on 2017-10-10.
 */

public class ApplicationProperty {
    /*********************
     * COMPLETE INTENT KEY VALUE
     */
    public static String STRING_KEY_VALUE_RESULT = "result";
    public static String STRING_KEY_VALUE_RESULT_DETAIL = "result_detail";
    public static String STRING_KEY_VALUE_EXPLAIN = "explain";
    public static String STRING_KEY_VALUE_BUTTON_TYPE = "btn_type";

    /*********************
     * COMPLETE INTENT VALUE
     */
    //HIGH BLOOD
    public static String STRING_VALUE_COMPLETE_RESULT_NORMAL = "정상입니다.";
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_HIGH_1 = "고혈압이 의심됩니다!";
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_HIGH_2 = "고혈압입니다!";

    //DIABETES
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_DIABETES_1 = "당뇨가 의심됩니다!";
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_DIABETES_2 = "당뇨 입니다!";
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_DIABETES_DETAIL = "생활 습관 교정 등을 통해 혈당 수취를 낮춰야 합니다.";

    //GASTRITIS
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_GASTRITIS_1 = "위염이 의심됩니다!";
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_GASTRITIS_2 = "위염 입니다!";
    public static String STRING_VALUE_COMPLETE_RESULT_ABNORMAL_GASTRITIS_DETAIL = "식습관, 생활습관을 바꿔야 합니다.";



    public static String STRING_VALUE_COMPLETE_EXPLAIN_NORMAL = "이대로 쭉~ 유지하세요";
    public static String STRING_VALUE_COMPLETE_EXPLAIN_ABNORMAL = "화살표를 눌러 질병관리 방법을 보세요.";

    public static int INTEGER_VALUE_COMPLETE_BUTTON_TYPE_NORMAL = 0x01;
    public static int INTEGER_VALUE_COMPLETE_BUTTON_TYPE_ARROW = 0x02;


}
