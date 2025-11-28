package services;

import models.Book;

import java.util.*;

//BookService: Kitap işlemlerini yönetir
//Kitap ekleme/güncelleme
//Kitap arama (id-title-category-yazar kriterlerine gore yri ayri methodlar)
public class BookService {
    private final Map<String, Book> bookMap = new HashMap<>();

    public void addBook(Book book) {
        if (book != null) bookMap.put(book.getId(), book);
    }

    public void updateBook(Book book) {
        if (book != null && bookMap.containsKey(book.getId())) bookMap.put(book.getId(), book);
    }

    public void removeBook(Book book) {
        if (book != null) bookMap.remove(book.getId());
    }

    public Book findById(String id) {
        return bookMap.get(id);
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : bookMap.values()) {
            if (b.getTitle().equalsIgnoreCase(title)) result.add(b);
        }
        return result;
    }

    public List<Book> findByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book b : bookMap.values()) {
            if (b.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> findByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book b : bookMap.values()) {
            if (b.getCategory().equalsIgnoreCase(category)) result.add(b);
        }
        return result;
    }

    public List<Book> listAllBooks() {
        return new ArrayList<>(bookMap.values());
    }
}
