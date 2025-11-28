package models;

public enum Category {
    FICTION("Roman"),
    SCIENCE("Bilim"),
    HISTORY("Tarih"),
    TECHNOLOGY("Teknoloji"),
    CHILDREN("Çocuk"),
    DYSTOPIAN("Distopik"),
    ART("Sanat");


    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

