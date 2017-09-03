package com.future.bbetter.schedule.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ScheduleRemind.java auto generated by Charles. 2017/9/3 下午 05:39:31
 */
@Entity
@Table(name = "schedule_remind")
public class ScheduleRemind {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "schedule_remind_id")
	private Long scheduleRemindId;
	@Column(name = "schedule_had_id")
	private Long scheduleHadId;
	@Column(name = "remind_time")
	private Date remindTime;
	@Column(name = "remind_way")
	private Integer remindWay;
	@Column(name = "remark")
	private String remark;

	public ScheduleRemind(){}
	public ScheduleRemind(Long scheduleRemindId){
		this.scheduleRemindId = scheduleRemindId;
	}

	public void setScheduleRemindId(Long scheduleRemindId){
		this.scheduleRemindId = scheduleRemindId;
	}
	public Long getScheduleRemindId() {
			return this.scheduleRemindId;
	}
	public void setScheduleHadId(Long scheduleHadId){
		this.scheduleHadId = scheduleHadId;
	}
	public Long getScheduleHadId() {
			return this.scheduleHadId;
	}
	public void setRemindTime(Date remindTime){
		this.remindTime = remindTime;
	}
	public Date getRemindTime() {
			return this.remindTime;
	}
	public void setRemindWay(Integer remindWay){
		this.remindWay = remindWay;
	}
	public Integer getRemindWay() {
			return this.remindWay;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark() {
			return this.remark;
	}


	@Override
	public String toString() {
		return String.format(
				"ScheduleRemind [scheduleRemindId=%s, scheduleHadId=%s, remindTime=%s, remindWay=%s, remark=%s]",scheduleRemindId, scheduleHadId, remindTime, remindWay, remark);
	}
}
