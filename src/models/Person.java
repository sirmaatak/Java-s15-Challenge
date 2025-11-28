package models;


 //Abstract Person sınıfı.
 //tum kisi turleri (uye-yazar-kutuphaneci) bu siniftan turetilir.
public abstract class Person {

    private final String id; // Her kişinin benzersiz kimliği
    private String name;     // Kişinin adı


    public Person(String id, String name) {
        this.id = id;       //Sonradan degistirilmesini istemedigim icin setter yazmiyorum.
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //SubClass'larda override edilecegi icin Abstract olarak yazdim.
    //Kisinin bilgisini donen method.
    public abstract String getInfo();
}
