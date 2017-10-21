package com.future.bbetter.schedule.model;
// Generated 2017/10/14 上午 11:25:08 by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

/**
 * ScheduleOwner generated by hbm2java
 */
@Entity
@Table(name = "schedule_owner", catalog = "bbetter")
public class ScheduleOwner implements java.io.Serializable {

	private Long scheduleOwnerId;
	private Long registrantId;
	private int source;
	private int isValid;
	private Date createdate;
	private Date updatedate;
	private Set<ScheduleReport> scheduleReports = new HashSet<ScheduleReport>(0);
	private Set<ScheduleHad> scheduleHads = new HashSet<ScheduleHad>(0);

	public ScheduleOwner() {
	}


	public ScheduleOwner(Long registrantId, int isValid, Date createdate, Date updatedate,
			Set<ScheduleReport> scheduleReports, Set<ScheduleHad> scheduleHads) {
		this.registrantId = registrantId;
		this.isValid = isValid;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.scheduleReports = scheduleReports;
		this.scheduleHads = scheduleHads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "schedule_owner_id", unique = true, nullable = false)
	public Long getScheduleOwnerId() {
		return this.scheduleOwnerId;
	}

	public void setScheduleOwnerId(Long scheduleOwnerId) {
		this.scheduleOwnerId = scheduleOwnerId;
	}

	@Column(name = "registrant_id", nullable = false)
	public Long getRegistrantId() {
		return registrantId;
	}


	public void setRegistrantId(Long registrantId) {
		this.registrantId = registrantId;
	}

	@Column(name = "source", nullable = false)
	public int getSource() {
		return source;
	}


	public void setSource(int source) {
		this.source = source;
	}


	@Column(name = "is_valid", nullable = false)
	@ColumnDefault("1")
	public int getIsValid() {
		return this.isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdate", length = 19)
	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedate", length = 19)
	public Date getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleOwner")
	public Set<ScheduleReport> getScheduleReports() {
		return this.scheduleReports;
	}

	public void setScheduleReports(Set<ScheduleReport> scheduleReports) {
		this.scheduleReports = scheduleReports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleOwner")
	public Set<ScheduleHad> getScheduleHads() {
		return this.scheduleHads;
	}

	public void setScheduleHads(Set<ScheduleHad> scheduleHads) {
		this.scheduleHads = scheduleHads;
	}

}
