import core.Library;
import models.*;
import utils.IdGenerator;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- GİRİŞ MENÜSÜ ---");
            System.out.println("1. Kütüphaneci girişi");
            System.out.println("2. Üye girişi");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            String mainChoice = sc.nextLine().trim();

            switch (mainChoice) {
                case "1" -> { // Kütüphaneci girişi
                    librarianMenu(sc, library);
                }
                case "2" -> { // Üye girişi
                    System.out.print("Üye adınız: ");
                    String memberName = sc.nextLine();
                    Member member = library.getMemberByName(memberName);
                    if (member == null) {
                        System.out.println("Üye bulunamadı.");
                        break;
                    }
                    memberMenu(sc, library, member);
                }
                case "0" -> {
                    System.out.println("Sistemden çıkılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private static void librarianMenu(Scanner sc, Library library) {

        while (true) {
            System.out.println("\n--- KÜTÜPHANECİ MENÜ ---");
            System.out.println("1. Kitap ekle");
            System.out.println("2. Kitap ara");
            System.out.println("3. Kitap sil");
            System.out.println("4. Tüm kitapları listele");
            System.out.println("5. Tüm yazarları listele");
            System.out.println("6. Üye ekle");
            System.out.println("7. Tüm üyeleri listele");
            System.out.println("8. Tüm kutuphanecileri listele");
            System.out.println("9. Bilgi Guncelle ");
            System.out.println("0. Çıkış / Ana menüye dön");
            System.out.print("Seçiminiz: ");

            String choice=  sc.nextLine().trim();

            switch (choice) {
                // Kitap ekle
                case "1" -> {
                    System.out.print("Kitap adı: ");
                    String title = sc.nextLine();
                    System.out.print("Yazar adı: ");
                    String authorName = sc.nextLine();
                    System.out.print("Kategori: ");
                    String category = sc.nextLine();

                    // Yazar tekilleştirme.Burada eger daha once boyle bir yazar varsa yeni id atamiyoruz
                    Author author = library.getAuthorService().getAuthorByName(authorName);
                    if (author == null) {
                        author = new Author(IdGenerator.nextAuthorId(),authorName); // Yeni ID otomatik atanır
                    }

                    // Kitap olustur
                    Book book = new Book(IdGenerator.nextBookId(), title, author, category);

                    // Kitabı ve yazarı ekle / güncelle
                    library.getAuthorService().addOrUpdateAuthor(author, book); // Yazar listesine ekle veya güncelle
                    library.getBookService().addBook(book);                    // Kitap listesine ekle

                    System.out.println("Kitap eklendi: " + book);
                }

                //Kitap ara
                case "2" -> {
                    System.out.println("\n--- KİTAP ARAMA ---");
                    System.out.println("1. ID ile ara");
                    System.out.println("2. Kitap adı ile ara");
                    System.out.println("3. Yazar adına göre ara");
                    System.out.println("4. Kategoriye göre ara");
                    System.out.print("Arama seçimi: ");
                    String searchChoice = sc.nextLine().trim();

                    switch (searchChoice) {
                        case "1" -> {
                            System.out.print("Kitap ID: ");
                            String id = sc.nextLine();
                            Book b = library.getBookService().findById(id);
                            System.out.println(b != null ? b : "Kitap bulunamadı.");
                        }
                        case "2" -> {
                            System.out.print("Kitap adı: ");
                            String title = sc.nextLine();
                            List<Book> list = library.getBookService().findByTitle(title);
                            list.forEach(System.out::println);
                            if (list.isEmpty()) System.out.println("Sonuç bulunamadı.");
                        }
                        case "3" -> {
                            System.out.print("Yazar adı: ");
                            String authorName = sc.nextLine();
                            List<Book> list = library.getBookService().findByAuthor(authorName);
                            list.forEach(System.out::println);
                            if (list.isEmpty()) System.out.println("Sonuç bulunamadı.");
                        }
                        case "4" -> {
                            System.out.print("Kategori: ");
                            String category = sc.nextLine();
                            List<Book> list = library.getBookService().findByCategory(category);
                            list.forEach(System.out::println);
                            if (list.isEmpty()) System.out.println("Sonuç bulunamadı.");
                        }
                        default -> System.out.println("Geçersiz arama seçimi.");
                    }
                }

                // Kitap sil
                case "3" -> {
                    System.out.print("Silinecek kitap ID: ");
                    String id = sc.nextLine();
                    Book toRemove = library.getBookService().findById(id);
                    if (toRemove != null) {
                        library.removeBook(toRemove);
                        System.out.println("Kitap silindi: " + id);
                    } else {
                        System.out.println("Kitap bulunamadı: " + id);
                    }
                }

                //Tum kitaplari listele
                case "4" -> {
                    System.out.println("*********** TÜM KİTAPLAR ***********");
                    library.listAllBooks().forEach(System.out::println);
                }

                //Tum yazarlari listele
                case "5" -> {
                    System.out.println("*********** TÜM YAZARLAR ***********");
                    library.listAllAuthors().forEach(System.out::println);
                }

                // Üye ekle
                case "6" -> {
                    System.out.print("Üye adı: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Member member = new Member(IdGenerator.nextMemberId(), name, email);
                    library.registerMember(member);
                    System.out.println("Üye eklendi: " + member);
                }

                //Tum uyeleri listele
                case "7" -> {
                    System.out.println("*********** TÜM ÜYELER ***********");
                    library.listAllMembers().forEach(System.out::println);
                }

                //Tum calisanlari listele
                case "8" -> {
                    System.out.println("*********** TÜM KÜTÜPHANECİLER ***********");
                    library.listAllLibrarians().forEach(System.out::println);
                }

                // Bilgi güncelle
                case "9" -> {
                    System.out.println("\n--- BİLGİ GÜNCELLE ---");
                    System.out.println("1. Kitap güncelle");
                    System.out.println("2. Üye güncelle");
                    System.out.println("3. Yazar güncelle");
                    System.out.print("Seçim: ");
                    String updateChoice = sc.nextLine().trim();

                    switch (updateChoice) {

                        // 1) Kitap güncelleme
                        case "1" -> {
                            System.out.print("Güncellenecek kitap ID: ");
                            String id = sc.nextLine();
                            Book book = library.getBookService().findById(id);
                            if (book == null) {
                                System.out.println("Kitap bulunamadı.");
                                break;
                            }

                            System.out.print("Yeni başlık (boş bırakılırsa değişmez): ");
                            String newTitle = sc.nextLine();
                            if (!newTitle.isBlank()) book.setTitle(newTitle);

                            System.out.print("Yeni kategori (boş bırakılırsa değişmez): ");
                            String newCategory = sc.nextLine();
                            if (!newCategory.isBlank()) book.setCategory(newCategory);

                            System.out.print("Yeni yazar adı (boş bırakılırsa değişmez): ");
                            String newAuthorName = sc.nextLine();
                            if (!newAuthorName.isBlank()) {
                                Author newAuthor = library.getAuthorService().getAuthorByName(newAuthorName);
                                if (newAuthor == null) newAuthor = new Author(null, newAuthorName);
                                book.setAuthor(newAuthor);
                            }

                            library.getBookService().updateBook(book);
                            library.getAuthorService().addOrUpdateAuthor(book.getAuthor(), book); // Tekilleştirme
                            System.out.println("Kitap güncellendi: " + book);
                        }

                        // 2) Üye güncelleme
                        case "2" -> {
                            System.out.print("Güncellenecek üye adı: ");
                            String memberName = sc.nextLine();
                            Member member = library.getMemberByName(memberName);
                            if (member == null) {
                                System.out.println("Üye bulunamadı.");
                                break;
                            }

                            System.out.print("Yeni isim (boş bırakılırsa değişmez): ");
                            String newName = sc.nextLine();
                            if (!newName.isBlank()) member.setName(newName);

                            System.out.print("Yeni email (boş bırakılırsa değişmez): ");
                            String newEmail = sc.nextLine();
                            if (!newEmail.isBlank()) member.setEmail(newEmail);

                            System.out.println("Üye güncellendi: " + member);
                        }

                        // 3) Yazar güncelleme
                        case "3" -> {
                            System.out.print("Güncellenecek yazar adı: ");
                            String name = sc.nextLine();
                            Author author = library.getAuthorService().getAuthorByName(name);
                            if (author == null) {
                                System.out.println("Yazar bulunamadı.");
                                break;
                            }

                            System.out.print("Yeni isim (boş bırakılırsa değişmez): ");
                            String newName = sc.nextLine();
                            if (!newName.isBlank()) author.setName(newName);

                            System.out.print("Yeni biyografi (boş bırakılırsa değişmez): ");
                            String newBio = sc.nextLine();
                            if (!newBio.isBlank()) author.setBio(newBio);

                            library.getAuthorService().refreshAuthors(); // Tekilleştirme
                            System.out.println("Yazar güncellendi: " + author);
                        }

                        default -> System.out.println("Geçersiz seçim.");
                    }
                }

                // Geri
                case "0" -> {
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private static void memberMenu(Scanner sc, Library library, Member member) {
        while (true) {
            System.out.println("\n--- ÜYE MENÜ ---");
            System.out.println("1. Tüm kitapları listele");
            System.out.println("2. Kitap ara");
            System.out.println("3. Kitap ödünç al");
            System.out.println("4. Kitap iade et");
            System.out.println("5. Ödünç aldığım kitapları listele");
            System.out.println("0. Geri");

            System.out.print("Seçiminiz: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                //Tum kitaplari listele
                case "1" -> library.listAllBooks().forEach(System.out::println);

                //Kitap ara
                case "2" -> {
                    System.out.println("Arama kriteri seçin:");
                    System.out.println("1. ID");
                    System.out.println("2. Kitap adı");
                    System.out.println("3. Yazar adı");
                    System.out.println("4. Kategori");
                    System.out.print("Seçim: ");
                    String searchChoice = sc.nextLine().trim();

                    switch (searchChoice) {
                        case "1" -> {
                            System.out.print("Kitap ID: ");
                            String id = sc.nextLine();
                            Book b = library.getBookService().findById(id);
                            System.out.println(b != null ? b : "Kitap bulunamadı.");
                        }
                        case "2" -> {
                            System.out.print("Kitap adı: ");
                            String title = sc.nextLine();
                            List<Book> list = library.getBookService().findByTitle(title);
                            list.forEach(System.out::println);
                            if (list.isEmpty()) System.out.println("Sonuç bulunamadı.");
                        }
                        case "3" -> {
                            System.out.print("Yazar adı: ");
                            String authorName = sc.nextLine();
                            List<Book> list = library.getBookService().findByAuthor(authorName);
                            list.forEach(System.out::println);
                            if (list.isEmpty()) System.out.println("Sonuç bulunamadı.");
                        }
                        case "4" -> {
                            System.out.print("Kategori: ");
                            String category = sc.nextLine();
                            List<Book> list = library.getBookService().findByCategory(category);
                            list.forEach(System.out::println);
                            if (list.isEmpty()) System.out.println("Sonuç bulunamadı.");
                        }
                        default -> System.out.println("Geçersiz seçim.");
                    }
                }

                //Kitap odunc alma
                case "3" -> {
                    System.out.print("Almak istediğiniz kitabın ID'si: ");
                    String bookId = sc.nextLine();
                    library.borrowBook(member.getName(), bookId);
                }

                //Kitap iade etme
                case "4" -> {
                    System.out.print("İade etmek istediğiniz kitabın ID'si: ");
                    String bookId = sc.nextLine();
                    library.returnBook(member.getName(), bookId);
                }

                // Uyenin odunc aldigi kitaplari listele
                case "5" -> {
                    List<Book> borrowed = member.getBorrowedBooks();
                    if (borrowed.isEmpty()) {
                        System.out.println("Şu anda ödünç alınmış kitap yok.");
                    } else {
                        System.out.println("*********** Ödünç Aldığım Kitaplar ***********");
                        borrowed.forEach(System.out::println);
                    }
                }

                //Geri
                case "0" -> {
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }
}
