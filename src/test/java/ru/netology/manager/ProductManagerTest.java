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

    @BeforeEach
    public void setUp() {
        manager.add(galaxyS21);
        manager.add(lesMiserables);
        manager.add(honor50);
        manager.add(harryPotter);
        manager.add(iPhone11Pro);
        manager.add(nineteenEightyFour);
    }

    @Test
    public void shouldSaveItems() {
        Product[] expected = new Product[]{galaxyS21, lesMiserables, honor50, harryPotter, iPhone11Pro, nineteenEightyFour};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphone() {
        manager.searchBy("Honor50");

        Product[] expected = new Product[]{honor50};
        Product[] actual = manager.searchBy("Honor50");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindCompany() {
        manager.searchBy("Samsung");

        Product[] expected = new Product[]{galaxyS21};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBook() {
        manager.searchBy("1984");

        Product[] expected = new Product[]{nineteenEightyFour};
        Product[] actual = manager.searchBy("1984");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAuthor() {
        manager.searchBy("Rowling J.K.");

        Product[] expected = new Product[]{harryPotter};
        Product[] actual = manager.searchBy("Rowling");
        assertArrayEquals(expected, actual);
    }
}