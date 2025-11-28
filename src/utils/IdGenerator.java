
package utils;

import java.util.concurrent.atomic.AtomicInteger;


//ID üreten yardımcı sınıf.
//benzersiz ID'ler üretir.
//sınıf final yapılmıştır cunku miras alınması gerekmez.
//aynı şekilde constructor private'tır ve tamamen static methodlarla calisir.

public final class IdGenerator {


     //Kitap ID'leri için sayaç. ornek olarak : B1000, B1001
    private static final AtomicInteger BOOK = new AtomicInteger(1000);


     //Üye ID'leri için sayaç. ornek olarak: M1, M2..
    private static final AtomicInteger MEMBER = new AtomicInteger(1);

    //Loan ID'leri için sayaç. ornek olarak: L1, L2...
    private static final AtomicInteger LOAN = new AtomicInteger(1);

    //Yazar ID'leri için sayaç. ornek olarak: A1, A2...
    private static final AtomicInteger AUTHOR = new AtomicInteger(1);

     //Private constructor sayesinde nesne oluşturulmasını engelliyoruz.
    private IdGenerator() {}

    //"A" ile başlayan benzersiz bir yazar ID'si ureten method
    public static String nextAuthorId() {
        return "A" + AUTHOR.getAndIncrement(); }

    //"B" ile başlayan benzersiz bir kitap ID'si ureten method
    public static String nextBookId() {
        return "B" + BOOK.getAndIncrement();
    }

    //"M" ile başlayan benzersiz üye ID'si ureten method
    public static String nextMemberId() {
        return "M" + MEMBER.getAndIncrement();
    }

   //"L" ile başlayan benzersiz ödünç (loan) ID'si ureten method
    public static String nextLoanId() {
        return "L" + LOAN.getAndIncrement();
    }
}

