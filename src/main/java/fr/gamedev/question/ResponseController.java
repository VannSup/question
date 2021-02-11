package fr.gamedev.question;

import java.util.List;
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
    private final int pointTaken = 10;

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
        String informationComplement = "";
        UserAnswer userAnswer = new UserAnswer();
        Optional<User> user = userRepository.findById((long) responseBody.getUserId());
        Optional<Question> question = questionRepository.findById((long) responseBody.getQuestionId());
        Optional<Answer> reponse = answerRepository.findByQuestion(question.get());
        List<UserAnswer> previousResponse = userAnswerRepository.findByAnswer(reponse.get());
        int pointValue = pointTaken;

        // Ajouter des points
        if (!previousResponse.isEmpty()) {
            int nbReponsePrecedent = 0;
            for (UserAnswer it : previousResponse) {
                if (it.getUser().getId() == user.get().getId()) {
                    nbReponsePrecedent++;
                }
            }
            if (nbReponsePrecedent > 0) {
                pointValue = pointValue / (nbReponsePrecedent + 1);
                informationComplement = String.format(
                        "Information complémentaire :\n" + "Vous avez déjà répondu %d fois à cette question,"
                                + " c'est pour cette raison que le nombre de point obtenue n'est pas %d mais %d .",
                        nbReponsePrecedent, pointTaken, pointValue);
            }
        }

        userAnswer.setUser(user.get());
        userAnswer.setAnswer(reponse.get());

        if (responseBody.getAnswer() == reponse.get().getCorrectAnswer()) {

            userAnswer.setPoints(pointValue);
            response = String.format("Bravo ! vous avez trouvé ! point obtenu : %d \n %s", pointValue,
                    informationComplement);
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
