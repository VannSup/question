/**
 * 
 */
package fr.gamedev.question.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import fr.gamedev.question.data.enumeration.Domaine;
import fr.gamedev.question.data.enumeration.Skill;

/**
 * @author yannk
 *
 */
@Entity
public class Ressource {

    @Id
    private long id;

    private Skill skill;

    private Domaine domaine;

    private List<Question> questions;

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
     * @return the skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * @param theSkill the skill to set
     */
    public void setSkill(Skill theSkill) {
        this.skill = theSkill;
    }

    /**
     * @return the domaine
     */
    public Domaine getDomaine() {
        return domaine;
    }

    /**
     * @param theDomaine the domaine to set
     */
    public void setDomaine(Domaine theDomaine) {
        this.domaine = theDomaine;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
