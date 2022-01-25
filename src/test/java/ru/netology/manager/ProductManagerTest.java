package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Smartphone honor50 = new Smartphone(1, "Honor50", 45000, "Honor");
    private Smartphone iPhone11Pro = new Smartphone(2, "IPhone11Pro", 70000, "Apple");
    private Smartphone galaxyS21 = new Smartphone(3, "GalaxyS21", 60000, "Samsung");
    private Book harryPotter = new Book(4, "Harry Potter", 4200, "Rowling J.K.");
    private Book lesMiserables = new Book(5, "Les Miserables", 150, "Victor Hugo");
    private Book nineteenEightyFour = new Book(6, "1984", 200, "George Orwell");
    private Smartphone iPhone13 = new Smartphone(7, "IPhone13", 90000, "Apple");

    @BeforeEach
    public void setUp() {
        manager.add(galaxyS21);
        manager.add(lesMiserables);
        manager.add(honor50);
        manager.add(harryPotter);
        manager.add(iPhone11Pro);
        manager.add(nineteenEightyFour);
        manager.add(iPhone13);
    }

    @Test
    public void shouldSaveItems() {
        Product[] expected = new Product[]{galaxyS21, lesMiserables, honor50, harryPotter, iPhone11Pro, nineteenEightyFour, iPhone13};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphone() {
        Product[] expected = new Product[]{honor50};
        Product[] actual = manager.searchBy("Honor50");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindCompany() {
        Product[] expected = new Product[]{galaxyS21};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneCompany() {
        Product[] expected = new Product[]{iPhone11Pro, iPhone13};
        Product [] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBook() {
        Product[] expected = new Product[]{nineteenEightyFour};
        Product[] actual = manager.searchBy("1984");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAuthor() {
        Product[] expected = new Product[]{harryPotter};
        Product[] actual = manager.searchBy("Rowling");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNonExistentAuthor() {
        Product [] expected = new Product[] {};
        Product [] actual = manager.searchBy("Pushkin");
        assertArrayEquals(expected, actual);
    }
}