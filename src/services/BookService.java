package services;

import models.Book;
import models.Category;

import java.util.*;
import java.util.stream.Collectors;

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


    public List<Book> findByCategory(Category category) {
        return bookMap.values().stream()
                .filter(book -> book.getCategory() == category)
                .collect(Collectors.toList());
    }



    public List<Book> listAllBooks() {
        return new ArrayList<>(bookMap.values());
    }
}
