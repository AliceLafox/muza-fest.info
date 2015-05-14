package net.lafox.muza.entity;

import javax.persistence.*;

@Entity
@Table(name="tracks")
@SuppressWarnings("unused")
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String title;

    private Long size = 0L;

    private Long secs = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSecs() {
        return secs;
    }

    public void setSecs(Long secs) {
        this.secs = secs;
    }
}
