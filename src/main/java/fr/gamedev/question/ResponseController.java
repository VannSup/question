package fr.gamedev.question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.networking.data.ResponseBody;
import fr.gamedev.question.repository.UserAnswerRepository;

/**
 * Controller for response.
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    /** Les point max a attribué si juste + pas deja rep + question lui est envoyer.*/
    private static final int POINT_FOR_CORRECT_ANSWER = 10;

    /** Les point a attribué si l'utilisateur répond faux. */
    private static final int POINT_FOR_BAD_ANSWER = 0;

    /** Access to user answer Data. */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    /**
     * example d'appel post man sur l'url : http://localhost:8080/response.
     * en POST
     * body en raw JSON
     * {
            "questionId": 0,
            "answer": true,
            "userId": 0
        }
     * @param responseBody
     * @return resultas
     */
    @RequestMapping(path = "/response", method = RequestMethod.POST)
    public final String answer(final @RequestBody ResponseBody responseBody) {
        String response;

        Optional<UserAnswer> askedAnswer = userAnswerRepository.findById(responseBody.getUserAnswerId());

        Assert.isTrue(askedAnswer.isPresent(), "Réponse ignorée : la question ne vous à pas été posée !");
        Assert.isTrue(askedAnswer.get().getPoints() == null,
                "Réponse ignorée : vous avez déja répondu à cette question.");

        Answer reponse = askedAnswer.get().getAnswer();

        if (responseBody.getAnswer() == reponse.getCorrectAnswer()) {
            int pointWin = getNumberOfPointTaken(askedAnswer.get().getAnswer().getQuestion(),
                    askedAnswer.get().getUser(), 0);
            askedAnswer.get().setPoints(pointWin);

            response = String.format("Bravo ! vous avez trouvé ! \n    Point obtenu : %d.", pointWin);
        } else {
            askedAnswer.get().setPoints(POINT_FOR_BAD_ANSWER);

            response = "Oops ! Ca n'est pas correcte";
        }

        return response;
    }

    /**
     * Calcul le nombre de point que l'utilisateur obtiendra.
     * @param question
     * @param user
     * @param greaterThan
     * @return result
     */
    private int getNumberOfPointTaken(final Question question, final User user, final int greaterThan) {
        int result = POINT_FOR_CORRECT_ANSWER;
        Optional<UserAnswer> previousUserAnswer = userAnswerRepository
                .findTopByAnswerQuestionAndUserAndPointsNotNullAndPointsIsGreaterThanOrderByPoints(question, user,
                        greaterThan);

        if (previousUserAnswer.isPresent()) {
            int lastEarnedPoints = previousUserAnswer.get().getPoints();
            result = lastEarnedPoints / 2;
        }

        return result;
    }
}
