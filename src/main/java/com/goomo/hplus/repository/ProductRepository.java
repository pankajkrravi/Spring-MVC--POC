package com.goomo.hplus.repository;

import com.goomo.hplus.beans.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends CrudRepository<Product,Integer> {

    //Custom Query impl can be down with Spring JPA
    @Query("select p from Product p where p.name like %:name%")
    public List<Product> searchByName(@Param("name") String name);

}
