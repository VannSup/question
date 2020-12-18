package fr.gamedev.question.data;

/**
 * @author yannk
 *
 */
public class ResponseBody {
    /**
     *
     */
    private int questionId;
    /**
    *
    */
    private int userId;
    /**
    *
    */
    private Boolean answer;

    /**
     * @return the questionId
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * @param theQuestionId the questionId to set
     */
    public void setQuestionId(final int theQuestionId) {
        this.questionId = theQuestionId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param theUserId the userId to set
     */
    public void setUserId(final int theUserId) {
        this.userId = theUserId;
    }

    /**
     * @return the answer
     */
    public Boolean getAnswer() {
        return answer;
    }

    /**
     * @param theAnswer the answer to set
     */
    public void setAnswer(final Boolean theAnswer) {
        this.answer = theAnswer;
    }

}
