package es.uji.daal.easyrent.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

/**
 * Created by alberto on 17/03/16.
 */

@Entity
@Table(name = "photos")
public class Photo extends DomainModel {

    @ManyToOne(cascade = CascadeType.ALL)
    private Property property;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private Date uploadDate;

    /**
     * ======
     * Methods
     * ======
     */

    protected Photo() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * ======
     * Extra
     * ======
     */

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
