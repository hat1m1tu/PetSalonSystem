package entity;

import java.util.Date;

public class History {
	int id ;
	int petId;
	int serviceId;
	Date serviceDate;
	public int getId() {
		return id;
	}
	public int getPetId() {
		return petId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}


}
