package fr.gamedev.question.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

//TODO grp4 by DJE : JavaDoc : il faut indiquer une description (en première ligne). Cette description doit être une phrase donc se terminer par un . (point).
/**
 *. @author djer1
 *
 */
@Entity
public class Answer {

    //TODO grp4 by DJE : JavaDoc : il faut documenter l'attribut "id".
    /**
     *.
     */
    @GeneratedValue(generator = "seq_gen_answer")
    @GenericGenerator(name = "seq_gen_answer", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_answer"),
                    @Parameter(name = "initial_value", value = "0"), @Parameter(name = "increment_size", value = "1") })
    @Id
    private long id;

    /**
    *
    */
    @OneToOne
    private Question question;
    /**
    *
    */
    private Boolean correctAnswer;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param theId the id to set
     */
    public void setId(final long theId) {
        this.id = theId;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param theQuestion the question to set
     */
    public void setQuestion(final Question theQuestion) {
        this.question = theQuestion;
    }

    /**
     * @return the correctAnswer
     */
    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param theCorrectAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(final Boolean theCorrectAnswer) {
        this.correctAnswer = theCorrectAnswer;
    }

}
