package ir.maktab.entities;

import javax.persistence.*;
@Entity
@Table(name = "article")

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , updatable = false , nullable = false , unique = true)
    private int id;
    @Column(name = "title" , nullable = false , unique = true)
    private String title;
    @Column(name = "brief" )
    private String brief;
    @Column(name = "content" )
    private String content;
    @Column(name = "createDate" , nullable = false)
    private String createDate;
    @Column(name = "isPublished" , nullable = false)
    private boolean isPublished;
    @ManyToOne
    @JoinColumn(name="publisherid")
    private User user;
    @ManyToOne
    @JoinColumn(name="categoryid")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
