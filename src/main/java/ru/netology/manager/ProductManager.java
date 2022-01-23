package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;
import ru.netology.domain.Smartphone;
import ru.netology.domain.Book;

public class ProductManager {
    // добавьте необходимые поля, конструкторы и методы
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product item) {
        repository.save(item);
    }

    public Product[] searchBy(String text) {
        // ваш код
        Product[] result = new Product[0];
        for (Product item : repository.findAll()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                // используйте System.arraycopy, чтобы скопировать всё из result в tmp
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product instanceof Book) { // если в параметре product лежит объект класса Book
            Book book = (Book) product; // положем его в переменную типа Book чтобы пользоваться методами класса Book
            if (book.getName().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
                return true;
            }
            return book.getAuthor().contains(search);


        }
        if (product instanceof Smartphone) { // если в параметре product лежит объект класса Smartphone
            Smartphone smartphone = (Smartphone) product; // положем его в переменную типа Smartphone чтобы пользоваться методами класса Smartphone
            if (smartphone.getName().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
                return true;
            }
            return smartphone.getCompany().contains(search);
        }
        return false;
    }
}