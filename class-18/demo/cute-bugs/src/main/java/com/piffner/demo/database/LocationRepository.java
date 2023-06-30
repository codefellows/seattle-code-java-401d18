package com.piffner.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Our location repository is just like the bug repository
 * <p>
 * Note!  We changed to JpaRepository for **more magic**
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
