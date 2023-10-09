package com.customer.feedback.model;

import java.util.List;

public class GroupedFeedback {
  Feedback feedback;
  List<Feedback> pastFeedbacks;

  public Feedback getFeedback() {
    return feedback;
  }

  public void setFeedback(Feedback feedback) {
    this.feedback = feedback;
  }

  public List<Feedback> getPastFeedbacks() {
    return pastFeedbacks;
  }

  public void setPastFeedbacks(List<Feedback> pastFeedbacks) {
    this.pastFeedbacks = pastFeedbacks;
  }

  @Override
  public String toString() {
    return "GroupedFeedback {" +
        "feedback=" + feedback +
        ", pastFeedbacks=" + pastFeedbacks +
        '}';
  }
}
