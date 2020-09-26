package ir.maktab.services;

import ir.maktab.Scan;
//import ir.maktab.entities.Role;
import ir.maktab.menu.UserMenu;
import ir.maktab.repository.UserRepository;
import ir.maktab.entities.Article;
import ir.maktab.entities.User;

import java.util.HashSet;
import java.util.Set;


public class UserService {
    private static User user = new User();
    private static UserRepository repository = new UserRepository();
    private static Scan sc = Scan.getInstance();

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserService.user = user;
    }

    public static void userSignIn() {
        String userName = sc.getString("Username: ");
        user.setName(userName);
        String nationalCode = sc.getString("National Code: ");
        user.setNationalCode(nationalCode);
        String birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        while (!birthday.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            birthday = sc.getString("Date of Birthday: (Format : YYYY-MM-DD)");
        }
        user.setBirthday(birthday);
        user.setPassword(nationalCode);
//        Role role = RoleService.getRole();
////        user.setRole(role);
////        role.addUser(user);
        repository.userSignIn(user);
    }

    public static boolean signInOrLogin() {
        String choice = sc.getString("Sign In Or Login Or Exit: ");
        switch (choice.toUpperCase()) {
            case "SIGN IN":
                userSignIn();
                return true;
            case "LOGIN":
                if (login()) {
                    return true;
                }
                break;
            case "EXIT":
                System.exit(0);
        }
        return false;
    }

    private static boolean login() {
        String userName = sc.getString("Username: ");
        String password = sc.getString("Password: ");
        return repository.userLogin(userName, password);
    }

    public static void changePassword() {
        String oldPassword = sc.getString("Enter Your Old Password: ");
        if (!oldPassword.equals(user.getPassword())) {
            System.out.println("Wrong Password!");
            return;
        }
        String newPassword = sc.getString("Enter Your New Password: ");
        String confirmedPassword = sc.getString("Enter Your New Password Again: ");
        if (newPassword.equals(confirmedPassword)) {
            user.setPassword(newPassword);
            repository.update(user);
        }
        System.out.println("Unmatched Passwords!");

    }

    public static void close() {
        repository.close();
    }

    public static void userLogin() {
        boolean checkAccount = signInOrLogin();
        while (!checkAccount) {
            checkAccount =  signInOrLogin();
        }
        new UserMenu().menuHandler();
    }

    public static void addArticles(Article article) {
        try {
            Set<Article> articleSet = user.getArticles();
            articleSet.add(article);
            user.setArticles(articleSet);
        }catch (NullPointerException ex){
            Set<Article> articleSet = new HashSet<>();
            articleSet.add(article);
            user.setArticles(articleSet);
        }
    }
}
