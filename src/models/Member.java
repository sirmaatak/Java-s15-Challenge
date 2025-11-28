package models;

import java.util.ArrayList;
import java.util.List;

//// person parent a sahip uyeler class'i
public class Member extends Person {
    private String email; // uyenin e-posta adresi
    private double balance = 50.0; // uyenin bakiye durumu
    private final List<Book> borrowedBooks = new ArrayList<>(); //odunc alınan kitaplari tutan liste

    public static final int MAX_BORROW = 5; // uyenin ayni anda odunc alabilecegi maksimum kitap sayısı


    public Member(String id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public double getBalance() {
        return balance;
    }

    // bakiye arttırmak icin yazilan method
    public void charge(double amount) {
        this.balance += amount;
    }
    // bakiye azaltmak icin yazilan method
    public void refund(double amount) {
        this.balance -= amount;
    }

    // aktif odunc alinan kitaplari veren method
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // uye daha fazla kitap alabilir mi kontrolu yapan method
    public boolean canBorrowMore() {
        return borrowedBooks.size() < MAX_BORROW;
    }

    // odunc alinan kitaplar listesine kitabi ekleme
    public void addBorrowedBook(Book book) {
        if(canBorrowMore()) {
            borrowedBooks.add(book);
        }else{
            System.out.println("Maximum kitap limiti!!!Lutfen kitap almak icin onceden aldiginiz kitaplardan iade yapiniz");
        }
    }

    //odunc alinan kitaplardan iade edilen kitabi silme
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }


    //uye bilgilerini veren method
    //TODO:STRINGBUILDER ACIKLAMASINI YAZ
    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member[id=").append(getId())
                .append(", name=").append(getName())
                .append(", email=").append(email)
                .append(", borrowedBooks=[");

        for (int i = 0; i < borrowedBooks.size(); i++) {
            sb.append(borrowedBooks.get(i).getTitle());
            if (i < borrowedBooks.size() - 1) sb.append(", ");
        }
        sb.append("]]");
        return sb.toString();
    }

}
