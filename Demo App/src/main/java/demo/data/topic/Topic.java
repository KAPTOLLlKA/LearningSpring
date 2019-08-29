package demo.data.topic;

import java.sql.Timestamp;

public class Topic implements Comparable<Topic> {
    private int id;
    private String title;
    private String content;
    private String postedBy;
    private Timestamp postedAt;

    public Topic() {
    }

    public Topic(int id, String title, String content, String postedBy, Timestamp postedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }

    @Override
    public int compareTo(Topic topic) {
        return title.length() - topic.title.length();
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

    public Timestamp getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Timestamp postedAt) {
        this.postedAt = postedAt;
    }
}
