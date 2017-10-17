package com.future.bbetter.pet.model;
// Generated 2017/10/14 上午 11:25:08 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

/**
 * Pet generated by hbm2java
 */
@Entity
@Table(name = "pet", catalog = "bbetter")
public class Pet implements java.io.Serializable {

	private Integer petId;
	private String name;
	private float initHeight;
	private float initWeight;
	private float initVision;
	private float initHp;
	private float initMp;
	private float initMentality;
	private float initStrength;
	private float initIntelligence;
	private float initDexterity;
	private float initVitality;
	private Set<PetsHad> petsHads = new HashSet<PetsHad>(0);

	public Pet() {
	}

	public Pet(String name, float initHeight, float initWeight, float initVision, float initHp, float initMp,
			float initMentality, float initStrength, float initIntelligence, float initDexterity, float initVitality) {
		this.name = name;
		this.initHeight = initHeight;
		this.initWeight = initWeight;
		this.initVision = initVision;
		this.initHp = initHp;
		this.initMp = initMp;
		this.initMentality = initMentality;
		this.initStrength = initStrength;
		this.initIntelligence = initIntelligence;
		this.initDexterity = initDexterity;
		this.initVitality = initVitality;
	}

	public Pet(String name, float initHeight, float initWeight, float initVision, float initHp, float initMp,
			float initMentality, float initStrength, float initIntelligence, float initDexterity, float initVitality,
			Set<PetsHad> petsHads) {
		this.name = name;
		this.initHeight = initHeight;
		this.initWeight = initWeight;
		this.initVision = initVision;
		this.initHp = initHp;
		this.initMp = initMp;
		this.initMentality = initMentality;
		this.initStrength = initStrength;
		this.initIntelligence = initIntelligence;
		this.initDexterity = initDexterity;
		this.initVitality = initVitality;
		this.petsHads = petsHads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "pet_id", unique = true, nullable = false)
	public Integer getPetId() {
		return this.petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "init_height", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitHeight() {
		return this.initHeight;
	}

	public void setInitHeight(float initHeight) {
		this.initHeight = initHeight;
	}

	@Column(name = "init_weight", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitWeight() {
		return this.initWeight;
	}

	public void setInitWeight(float initWeight) {
		this.initWeight = initWeight;
	}

	@Column(name = "init_vision", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitVision() {
		return this.initVision;
	}

	public void setInitVision(float initVision) {
		this.initVision = initVision;
	}

	@Column(name = "init_hp", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitHp() {
		return this.initHp;
	}

	public void setInitHp(float initHp) {
		this.initHp = initHp;
	}

	@Column(name = "init_mp", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitMp() {
		return this.initMp;
	}

	public void setInitMp(float initMp) {
		this.initMp = initMp;
	}

	@Column(name = "init_mentality", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitMentality() {
		return this.initMentality;
	}

	public void setInitMentality(float initMentality) {
		this.initMentality = initMentality;
	}

	@Column(name = "init_strength", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitStrength() {
		return this.initStrength;
	}

	public void setInitStrength(float initStrength) {
		this.initStrength = initStrength;
	}

	@Column(name = "init_intelligence", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitIntelligence() {
		return this.initIntelligence;
	}

	public void setInitIntelligence(float initIntelligence) {
		this.initIntelligence = initIntelligence;
	}

	@Column(name = "init_dexterity", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitDexterity() {
		return this.initDexterity;
	}

	public void setInitDexterity(float initDexterity) {
		this.initDexterity = initDexterity;
	}

	@Column(name = "init_vitality", nullable = false, precision = 6, scale = 3)
	@ColumnDefault("0.0")
	public float getInitVitality() {
		return this.initVitality;
	}

	public void setInitVitality(float initVitality) {
		this.initVitality = initVitality;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pet")
	public Set<PetsHad> getPetsHads() {
		return this.petsHads;
	}

	public void setPetsHads(Set<PetsHad> petsHads) {
		this.petsHads = petsHads;
	}

}
