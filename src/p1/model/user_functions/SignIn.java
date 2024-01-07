package p1.model.user_functions;

public class SignIn {

    public static boolean authenticate(String username, String password) {
        if (doesUserExist(username)) {
            if (checkPassword(username, password))
                return true;
            else return false;
        }
        return false;
    }

    private static boolean checkPassword(String username, String password) {
        return password.equals(UserStorage.getUserStorage().get(username).getPassword());
    }

    public static boolean doesUserExist(String username) {
        return UserStorage.getUserStorage().containsKey(username);
    }
}
