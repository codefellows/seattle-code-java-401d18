package com.reyjroliva.buystuff.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.reyjroliva.buystuff.daos.ProductDao;
import com.reyjroliva.buystuff.models.Product;

@TypeConverters({BuyStuffDatabaseClassConverters.class})
@Database(entities = {Product.class}, version = 1) // if we update the version, it will destroy the DB
public abstract class BuyStuffDatabase extends RoomDatabase {
  public abstract ProductDao productDao();
}
