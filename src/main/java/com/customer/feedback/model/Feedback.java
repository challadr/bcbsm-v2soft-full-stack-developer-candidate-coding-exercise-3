package com.customer.feedback.model;

public class Feedback {
  private String id;
  private String username;
  private String comments;
  private Integer rating;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Feedback {" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        ", feedback='" + comments + '\'' +
        ", rating=" + rating +
        '}';
  }
}
