package models;

import java.util.List;
import java.util.Objects;
import services.BookService;


 //Librarian sinifi, kütüphanede kitap ekleme/guncelleme/silme islemlerini yapabilir.
 //BookService tanimladim, böylece her metod kullaniminda tekrar tekrar parametre tanimlamasina gerek yok.

public class Librarian {
    private final String id;            // calisan id
    private String name;                // calisan adı
    private String employeeNo;          // calisan numarası

    public Librarian(String id, String name, String employeeNo) {
        this.id = id;
        this.name = name;
        this.employeeNo = employeeNo;
    }

    // Getters / Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmployeeNo() { return employeeNo; }
    public void setEmployeeNo(String employeeNo) { this.employeeNo = employeeNo; }


    @Override
    public String toString() {
        return "Librarian[id=" + id + ", name=" + name + ", empNo=" + employeeNo + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Librarian)) return false;
        Librarian l = (Librarian) o;
        return id.equals(l.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}

