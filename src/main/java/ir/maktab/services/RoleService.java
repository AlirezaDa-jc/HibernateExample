package ir.maktab.services;

import ir.maktab.entities.Article;
import ir.maktab.entities.User;
import ir.maktab.repository.RoleRepository;
import ir.maktab.Scan;
import ir.maktab.entities.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleService {
    private static Role role  = new Role();
    private static RoleRepository repository = new RoleRepository();
    private static Scan sc = Scan.getInstance();

    public static Role getRole() {
        return role;
    }

    public static void setRole(Role role) {
        RoleService.role = role;
    }

    public static boolean checkRole() {
        String roleTitle = sc.getString("User Or Admin: ");
        roleTitle = roleTitle.toUpperCase();
        role.setRoleTitle(roleTitle);
        repository.update(role);
        if(repository.check(role)){
            System.out.println("Welcome " + role.getRoleTitle());
            return true;
        }
        System.out.println("Invalid Role Title!");

        return false;

    }

    public static void displayUsers() {
        try {
            Set<User> userSet = role.getUsers();
            for(User user : userSet){
                System.out.println(user.getName());
            }
        }catch (NullPointerException ex){
            System.out.println("No User Found");
        }
    }
}
