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
    @NamedQuery(name = "select-all-comments-to-post", query = "SELECT c FROM Comment c JOIN Post p ON c.post = p WHERE c.post = :post"),
    @NamedQuery(name = "select-comment-by-id", query = "SELECT c FROM Comment c WHERE c.id=:id")
})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date time;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date editTime;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    AppUser user;

    @ManyToOne
    @JoinColumn(nullable = false)
    Post post;

    public Comment() {
    }

    public Comment(String content, AppUser user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
        time = new Date();
        editTime = new Date();
    }

    public Date getTime() {
        return time;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AppUser getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wall.models.Comment[ id=" + id + " ]";
    }

}
