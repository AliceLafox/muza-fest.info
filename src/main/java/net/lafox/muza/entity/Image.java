package net.lafox.muza.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

@Entity
@Table(name = "images")
@SuppressWarnings("unused")
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(length = 16 * 1024)
    private String description;

    @Column(length = 32)
    private String contentType;

    @Column(length = 2048)
    private String externalUrl;

    @JsonIgnore
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;

    @Column(nullable = false)
    private long version = 0L;
    @Column(nullable = false)
    private int width = 0;
    @Column(nullable = false)
    private int height = 0;

    @Column(nullable = true)
    private Date created = new Date();

    @Column(nullable = true)
    private Date updated = new Date();

    public Image(byte[] img) throws IOException {
        setImg(img);
    }

    public Image(byte[] img, String title) throws IOException {
        setImg(img);
        this.title = title;
    }

    public Image() {
    }

    public void setImg(byte[] img) throws IOException {
        updated = new Date();
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(img));
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
            contentType = Magic.getMagicMatch(img, false).getMimeType();
            this.img = img;
            version++;
        } catch (IOException | MagicException | MagicMatchNotFoundException | MagicParseException e) {
            throw new IOException("Bad Image Format");
        }
    }

//////// GENERATED !!! ////////////////////////////////////////////////////////////


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public byte[] getImg() {
        return img;
    }

    public long getVersion() {
        return version;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", contentType='" + contentType + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                ", img.length=" + img.length +
                ", version=" + version +
                ", width=" + width +
                ", height=" + height +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
