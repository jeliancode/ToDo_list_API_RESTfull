package jelian.code.springdata.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserValidationService {
    private static final Pattern EMAIL = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern ALPHABETIC_TEXT = Pattern.compile(
            "^[A-Za-z]*$",
            Pattern.CASE_INSENSITIVE
    );

    // validate if email is valid
    public Boolean isValidEmail(String email) {
        return EMAIL.matcher(email).matches();
    }

    // validate if text is in (A-Z)
    public Boolean isValidText(String text) {
        return ALPHABETIC_TEXT.matcher(text).matches();
    }
}
