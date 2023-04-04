package security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class Validaciones {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /*
    Explicacion de VALID_PASSWORD_REGEX:
    
    ^           - Inicio de la cadena
    (?=.*[0-9]) - Debe haber minimo un digito
    (?=.*[a-z]) - Debe haber minimo una leta minuscula
    (?=.*[A-Z]) - Debe haber minimo una letra mayuscula
    (?=\S+$)    - No debe haber espacios en blanco
    .{8,}       - Minimo 8 caracteres
    $           - Final de la cadena
    
    Fuente: https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation/3802238
     */
    public static final Pattern VALID_PASSWORD_REGEX
            = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");

    /*
    VALID_PHONE_NUMBER permite telefonos con lada internacional. Permite numeros de la siguiente forma:
    
    1) (XXX) XXX-XXXX
    2) XXX-XXX-XXXX 
    3) XXX-XXXXXXX
    4) XXXXXXXXXX
    5) (+XXX) 1, 2, 3 o 4
    6) (+XX) 1, 2, 3 o 4
    7) Varaciones sin parentesis de 5 y 6
     */
    public static final Pattern VALID_PHONE_REGEX
            = Pattern.compile("(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(String passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    public static boolean validatePhone(String phoneStr) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phoneStr);
        return matcher.find();
    }

}
