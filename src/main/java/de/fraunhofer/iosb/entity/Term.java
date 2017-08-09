package de.fraunhofer.iosb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Term
{
    @Id
    private String termID;

    @NotNull
    private Date from;

    @NotNull
    private Date till;
}
