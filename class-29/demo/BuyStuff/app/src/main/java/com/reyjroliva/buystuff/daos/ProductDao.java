package com.reyjroliva.buystuff.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.reyjroliva.buystuff.models.Product;

import java.util.List;

@Dao // think of ths like a Spring JPA Repository, but we have to implement the SQL ourselves
public interface ProductDao {
  @Insert
  void insertAProduct(Product product);

  @Query("SELECT * FROM Product")
  List<Product> findAll();

  @Query("SELECT * FROM Product ORDER BY name ASC")
  List<Product> findAllSortedByName();

  @Query("SELECT * FROM Product WHERE id = :id")
  Product findByAnId(long id);
}
