/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "competition")
public class Competition extends NamedEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
	private Set<Squad> squads = new LinkedHashSet<Squad>();
}
