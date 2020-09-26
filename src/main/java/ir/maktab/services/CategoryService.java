package ir.maktab.services;

import ir.maktab.entities.Category;
import ir.maktab.MainApp;
import ir.maktab.Scan;
import ir.maktab.repository.CategoryRepository;

public class CategoryService {
    private static Category category = new Category();
    private static CategoryRepository repository = new CategoryRepository();
    private static Scan sc = MainApp.getSc();


    public static Category getCategory() {
        return category;
    }

    public static void setCategory(Category category) {
        CategoryService.category = category;
    }

    public static CategoryRepository getRepository() {
        return repository;
    }

    public static void setRepository(CategoryRepository repository) {
        CategoryService.repository = repository;
    }

    public static void display() {
    repository.display();
    }

//    public static Category check() {
//        String check = sc.getString("Category to (Add Or Use): ");
//        switch (check.toUpperCase()) {
//            case "ADD":
//                return add();
//            case "USE":
//                return use();
//        }
//        return null;
//    }

    public static Category use() {
        String title = sc.getString("Enter Title: ");
        return CategoryRepository.use(title);
    }

    public static void add() {
        String title = sc.getString("Enter Title: ");
        if (!CategoryRepository.checkDuplicateTitle(title)) {
            System.out.println("Invalid Title");
            return;
        }
        String description = sc.getString("Enter Description: ");
        category.setTitle(title);
        category.setDescription(description);
        CategoryRepository.add(category);
    }
    public static void close() {
        repository.close();
    }
}
