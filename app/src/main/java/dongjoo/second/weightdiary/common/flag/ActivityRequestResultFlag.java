package dongjoo.second.weightdiary.common.flag;

public class ActivityRequestResultFlag {

    /**
     *  request
     *
     *  액티비티 요청 코드
     *
     *  10010 - dialog
     *
     */

    public static final int CONFIRM_CANCEL_DIALOG_HISTORY_DELETE_REQUEST = 10011;

    public static final int CONFIRM_CANCEL_DIALOG_APP_DESTROY_REQUEST = 10020;




    /**
     * result
     * <p>
     * 액티비티 결과 코드
     */
    public static final int RESULT_OK = -1;
    public static final int RESULT_CANCEL = 0;
    public static final int RESULT_EDIT = 3;
    public static final int RESULT_DELETE = 4;


}
