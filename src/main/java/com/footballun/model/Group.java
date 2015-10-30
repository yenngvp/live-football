package com.footballun.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "`group`")
public class Group extends NamedEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Squad> squads; 
}
