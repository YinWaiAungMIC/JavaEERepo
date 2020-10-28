package com.mmit.entity;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity

=======
import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import com.mmit.entity.Category;


@Entity
@NamedQuery(name="Item.getItems",query="SELECT i FROM Item i")
@NamedQuery(name="Item.searchByPrice",query="SELECT i FROM Item i WHERE i.price BETWEEN :val1 AND :val2")
>>>>>>> Java Assignment2
public class Item implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
<<<<<<< HEAD
	private int id;
	private String name;
	private int price;
	@OneToMany(mappedBy = "item")
	private List<Orderdetail> orderdetails;
=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="price")
	private int price;
	
	@ManyToOne(optional = false, cascade = PERSIST)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;
	
	
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


>>>>>>> Java Assignment2
	public int getId() {
		return id;
	}

<<<<<<< HEAD
=======

>>>>>>> Java Assignment2
	public void setId(int id) {
		this.id = id;
	}

<<<<<<< HEAD
=======

>>>>>>> Java Assignment2
	public String getName() {
		return name;
	}

<<<<<<< HEAD
=======

>>>>>>> Java Assignment2
	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
=======

>>>>>>> Java Assignment2
	public int getPrice() {
		return price;
	}

<<<<<<< HEAD
=======

>>>>>>> Java Assignment2
	public void setPrice(int price) {
		this.price = price;
	}

<<<<<<< HEAD
=======

	


>>>>>>> Java Assignment2
	public Item() {
		super();
	}
   
}
