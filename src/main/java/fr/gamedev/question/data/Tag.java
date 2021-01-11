
package fr.gamedev.question.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * example of value :  Java, C#, Docker, etc..
 * @author yannk
 *
 */
@Entity
public class Tag {

    /** Id use for BDD object. */
    @GeneratedValue(generator = "seq_gen_tag")
    @GenericGenerator(name = "seq_gen_tag", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "seq_tag"), @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    @Id
    private long id;

    /** Example of value :  Java, C#, Docker, etc.. */
    private String value;

    /** Category. */
    @ManyToOne
    private Category category;

    /** List of question for current tag. */
    @ManyToMany
    private List<Question> questions = new ArrayList<Question>();;

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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param theValue the skill to set
     */
    public void setSkill(final String theValue) {
        this.value = theValue;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param theCategory the category to set
     */
    public void setCategory(final Category theCategory) {
        this.category = theCategory;
    }

    /**
     * @param theValue the value to set
     */
    public void setValue(final String theValue) {
        this.value = theValue;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param theQuestions the questions to set
     */
    public void setQuestions(final List<Question> theQuestions) {
        this.questions = theQuestions;
    }

}
