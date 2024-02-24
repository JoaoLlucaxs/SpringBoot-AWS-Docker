package com.essentials.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

//TESTING SERIALIZATION IN VO -> @JsonPropertyOrder({"id","address","first_name","lastName","gender"})
@JsonPropertyOrder({ "id", "firstName", "lastName", "address", "gender" })
public class PersonVOV1 extends RepresentationModel<PersonVOV1> implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Mapping("id")
	private Long key;

	// TESTING SERIALIZATION IN VO -> @JsonProperty("first_name")
	private String firstName;
	private String lastName;

	private String address;
	//TESTING SERIALIZATION IN VO -> @JsonIgnore
	private String gender;

	public PersonVOV1() {};


	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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
		if (!super.equals(o)) return false;
		PersonVOV1 that = (PersonVOV1) o;
		return Objects.equals(key, that.key) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), key, firstName, lastName, address, gender);
	}
}
