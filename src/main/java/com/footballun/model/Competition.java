/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "competition")
public class Competition extends NamedEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "competition")
	private List<Squad> squads;
}
