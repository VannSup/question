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
        UserAnswer userAnswer = new UserAnswer();
        Optional<User> user = userRepository.findById((long) responseBody.getUserId());
        Optional<Question> question = questionRepository.findById((long) responseBody.getQuestionId());
        Optional<Answer> reponse = answerRepository.findByQuestion(question.get());
        final Integer pointValue = 10;

        userAnswer.setUser(user.get());
        userAnswer.setAnswer(reponse.get());

        if (responseBody.getAnswer() == reponse.get().getCorrectAnswer()) {
            // Ajouter des points

            userAnswer.setPoints(pointValue);
            response = "Bravo ! vous avez trouvé ! ";
        } else {
            // Ne pas ajouter de points
            /*grp4 by DJE : Algo :
             *  Sais-tu pourquoi il y a "0" dans la BDD alors que tu ne défini par
             *  explicitement le nombre de points lorsque l'utilisateur se trompe ?
             *
             *  Je suppose que c'est lié au GenericGenerator
             *  ici nous precisont pas de valeur donc par défaut il sera initialiser a un Long = 0
             *  (car c'est un argument non nullable)*/
            response = "Oops ! Ca n'est pas correcte";
        }

        userAnswerRepository.save(userAnswer);

        return response;
    }
}
