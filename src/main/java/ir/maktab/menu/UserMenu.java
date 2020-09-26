package ir.maktab.menu;

import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.base.Menu;
import ir.maktab.services.ArticleService;
import ir.maktab.services.CategoryService;
import ir.maktab.services.UserService;

public class UserMenu extends Menu {
    private Scan sc = null;

    public UserMenu() {
        sc = MainApp.getSc();
    }

    @Override
    public void display(){
        System.out.println("See Your Articles Press 1");
        System.out.println("Edit Your Articles Press 2");
        System.out.println("Insert an Article Press 3");
        System.out.println("Edit Password Press 4");
        System.out.println("See All of Articles Press 5");
        System.out.println("Delete Your Articles Press 6");
        System.out.println("Log Out Press 7");
    }
    //Maven Problem !
    public void setOption(Scan sc){
        while(true) {
            try {
                option = Integer.parseInt(sc.getString("Enter a Number: "));
                super.setOption(option);
                break;
            } catch (NumberFormatException e) {
                e.getMessage();
            }
        }
    }

    public int getOption(){
        return super.getOption();
    }

    @Override
    public void menuHandler() {
        boolean flag = true;
        while (flag) {
            display();
            setOption(sc);
            int option = getOption();
            switch (option) {
                case 1:
                    ArticleService.displayAUserArticle();
                    break;
                case 2:
                    ArticleService.updateUser();
                    break;
                case 3:
                    ArticleService.add();
                    break;
                case 4:
                    UserService.changePassword();
                    break;
                case 5:
                    ArticleService.displayAll();
                    if (checkChoice()){
                        ArticleService.displayAnArticle();
                    }
                    break;
                case 6:
                    ArticleService.deleteUser();
                    break;
                case 7:
                    flag = false;
                    UserService.close();
                    ArticleService.close();
                    CategoryService.close();
                    break;
                default:
                    System.out.println("Invalid Number");
            }
        }
    }

    @Override
    public boolean checkChoice() {
        char choice = sc.getString("Do You Want To See Details Of an Article?: ").toUpperCase().charAt(0);
        return choice == 'Y';
    }
}
