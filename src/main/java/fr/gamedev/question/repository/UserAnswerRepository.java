package fr.gamedev.question.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.gamedev.question.data.Answer;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;

/**
 * @author djer1
 *
 */
@RepositoryRestResource(collectionResourceRel = "userAnswer", path = "userAnswer")
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer, Long> {

    List<UserAnswer> findByAnswer(Answer answer);

    List<UserAnswer> findByUser(User user);
}
