package com.example.redis.repository;

import java.util.Set;
import javax.annotation.Resource;
import com.example.redis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyDAO {
    private static final String KEY = "myFamilyKey";

    @Resource(name="redisTemplate")
    private SetOperations<String, Person> setOps;

    @Autowired
    private RedisTemplate<String, Person> redisTemplate;

    public void addFamilyMembers(Person... persons) {
        setOps.add(KEY, persons);
    }
    public Set<Person> getFamilyMembers() {
        return setOps.members(KEY);
    }
    public long getNumberOfFamilyMembers() {
        return setOps.size(KEY);
    }
    public long removeFamilyMembers(Person... persons) {
        return setOps.remove(KEY, (Object[])persons);
    }

    public void removeKey(){
        redisTemplate.delete(KEY);
    }

    public void addPersons(Person... persons){
        redisTemplate.opsForSet().add(KEY,persons);
    }
}
