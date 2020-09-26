package ir.maktab.services;

import ir.maktab.entities.Admin;
import ir.maktab.Scan;
//import ir.maktab.entities.Role;
import ir.maktab.repository.AdminRepository;

public class AdminService {

    private static Admin admin = new Admin();
    private static AdminRepository repository = new AdminRepository();
    private static Scan sc = Scan.getInstance();

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        AdminService.admin = admin;
    }

    public static boolean adminLogin() {
        String userName = sc.getString("Username: ");
        String password = sc.getString("Password: ");
        if(repository.adminLogin(userName, password)){
            admin.setName(userName);
            admin.setPassword(password);
//            Role role = RoleService.getRole();
//            admin.setRole(role);
//            role.addAdmin(admin);
            return true;
        }
        return false;
    }

//    public static void editRole() {
//        RoleService.displayUsers();
//    }
}
