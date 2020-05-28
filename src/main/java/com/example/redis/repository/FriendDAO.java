package com.example.redis.repository;

import com.example.redis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendDAO {
    private static final String KEY = "friendsKey";

    @Autowired
    private RedisTemplate<String, Person> redisTemplate;

    public void addFriend(Person person) {
        redisTemplate.opsForList().leftPush(KEY, person);
    }
    public long getNumberOfFriends() {
        return redisTemplate.opsForList().size(KEY);
    }
    public Person getFriendAtIndex(Integer index) {
        return redisTemplate.opsForList().index(KEY, index);
    }
    public List<Person> listAllFriends(){
        System.out.println("Friend size: " + redisTemplate.opsForList().size(KEY));
        long size = redisTemplate.opsForList().size(KEY);
        return redisTemplate.opsForList().range(KEY,0,size);
    }
    public void removeFriend(Person p) {
        redisTemplate.opsForList().remove(KEY, 1, p);
    }
    public void removeKey(){
        redisTemplate.delete(KEY);
    }
}