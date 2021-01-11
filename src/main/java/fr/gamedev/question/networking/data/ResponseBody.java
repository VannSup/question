package fr.gamedev.question.networking.data;

/**
 * ResponseBody it is a objet use for API response.
 * example d'appel post man sur l'url : http://localhost:8080/response.
 * en POST
 * body en raw JSON
 * {
 *    "questionId": 0,
 *     "answer": true,
 *     "userId": 0
 * }
 * not use for BDD
 * @author yannk
 *
 */
public class ResponseBody {

    /** Id of question. */
    private int questionId;

    /** Id of user. */
    private int userId;

    /** Value of response. */
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
