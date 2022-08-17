package com.example.backend.entity;
import java.util.Arrays;

import com.example.backend.exception.CustomException;

public enum VoteType {
  UPVOTE(1),
  DOWNVOTE(-1),;

  private Integer direction;

  VoteType(Integer direction) {
  }

  public static VoteType lookup(Integer direction) {
    return Arrays.stream(VoteType.values())
      .filter(value -> value.getDirection().equals(direction))
      .findAny()
      .orElseThrow(() -> new CustomException("Vote not found"));
  }

  public Integer getDirection() {
    return direction;
  }
}
