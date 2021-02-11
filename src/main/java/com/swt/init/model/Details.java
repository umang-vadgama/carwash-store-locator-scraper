package com.swt.init.model;

public class Details {
	
	
	String city,store,address,phoneNumber,email,workingHrs;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkingHrs() {
		return workingHrs;
	}

	public void setWorkingHrs(String workingHrs) {
		this.workingHrs = workingHrs;
	}

	@Override
	public String toString() {
		return "Details [city=" + city + ", store=" + store + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", workingHrs=" + workingHrs + "]";
	}
	

}
