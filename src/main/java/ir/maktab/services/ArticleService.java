package ir.maktab.services;

import ir.maktab.*;
import ir.maktab.entities.Article;
import ir.maktab.entities.Category;
import ir.maktab.entities.User;
import ir.maktab.repository.ArticleRepository;

public class ArticleService {

    private static Article article = new Article();
    private static ArticleRepository repository = new ArticleRepository();
    private static Scan sc = MainApp.getSc();

    public static void add() {
        String title = sc.getString("Title: ");
        article.setTitle(title);
        String brief = getText("Brief (Enter :ESC: To Finish) : ");
        article.setBrief(brief);
        String content = getText("Content: (Enter :ESC: To Finish) : ");
        article.setContent(content);
        String createDate = sc.getString("Create Date: (Format : YYYY-MM-DD): ");
        while (!createDate.matches("[0-9]{4}-[0-3][0-9]-[0-3][0-9]")) {
            createDate = sc.getString("Create Date: (Format : YYYY-MM-DD): ");
        }
        article.setCreateDate(createDate);
        article.setPublished(false);
        CategoryService.display();
        Category category = CategoryService.use();
        article.setCategory(category);
        User user = UserService.getUser();
        article.setUser(user);
        if (category == null || user == null) {
            System.out.println("Invalid Category Or User Usage");
            return;
        }
        UserService.addArticles(article);
        repository.add(article);
    }

    public static void displayAUserArticle() {
        repository.displayAUserArticle();
    }

    private static String getText(String s) {
        System.out.print(s);
        StringBuilder text = new StringBuilder();
        while (true) {
            String temp = sc.getString();
            if (temp.equals("ESC")) {
                break;
            }
            text.append(temp).append("\n");
        }
        return text.toString();
    }

    public static void updateUser() {
        displayAUserArticle();
        int id = Integer.parseInt(sc.getString("ID of Article You Want to Edit: "));
        String update = sc.getString("What You Want To Edit: (Title/Brief/Content/CreateDate/" +
                "Category): ");
        Article article = repository.getArticle(id);
        switch (update.toLowerCase()) {
            case "title":
                updateTitle(article);
                break;
            case "brief":
                updateBrief(article);
                break;
            case "content":
                updateContent(article);
                break;
            case "createdate":
                updateCreateDate(article);
                break;
            case "category":
                if (updateCategory(article)) {
                    break;
                }
            default:
                System.out.println("Invalid Input");
        }
    }

    public static void deleteUser() {

        displayAUserArticle();
        int id = Integer.parseInt(sc.getString("ID of Article You Want to Delete: "));
        User user = UserService.getUser();
        if (repository.deleteArticleUser(id,user)>0) {
            System.out.println("Deleted Successfully");
        } else {
            System.out.println("Invalid ID");
        }

    }

    /*
    In Persistence we used Update then It's Not Necessary to Use ExecuteUpdate Here !
    */

    private static boolean updateCategory(Article article) {
        Category category = CategoryService.use();
        if (category == null) {
            System.out.println("Invalid Usage Of Category");
            return false;
        }
        article.setCategory(category);
        repository.update(article);
        return true;
    }


    private static void updateCreateDate(Article article) {
        String createDate = sc.getString("New Create Date: (Format : YYYY-MM-DD): ");
        article.setCreateDate(createDate);
        repository.update(article);

    }

    private static void updateContent(Article article) {
        String content = getText("New Content: (Enter :ESC: To Finish): ");
        article.setContent(content);
        repository.update(article);

    }

    private static void updateBrief(Article article) {
        String brief = getText("New Brief: (Enter :ESC: To Finish): ");
        article.setBrief(brief);
        repository.update(article);
    }

    private static void updateTitle(Article article) {
        String title = sc.getString("New Title: ");
        article.setTitle(title);
        repository.update(article);
    }

    public static void displayAll() {
        repository.displayAll();
    }

    public static void displayAnArticle() {
        int id = Integer.parseInt(sc.getString("Enter ID of Article: "));
        Article article = repository.getArticle(id);
        System.out.println("ID: " + article.getId() + "\nTitle: " + article.getTitle() + "\nBrief: " + article.getBrief()
                + "\nContent: " + article.getContent() + "\nCreate Date: " + article.getCreateDate() + "\nAuthor: " +
                article.getUser().getName() + "\nIs Published: " + article.isPublished() + "\nCategory: "
                + article.getCategory().getTitle());
    }
    public static void close() {
        repository.close();
    }

    public static void updateAdmin() {
            displayAUserArticle();
            int id = Integer.parseInt(sc.getString("ID of Article You Want to Edit: "));
            String update = sc.getString("What You Want To Edit: (Title/Brief/Content/CreateDate/" +
                    "Category/isPublished): ");
            Article article = repository.getArticle(id);
            switch (update.toLowerCase()) {
                case "title":
                    updateTitle(article);
                    break;
                case "brief":
                    updateBrief(article);
                    break;
                case "content":
                    updateContent(article);
                    break;
                case "createdate":
                    updateCreateDate(article);
                    break;
                case "category":
                    if (updateCategory(article)) {
                        break;
                    }
                case "ispublished":
                    updatePublished(article);
                default:
                    System.out.println("Invalid Input");
            }
        }

    private static void updatePublished(Article article) {
        char published = sc.getString("Is Published(Y/N): ").charAt(0);
        if(published == 'Y' || published == 'N') {
            article.setPublished(published == 'Y');
            repository.update(article);
        }
    }

    public static void deleteArticleAdmin() {
        int id = Integer.parseInt(sc.getString("ID of Article You Want to Delete: "));
        if (repository.delete(id)>0) {
            System.out.println("Deleted Successfully");
        } else {
            System.out.println("Invalid ID");
        }
    }
}
