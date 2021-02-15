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

    /** Id of user answer. */
    private long userAnswerId;

    /** Value of response. */
    private Boolean answer;

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

    /**
     * @return the userAnswerId
     */
    public long getUserAnswerId() {
        return userAnswerId;
    }

    /**
     * @param theUserAnswerId the userAnswerId to set.
     */
    public void setUserAnswerId(final long theUserAnswerId) {
        this.userAnswerId = theUserAnswerId;
    }

}
