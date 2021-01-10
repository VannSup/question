//TODO grp4 by DJE : JavaDoc : CheckStyle indique un espace "invisible" à éviter.
//TODO grp4 by DJE : JavaDoc : le template de "fichier source" cotient par defaut se bloc Javadoc qui sert à documenter le "package". Il est maintenant recommandé d'utiliser le package-info.java, se bloc de JavaDoc peu donc être totalement supprimé.
/**
 *.
 */

package fr.gamedev.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author djer1
 *
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@SpringBootApplication
public class Launcher {

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

}
