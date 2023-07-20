package jsonquotes;

import java.util.Arrays;

public class Quote {
  String author;
  String likes;
  String text;
  String[] tags;

  public Quote() {
  }

  public Quote(String author, String likes, String text, String[] tags) {
    this.author = author;
    this.likes = likes;
    this.text = text;
    this.tags = tags;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getLikes() {
    return likes;
  }

  public void setLikes(String likes) {
    this.likes = likes;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "Quote{" +
      "author='" + author + '\'' +
      ", likes='" + likes + '\'' +
      ", text='" + text + '\'' +
      ", tags=" + Arrays.toString(tags) +
      '}';
  }
}
