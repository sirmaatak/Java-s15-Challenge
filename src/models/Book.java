package models;

import java.util.Objects;


 //Book: kutuphanedeki kitapları temsil eder.
 //her kitap benzersiz bir id'ye sahiptir.

public class Book {
    private final String id;       // kitap ID'si, örn: B1001
    private String title;          // kitap başlığı
    private Author author;         // yazari (Author nesnesi)
    private String category;       // kitap kategorisi TODO: Daha sonra ENUM yapalim
    private boolean isAvailable = true; //kitap odunc almaya musait mi?

   //kitabi kimin odunc aldigini tutmak icin
    private Member borrowedBy = null;

    public Book(String id, String title, Author author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean getIsAvailable() { return isAvailable; }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id); //id'ye göre eşitlik
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book[id=" + id +
                ", title='" + title + "'" +
                ", author='" + author + "'" +
                ", category='" + category + "'" +
                ", available=" + (isAvailable ? "is available" : "isn't available") +
                (borrowedBy != null ? ", borrowedBy=" + borrowedBy.getName() : "") +
                "]";
    }
}

