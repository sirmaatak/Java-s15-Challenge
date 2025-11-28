package models;

import java.util.ArrayList;
import java.util.List;



// person parent a sahip yazarlar class'i
public class Author extends Person {

    private String bio;                 // yazar biyografisi
    private final List<Book> books = new ArrayList<>(); //yazara ait kitaplari tutan list

    public Author(String id,String name) {
        super(id, name);
        this.bio = "bio";
    }

    // Getter ve Setter
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public List<Book> getBooks() { return books; }

    //yazara ait kitap ekleme
    public void addBook(Book book){
        if(!books.contains(book)) books.add(book);
    }

    @Override
    public String getInfo() {
        return "Author[id=" + getId() + ", name=" + getName() + ", totalBooks=" + books.size() + "]";
    }

    @Override
    public String toString() {
        return getInfo();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author a = (Author) o;
        return getName().equalsIgnoreCase(a.getName());
    }

    @Override
    public int hashCode() {
        return getName().toLowerCase().hashCode();
    }
}
