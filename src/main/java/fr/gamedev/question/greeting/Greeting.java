/**
 *
 */
package fr.gamedev.question.greeting;

/**
 * @author djer1
 *
 */
public class Greeting {

    /**
    *
    */
    private final long id;
    /**
    *
    */
    private final String content;

    public Greeting(final long theId, final String theContent) {
        this.id = theId;
        this.content = theContent;
    }

    public final long getId() {
        return id;
    }

    public final String getContent() {
        return content;
    }

}
