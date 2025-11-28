package core;

import models.Book;
import models.Member;
import models.Librarian;
import models.Author;
import models.Loan;
import services.BookService;
import services.AuthorService;
import services.LoanService;

import java.util.*;

public class Library {

    private final BookService bookService;
    private final AuthorService authorService;
    private final List<Member> members = new ArrayList<>();
    private final List<Librarian> librarians = new ArrayList<>();
    private final LoanService loanService = new LoanService();


    public Library() {
        this.bookService = new BookService();
        this.authorService = new AuthorService();
    }

    public BookService getBookService() { return bookService; }
    public AuthorService getAuthorService() { return authorService; }
    public LoanService getLoanService() { return loanService; }

    //Uye ekleme methodu
    public void registerMember(Member member) { members.add(member); }

    //ismine gore uyeyi donduren method
    public Member getMemberByName(String name) {
        for (Member m : members) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    //kitap silme methodu
    public void removeBook(Book book) {
        if (book == null) return;

        if (!book.getIsAvailable()) {
            System.out.println("Bu kitap ödünç verildiği için silinemez!");
            return;
        }

        bookService.removeBook(book);

        Author author = book.getAuthor();
        if (author != null) {
            author.getBooks().remove(book);
        }
    }




    //kitap odunc verme methodu
    public boolean borrowBook(String memberName, String bookId) {
        Member member = getMemberByName(memberName);
        Book book = bookService.findById(bookId);

        //gerekli kontroller
        if (member == null || book == null) {
            System.out.println("Üye veya kitap bulunamadı!");
            return false;
        }

        if (!book.getIsAvailable()) {
            System.out.println("Kitap başkası tarafından alınmış.");
            return false;
        }

        if (!member.canBorrowMore()) {
            System.out.println("Üye maksimum kitap limitine ulaştı.");
            return false;
        }

        //gerekli kontrollerden sonra odunc alma islemi yapiliyor
        Loan loan = loanService.createLoan(member, book);
        member.addBorrowedBook(book);
        book.setAvailable(false);

        double cost = 10.0;
        member.refund(cost); // uyenin bakiyesi azalir cunku kitabi aldi
        System.out.println("Kitap ödünç verildi: " + book.getTitle());
        System.out.println("Fatura kesildi: " + cost + " birim");
        System.out.println("Mevcut bakiye: " + member.getBalance() + " birim");
        return true;
    }


    //kitabi iade etme methodu
    public boolean returnBook(String memberName, String bookId) {
        Member member = getMemberByName(memberName);
        Book book = bookService.findById(bookId);

        //gerekli kontroller
        if (member == null || book == null) {
            System.out.println("Üye veya kitap bulunamadı!");
            return false;
        }

        Loan loan = loanService.findActiveLoan(member, book);
        if (loan == null) {
            System.out.println("Bu kullanıcı bu kitabı almamış.");
            return false;
        }

        //iade islemi gerceklesir
        loanService.closeLoan(loan);
        member.removeBorrowedBook(book);
        book.setAvailable(true);

        //alinan ucret iade edilir.uyenin bakiyesi artar .cunku ucreti geri alir
        double refundAmount = 10.0;
        member.charge(refundAmount);
        System.out.println("Kitap iade edildi: " + book.getTitle());
        System.out.println("Ücret geri ödendi: " + refundAmount + " birim");
        System.out.println("Mevcut bakiye: " + member.getBalance() + " birim");
        return true;
    }

    


    public List<Book> listAllBooks() { return bookService.listAllBooks(); }
    public List<Author> listAllAuthors() { return authorService.listAllAuthors(); }
    public List<Member> listAllMembers() { return members; }
    public List<Librarian> listAllLibrarians() { return librarians; }


}
