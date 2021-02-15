package fr.gamedev.question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.networking.data.ResponseBody;
import fr.gamedev.question.repository.AnswerRepository;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;

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

    /** Access to user Data. */
    @Autowired
    private UserRepository userRepository;

    /** Access to question Data. */
    @Autowired
    private QuestionRepository questionRepository;

    /** Access to answer Data. */
    @Autowired
    private AnswerRepository answerRepository;

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

        Optional<User> user = userRepository.findById((long) responseBody.getUserId());
        Optional<Question> question = questionRepository.findById((long) responseBody.getQuestionId());
        Optional<Answer> reponse = answerRepository.findByQuestion(question.get());

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user.get());
        userAnswer.setAnswer(reponse.get());

        if (responseBody.getAnswer() == reponse.get().getCorrectAnswer()) {

            int pointWin = getNumberOfPointTaken(question.get(), user.get(), 0);
            userAnswer.setPoints(pointWin);
            response = String.format("Bravo ! vous avez trouvé ! \n    Point obtenu : %d.", pointWin);

        } else {
            // Ne pas ajouter de points

            userAnswer.setPoints(POINT_FOR_BAD_ANSWER);
            response = "Oops ! Ca n'est pas correcte";

        }

        userAnswerRepository.save(userAnswer);

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
