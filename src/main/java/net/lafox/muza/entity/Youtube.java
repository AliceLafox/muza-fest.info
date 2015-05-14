package net.lafox.muza.entity;

import javax.persistence.*;

@Entity
@Table(name="youtubes")
@SuppressWarnings("unused")
public class Youtube {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 1024)
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
