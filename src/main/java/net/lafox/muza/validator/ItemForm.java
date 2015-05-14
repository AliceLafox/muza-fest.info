package net.lafox.muza.validator;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@SuppressWarnings("unused")
public class ItemForm {

    @NotNull
    @Min(1)
    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String category;

    @NotEmpty
    private String content;

    private boolean enabled;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date dateStart;

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
