package com.piffner.demo.database;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BugLike {
    @Id
    @GeneratedValue
    public long id;

    // Because the only purpose of this table
    // is to map between the two bugs
    // we always "eager" load the values
    // meaning we grab it immediately when we get a buglike
    @ManyToOne(fetch = FetchType.EAGER)
    public Bug fromBug;

    @ManyToOne(fetch = FetchType.EAGER)
    public Bug toBug;

    public Date likedOn;

    public String message;
}
