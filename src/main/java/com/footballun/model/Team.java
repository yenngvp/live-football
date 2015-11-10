/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 21, 2015
 */
package com.footballun.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "team")
public class Team extends NamedEntity {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	private Set<Squad> squads = new LinkedHashSet<Squad>();
	
	@OneToOne
	@JoinColumn
	private Country country; 
	
}
