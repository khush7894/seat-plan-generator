package com.seating.plan.generator.system.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String address;

	@Column(name="contact_one")
	private String contactOne;

	@Column(name="contact_two")
	private String contactTwo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="is_deleted")
	private int isDeleted;

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="profile_photo")
	private String profilePhoto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	//bi-directional many-to-one association to UserInstitute
	@OneToMany(mappedBy="user")
	private List<UserInstitute> userInstitutes;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactOne() {
		return this.contactOne;
	}

	public void setContactOne(String contactOne) {
		this.contactOne = contactOne;
	}

	public String getContactTwo() {
		return this.contactTwo;
	}

	public void setContactTwo(String contactTwo) {
		this.contactTwo = contactTwo;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePhoto() {
		return this.profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<UserInstitute> getUserInstitutes() {
		return this.userInstitutes;
	}

	public void setUserInstitutes(List<UserInstitute> userInstitutes) {
		this.userInstitutes = userInstitutes;
	}

	public UserInstitute addUserInstitute(UserInstitute userInstitute) {
		getUserInstitutes().add(userInstitute);
		userInstitute.setUser(this);

		return userInstitute;
	}

	public UserInstitute removeUserInstitute(UserInstitute userInstitute) {
		getUserInstitutes().remove(userInstitute);
		userInstitute.setUser(null);

		return userInstitute;
	}

}