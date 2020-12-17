package fr.gamedev.question;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    @GetMapping("/response")
    public final String answer(final @RequestParam long questionId, final @RequestParam Boolean answer,
            final @RequestParam long userId) {
        String response;

        if (answer == Boolean.TRUE) {
            // Ajouter des points

            response = "Bravo ! vous avez trouvé ! ";
        } else {
            // Ne pas ajouter de points

            response = "Oops ! Ca n'est pas correcte";
        }

        return response;
    }

}
