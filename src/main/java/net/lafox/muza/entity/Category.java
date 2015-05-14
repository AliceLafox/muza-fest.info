package net.lafox.muza.entity;


import javax.persistence.*;

@Entity
@Table(name="categories")
@SuppressWarnings("unused")
public class Category {

    @Id
    @Column(nullable = false, name="seo_url")
    private String seoUrl;

    @Column(nullable=false)
    private String title;

    public Category() {}

    public Category(String title, String seoUrl) {
        this.title = title;
        this.seoUrl = seoUrl;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "seoUrl='" + seoUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
