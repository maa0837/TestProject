package com.reliant.nest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batchid")
public class NestEntity implements Serializable {
	private static final long serialVersionUID = -7988799579036225137L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String FIRST_NAME;

	@Column
	private String LAST_NAME;

	@Column
	private String ADDRESS1;

	@Column
	private String ADDRESS2;
	
	@Column
	private String CITY;
	
	@Column
	private String STATE;
	
	@Column
	private String POSTAL_CODE;
	
	@Column
	private String COUNTRY;
	
	@Column
	private String EMAIL;
	
	@Column
	private String PHONE;
	
	@Column
	private String LANGUAGE_PREFERENCE;
	
	@Column
	private String ORDER_NUMBER;
	
	@Column
	private String ORDER_DATE;
	
	@Column
	private String SKU;
	
	@Column
	private Integer QUANTITY;
	
	@Column
	private Integer ORIGINAL_INDEX;
	
	@Column
	private String BATCH_ID;
	
	@Column
	private String STATUS;
	
	@Column
	private String COMMENTS;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getADDRESS1() {
		return ADDRESS1;
	}

	public void setADDRESS1(String aDDRESS1) {
		ADDRESS1 = aDDRESS1;
	}

	public String getADDRESS2() {
		return ADDRESS2;
	}

	public void setADDRESS2(String aDDRESS2) {
		ADDRESS2 = aDDRESS2;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}

	public void setPOSTAL_CODE(String pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}

	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}

	public String getLANGUAGE_PREFERENCE() {
		return LANGUAGE_PREFERENCE;
	}

	public void setLANGUAGE_PREFERENCE(String lANGUAGE_PREFERENCE) {
		LANGUAGE_PREFERENCE = lANGUAGE_PREFERENCE;
	}

	public String getORDER_NUMBER() {
		return ORDER_NUMBER;
	}

	public void setORDER_NUMBER(String oRDER_NUMBER) {
		ORDER_NUMBER = oRDER_NUMBER;
	}

	public String getORDER_DATE() {
		return ORDER_DATE;
	}

	public void setORDER_DATE(String oRDER_DATE) {
		ORDER_DATE = oRDER_DATE;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public Integer getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(Integer qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public Integer getORIGINAL_INDEX() {
		return ORIGINAL_INDEX;
	}

	public void setORIGINAL_INDEX(Integer oRIGINAL_INDEX) {
		ORIGINAL_INDEX = oRIGINAL_INDEX;
	}

	public String getBATCH_ID() {
		return BATCH_ID;
	}

	public void setBATCH_ID(String bATCH_ID) {
		BATCH_ID = bATCH_ID;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getCOMMENTS() {
		return COMMENTS;
	}

	public void setCOMMENTS(String cOMMENTS) {
		COMMENTS = cOMMENTS;
	}

}
