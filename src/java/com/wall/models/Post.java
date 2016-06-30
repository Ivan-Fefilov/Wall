package com.wall.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "select-all-of-posts", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "select-post-by-id", query = "SELECT p FROM Post p WHERE p.id=:id")
})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date time;
    
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date editTime;

    @Column(nullable = false, columnDefinition = "long varchar")
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    AppUser user;

    protected Post() {
    }

    public Post(String title, String content, AppUser user) {
        this.title = title;
        this.content = content;
        this.user = user;
        
        time = new Date();
        editTime= new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getEditTime() {
        return editTime;
    }
    
    

    public String getTitle() {
        return title;
    }

    public Date getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public AppUser getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wall.models.Post[ id=" + id + " ]";
    }

}
