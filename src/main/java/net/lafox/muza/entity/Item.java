package net.lafox.muza.entity;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="items")
@SuppressWarnings("unused")
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(length = 8 * 1024)
    private String content;

    @Column(name = "datestart")
    private Date dateStart= new Date();

    @OneToOne
    private Image avatar;

    @Column(nullable=false)
    private Boolean enabled = false;

    @ManyToOne
    private Category category;

    @OneToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private List<Image> images=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private List<Track> tracks=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    private List<Youtube> youtubes=new ArrayList<>();
    @ManyToOne
    private User user;


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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Boolean isEnabled() {return enabled; }
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Youtube> getYoutubes() {
        return youtubes;
    }

    public void setYoutubes(List<Youtube> youtubes) {
        this.youtubes = youtubes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateStart=" + dateStart +
                ", avatar=" + avatar +
                ", enabled=" + enabled +
                ", category=" + category +
                ", images=" + images +
                ", tracks=" + tracks +
                ", youtubes=" + youtubes +
                ", user=" + user +
                '}';
    }
}
