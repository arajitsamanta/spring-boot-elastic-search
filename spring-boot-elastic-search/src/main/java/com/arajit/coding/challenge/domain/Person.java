package com.arajit.coding.challenge.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Person details.
 *
 */
public class Person implements Serializable{
  
  private static final long serialVersionUID = 7534754435205146965L;
  private String employeeId;
  private String name;
  private boolean active;
  private String phone;
  private BigDecimal logitude;
  private BigDecimal latitude;
  private int zipCode;
  private String city;
  private String streetAddress;
  private String  email;
  private int age;
  
  /**
   * @return the employeeId
   */
  public String getEmployeeId() {
    return employeeId;
  }
  /**
   * @param employeeId the employeeId to set
   */
  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the active
   */
  public boolean isActive() {
    return active;
  }
  /**
   * @param active the active to set
   */
  public void setActive(boolean active) {
    this.active = active;
  }
  /**
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }
  /**
   * @param phone the phone to set
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }
  /**
   * @return the logitude
   */
  public BigDecimal getLogitude() {
    return logitude;
  }
  /**
   * @param logitude the logitude to set
   */
  public void setLogitude(BigDecimal logitude) {
    this.logitude = logitude;
  }
  /**
   * @return the latitude
   */
  public BigDecimal getLatitude() {
    return latitude;
  }
  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }
  /**
   * @return the zipCode
   */
  public int getZipCode() {
    return zipCode;
  }
  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }
  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }
  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }
  /**
   * @return the streetAddress
   */
  public String getStreetAddress() {
    return streetAddress;
  }
  /**
   * @param streetAddress the streetAddress to set
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * @return the age
   */
  public int getAge() {
    return age;
  }
  /**
   * @param age the age to set
   */
  public void setAge(int age) {
    this.age = age;
  }

}
