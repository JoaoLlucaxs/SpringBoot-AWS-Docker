package com.essentials.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class PersonVOV1 implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private String firstName;
	private String lastName;

	private String address;
	private String gender;

	public PersonVOV1() {};
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVOV1 personVOV1 = (PersonVOV1) o;
        return Objects.equals(id, personVOV1.id) && Objects.equals(firstName, personVOV1.firstName) && Objects.equals(lastName, personVOV1.lastName) && Objects.equals(address, personVOV1.address) && Objects.equals(gender, personVOV1.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender);
    }
}
