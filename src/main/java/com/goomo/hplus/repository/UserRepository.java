package com.goomo.hplus.repository;

import com.goomo.hplus.beans.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    //insert or save already available
    //Delete, Update
    //===============Login Method ======================
    @Query(value = "select u from User u where u.username= :name")
    public User searchByName(@Param("name") String username);

}
