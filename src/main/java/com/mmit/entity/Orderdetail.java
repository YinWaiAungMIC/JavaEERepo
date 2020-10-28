package com.mmit.entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

/**
 * Entity implementation class for Entity: Orderdetail
 *
 */
@Entity

public class Orderdetail implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int subQty;
	private int subPrice;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSubQty() {
		return subQty;
	}


	public void setSubQty(int subQty) {
		this.subQty = subQty;
	}


	public int getSubPrice() {
		return subPrice;
	}


	public void setSubPrice(int subPrice) {
		this.subPrice = subPrice;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Orders getOrder() {
		return order;
	}


	public void setOrder(Orders order) {
		this.order = order;
	}


	public Orderdetail() {
		super();
	}
   
}
