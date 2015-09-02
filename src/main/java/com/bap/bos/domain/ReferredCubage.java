package com.bap.bos.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 容积参考表
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年4月2日
 */
@Entity
@Table(name = "tb_referredcubage")
public class ReferredCubage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "referredcubage_id")
	private Long id;

	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "referredcubage_stationno", referencedColumnName = "Tanksetting_StationNo"),
			@JoinColumn(name = "referredcubage_tanknum", referencedColumnName = "Tanksetting_TankNum") })
	private Tanksetting tankSetting;

	@Column(name = "referredcubage_height")
	private Integer height;

	@Column(name = "referredcubage_cubage")
	private Double cubage;

	@Column(name = "referredcubage_enable")
	private Boolean enable = true;

	@ManyToOne
	@JoinColumn(name = "referredcubage_createdby")
	private Staff createdBy;

	@Column(name = "referredcubage_createdtime")
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tanksetting getTankSetting() {
		return tankSetting;
	}

	public void setTankSetting(Tanksetting tankSetting) {
		this.tankSetting = tankSetting;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Double getCubage() {
		return cubage;
	}

	public void setCubage(Double cubage) {
		this.cubage = cubage;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Staff getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Staff createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
