/**
 * @author: yen.nt
 * @created on Nov 4, 2015
 */

package com.footballun.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "squad_member")
public class SquadMember extends BaseEntity {
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn
	private List<Hero> heros;
}
