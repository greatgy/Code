package com.genius.xo.mongodb.mock.model;

import org.apache.commons.lang3.StringUtils;

import com.genius.core.base.annotation.Maps;

@Maps(strategy=Maps.dbStrategy)
public class Address {

	private String country;
	private String province;
	private String city;
	private String district;
	private String town;
	
	public Address() { }
	
	public Address(String country, String province, String city, String district, String town) {
		this.country = country;
		this.province = province;
		this.city = city;
		this.district = district;
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public int hashCode() {
		String all = this.country + this.province + this.city + this.district + this.town;
		return StringUtils.isEmpty(all) ? System.identityHashCode(this) : all.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return this == null;
		} else if (this.hashCode() == o.hashCode()) {
			return true;
		} else {
			return false;
		}
	}
}
