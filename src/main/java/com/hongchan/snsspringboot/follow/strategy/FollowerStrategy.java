package com.hongchan.snsspringboot.follow.strategy;

import javax.persistence.Entity;

public interface FollowerStrategy {

    void writePost();

    void beforeAccessTimeline();
}
