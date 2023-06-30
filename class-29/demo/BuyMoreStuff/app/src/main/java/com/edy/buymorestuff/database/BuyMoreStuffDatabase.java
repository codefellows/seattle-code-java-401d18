package com.edy.buymorestuff.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.edy.buymorestuff.dao.ProductDao;
import com.edy.buymorestuff.model.Product;

@Database(entities = {Product.class}, version = 1)  // if we update the version, it will delete the db!
@TypeConverters({BuyMoreStuffDatabaseConverters.class})
public abstract class BuyMoreStuffDatabase extends RoomDatabase
{
  public abstract ProductDao productDao();
}
