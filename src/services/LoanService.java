package services;

import models.Book;
import models.Loan;
import models.Member;

import java.util.ArrayList;
import java.util.List;

//LoanService: Odunc alma islemlerini yonetir.

public class LoanService {
    private final List<Loan> loans = new ArrayList<>();
    private static final double LOAN_COST = 10.0; // örnek ücret

    public Loan createLoan(Member member, Book book) {
        Loan loan = new Loan(member, book, LOAN_COST);
        loans.add(loan);
        return loan;
    }

    /** Ödünç alınmış ve henüz iade edilmemiş kitabı bulur */
    public Loan findActiveLoan(Member member, Book book) {
        for (Loan loan : loans) {
            if (loan.getMember().equals(member) &&
                    loan.getBook().equals(book) &&
                    !loan.isReturned()) {
                return loan;
            }
        }
        return null;
    }

    /** Ödünç işlemini kapatır (iade) */
    public void closeLoan(Loan loan) {
        if (loan != null) {
            loan.setReturned(true);
        }
    }

}
