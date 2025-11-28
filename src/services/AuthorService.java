package services;

import models.Author;
import models.Book;

import java.util.*;


 //AuthorService: Yazar işlemlerini yönetir
 //Yazar ekleme/güncelleme
 //Kitap ekleme
 //Yazarları listeleme
public class AuthorService {

    private final Map<String, Author> authors = new HashMap<>(); // key: lowercase name -> Author

    //Yazar ekler ,varsa günceller.Ayni yazara ait kitaplar olabilecegi icin ilk kitapta olusturur daha sonra id guncellemez
    public void addOrUpdateAuthor(Author author, Book book) {
        if (author == null) return;

        String key = author.getName().toLowerCase();
        Author existing = authors.get(key);

        if (existing != null) {
            // Mevcut yazar varsa yeni kitapları ekle
            for (Book b : author.getBooks()) {
                if (!existing.getBooks().contains(b)) existing.addBook(b);
            }
            if (book != null && !existing.getBooks().contains(book)) existing.addBook(book);
        } else {
            // Yeni yazar ekle
            if (book != null && !author.getBooks().contains(book)) author.addBook(book);
            authors.put(key, author);
        }
    }

    //yazar bilgisini isme gore bulup doner
    public Author getAuthorByName(String name) {
        if (name == null) return null;
        return authors.get(name.toLowerCase());
    }

    //yazarlari guncelleme
    public void refreshAuthors() {
        Map<String, Author> refreshed = new HashMap<>();
        for (Author a : authors.values()) {
            refreshed.put(a.getName().toLowerCase(), a);
        }
        authors.clear();
        authors.putAll(refreshed);
    }

    //tum yazarlari doner
    public List<Author> listAllAuthors() {
        return new ArrayList<>(authors.values());
    }
}
