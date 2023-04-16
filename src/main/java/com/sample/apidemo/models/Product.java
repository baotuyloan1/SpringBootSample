package com.sample.apidemo.models;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * @author BAO
 *
 */
@Entity
@Table(name = "tblProduct")
public class Product {
	@Id
	@SequenceGenerator(name = "product_sequence1", sequenceName = "product_sequence2", allocationSize = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence1")
	private Long id;

	@Column(nullable = false, unique = true, length = 300)
	private String productName;
	@Column(name = "yearr")
	@Min(1950)
	@Max(2023)
	private int year;
	private Double price;
	private String url;

	public Product() {
		super();
	}

//	calculated field = transient
	@Transient
	private int age;// age is calculated from "year"

	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - year;
	}

	public Product(String productName, int year, Double price, String url) {
		this.productName = productName;
		this.year = year;
		this.price = price;
		this.url = url;
	}

	@Override
	public String toString() {
		return "Product{id=" + id + ", productName=" + productName + ", year=" + year + ", price=" + price + ", url="
				+ url + "}";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
