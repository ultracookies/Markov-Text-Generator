package p1.model;

import p1.model.user_functions.User;

public class CurrentUser {
    private static User currentUser;

    private CurrentUser() {}

    public static void setCurrentUser(User u) {
        currentUser = u;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
