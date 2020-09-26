package ir.maktab;

//import ir.maktab.entities.Role;
import ir.maktab.menu.AdminMenu;
import ir.maktab.menu.UserMenu;
import ir.maktab.services.AdminService;
//import ir.maktab.services.RoleService;
import ir.maktab.services.UserService;

public class MainApp {

    private static Scan sc = Scan.getInstance();

    public static Scan getSc() {
        return sc;
    }

    public static void main(String[] args) {
//            checkRole();
            UserService.userLogin();
    }

//    private static void checkRole() {
//        if(RoleService.checkRole()) {
//            Role role = RoleService.getRole();
//            switch (role.getRoleTitle()) {
//                case "USER":
//                    UserService.userLogin();
//                    new UserMenu().menuHandler();
//                    break;
//                case "ADMIN":
//                    if(AdminService.adminLogin()) {
//                        new AdminMenu().menuHandler();
//                    }
//                    break;
//            }
//        }
//    }




}
