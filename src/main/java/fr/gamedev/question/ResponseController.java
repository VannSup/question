package fr.gamedev.question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.AnswerRepository;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     *
     */
    @Autowired
    private QuestionRepository questionRepository;

    /**
    *
    */
    @Autowired
    private AnswerRepository answerRepository;

    /**
     *
     */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @GetMapping("/response")
    public final String answer(final @RequestBody long questionId, final @RequestBody Boolean answer,
            final @RequestBody long userId) {
        String response;
        UserAnswer userAnswer = new UserAnswer();
        Optional<User> user = userRepository.findById(userId);
        Optional<Question> question = questionRepository.findById(questionId);
        Optional<Answer> reponse = answerRepository.findByQuestion(question.get());
        final Integer pointValue = 10;

        userAnswer.setUser(user.get());
        userAnswer.setAnswer(reponse.get());

        if (answer == reponse.get().getCorrectAnswer()) {
            // Ajouter des points

            userAnswer.setPoints(pointValue);
            response = "Bravo ! vous avez trouvé ! ";
        } else {
            // Ne pas ajouter de points

            response = "Oops ! Ca n'est pas correcte";
        }

        userAnswerRepository.save(userAnswer);

        return response;
    }

}
