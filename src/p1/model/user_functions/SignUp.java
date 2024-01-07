package p1.model.user_functions;

public class SignUp {

    public static boolean signUp(String username, String password) {
        if (hasValidInput(username, password)) {
            UserStorage.getUserStorage().put(username, new User(username, password));
            return true;
        }
        else return false;
    }

    private static boolean hasValidInput(String username, String password) {
        if (checkForExistingUsername(username))
            return false;
        if (!checkPasswordRequirements(password))
            return false;
        return true;
    }

    private static boolean checkPasswordRequirements(String password) {
        if (password.length() < 6)
            return false;
        var hasUpperCase = false;
        var hasLowerCase = false;
        var hasDigit = false;
        for (var c : password.toCharArray()) {
            if (hasUpperCase && hasLowerCase && hasDigit)
                return true;
            if (isUpperCase(c))
                hasUpperCase = true;
            if (isLowerCase(c))
                hasLowerCase = true;
            if (isDigit(c))
                hasDigit = true;
        }
        if (hasUpperCase && hasLowerCase && hasDigit)
            return true;
        return false;
    }

    public static boolean isInRange(char c, char lowerBound, char upperBound) {
        if (c < lowerBound || c > upperBound)
            return false;
        return true;
    }

    private static boolean isDigit(char c) {
        return isInRange(c, '0', '9');
    }

    private static boolean isLowerCase(char c) {
        return isInRange(c, 'a', 'z');
    }

    private static boolean isUpperCase(char c) {
        return isInRange(c, 'A', 'Z');
    }

    private static boolean checkForExistingUsername(String username) {
        return UserStorage.getUserStorage().containsKey(username);
    }
}
