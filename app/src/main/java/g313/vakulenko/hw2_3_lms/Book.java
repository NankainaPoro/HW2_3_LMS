package g313.vakulenko.hw2_3_lms;

import java.io.Serializable;

// Сущность книги
public class Book {
    // Поля
    private int id;
    private String name;
    private String author;

    // Конструктор
    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}