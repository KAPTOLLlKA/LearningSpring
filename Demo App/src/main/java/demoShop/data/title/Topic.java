package demoShop.data.title;

import java.sql.Date;

public class Topic {
    private int id;
    private String title;
    private String content;
    private String postedBy;
    private Date postedAt;

    public Topic() {
    }

    public Topic(int id, String title, String content, String postedBy, Date postedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
