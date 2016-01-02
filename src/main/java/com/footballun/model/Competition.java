/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.footballun.util.JsonDateDeserializer;
import com.footballun.util.JsonDateSerializer;
import com.footballun.util.LocalDatePersistenceConverter;

@Entity
@Table(name = "competition")
public class Competition extends NamedEntity {

	@OneToOne
	@JoinColumn(name = "host_country_id")
	private Country hostCountry;
	
	/**
	 * Columns
	 */
	@Column(name = "type")
	private String type;
	
	@Column(name = "year_from")
	private int yearFrom;
	
	@Column(name = "year_to")
	private int yearTo;
	
	@Column(name = "total_matchdays")
	private int totalMatchdays;
	
	@Column(name = "current_matchday")
	private int currentMatchday;

    @Column(name = "name_vn")
    private String nameVn;

	@Column(name = "start_at")
	@Convert(converter = LocalDatePersistenceConverter.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate startAt;
	
	@Column(name = "end_at")
	@Convert(converter = LocalDatePersistenceConverter.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
	private LocalDate endAt;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Country getHostCountry() {
		return hostCountry;
	}

	public void setHostCountry(Country hostCountry) {
		this.hostCountry = hostCountry;
	}

	public int getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public int getYearTo() {
		return yearTo;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}

	public int getTotalMatchdays() {
		return totalMatchdays;
	}

	public int getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(int currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public void setTotalMatchdays(int totalMatchdays) {
		this.totalMatchdays = totalMatchdays;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDate endAt) {
		this.endAt = endAt;
	}


    public String getNameVn() {
        return nameVn;
    }

    public void setNameVn(String nameVn) {
        this.nameVn = nameVn;
    }

}
