/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 21, 2015
 */
package com.footballun.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "team")
public class Team extends NamedEntity {
	
	@OneToOne
	@JoinColumn
	private Country country; 
	
}
