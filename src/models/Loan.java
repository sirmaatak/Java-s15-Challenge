package models;

public class Loan {
    private final Book book;
    private final Member member;
    private final double cost; // kitap ödünç alma ücreti
    private boolean returned = false;

    public Loan(Member member, Book book, double cost) {
        this.member = member;
        this.book = book;
        this.cost = cost;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public double getCost() {
        return cost;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Loan[book=" + book.getTitle() + ", member=" + member.getName() +
                ", cost=" + cost + ", returned=" + returned + "]";
    }
}
