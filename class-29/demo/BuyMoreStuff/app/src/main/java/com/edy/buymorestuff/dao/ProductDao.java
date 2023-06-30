package com.edy.buymorestuff.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.edy.buymorestuff.model.Product;

import java.util.List;

@Dao  // Think of this like a Spring JPA repository, except it implements nothing for us :(
public interface ProductDao
{
  @Insert
  public void insertAProduct(Product product);

  @Query("SELECT * FROM Product")
  public List<Product> findAll();

  @Query("SELECT * FROM Product ORDER BY name ASC")
  public List<Product> findAllSortedByName();

  @Query("SELECT * FROM Product WHERE id = :id")
  Product findByAnId(long id);
}
