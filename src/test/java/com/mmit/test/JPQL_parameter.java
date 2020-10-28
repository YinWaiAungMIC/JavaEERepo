package com.mmit.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mmit.DTO.ItemCategory;
import com.mmit.DTO.ItemPriceDTO;
import com.mmit.entity.Item;

class JPQL_parameter {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf=Persistence.createEntityManagerFactory("jpa-em-transaction");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em=emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	void testForGroupBy() {
		
		String sql="SELECT new com.mmit.DTO.ItemPriceDTO(i1.price,i1.name) FROM Item i1 WHERE i1.price IN(SELECT max(i2.price) FROM Item i2 GROUP BY i2.category.id)";
		TypedQuery<ItemPriceDTO> query=em.createQuery(sql,ItemPriceDTO.class);
		
		
		List<ItemPriceDTO> items=query.getResultList();
		items.forEach(i->System.out.println(i.getName()+"\t"+i.getPrice()));//Result ::Sprite	1200 Pizza	13500
		
	}

	//@Test
	void testForNameParameter() {
	TypedQuery<Item> query=em.createQuery("SELECT i FROM Item i WHERE i.price> ?1", Item.class);
	//query.setParameter("pri", 1000);
	query.setParameter(1,1000);
	List<Item> items=query.getResultList();
	items.forEach(i->{
		System.out.println(i.getId()+"\t"+i.getName()+"\t"+i.getPrice());
	});
	}
	
	//@Test
	void testForAggregateFunc() {
		TypedQuery<Integer> query=em.createQuery("SELECT MAX(i.price) From Item i", Integer.class);
		//List<Integer>list=query.getResultList();
		Integer max_price=query.getSingleResult();
		//System.out.println("Max Price:"+list.get(0));
		System.out.println("Max Price:"+max_price);
	}

	//@Test
	void testForLike_Operator() {
		TypedQuery<String> query=em.createQuery("SELECT i.name FROM Item i WHERE i.name LIKE :name", String.class);
		query.setParameter("name", "%o%");
		List<String> names=query.getResultList();
		names.forEach(s->System.out.println(s));
	}
	
	//@Test
	void testForBetween_In() {
		TypedQuery<Item> query=em.createNamedQuery("Item.searchByPrice", Item.class);
		List<Item> items=query.getResultList();
		query.setParameter("val1", 1000);
		query.setParameter("val2", 2000);
		items.forEach(i->{
			System.out.println(i.getName()+"\t"+i.getCategory().getName());
		});
	}
	
	//@Test
	void testForIn() {
		TypedQuery<Item> query=em.createQuery("SELECT i FROM Item i WHERE i.category.id IN :ids", Item.class);
		List<Integer> values=Arrays.asList(1,2);
		query.setParameter("ids", values);
		List<Item> items=query.getResultList();
		items.forEach(i->
		System.out.println(i.getName()+"\t"+i.getCategory().getId()));
	}
	//@Test
	void testForJoin() {
		/*
		 * String
		 * sql="SELECT new com.mmit.DTO.ItemPriceDTO(i.price,i.name) FROM Item i WHERE i.category.name=:catName"
		 * ; TypedQuery<ItemPriceDTO> query=em.createQuery(sql,ItemPriceDTO.class);
		 * query.setParameter("catName", "Juices"); List<ItemPriceDTO>
		 * items=query.getResultList(); items.forEach(i-> System.out.println(i+"\t"));
		 */
		
		String sql="SELECT new com.mmit.DTO.ItemPriceDTO(i.price,i.name) FROM Item i WHERE i.category.name = :catName";
		TypedQuery<ItemPriceDTO> query=em.createQuery(sql,ItemPriceDTO.class);
		query.setParameter("catName", "Juices");
		
		List<ItemPriceDTO> items=query.getResultList();
		items.forEach(i->System.out.println(i.getName()+"\t"+i.getPrice()));
	}
	
	
	
	//@Test
	void testForNew_Op() {
		String sql="SELECT new com.mmit.DTO.ItemCategory(i,i.category.name) FROM Item i";
		TypedQuery<ItemCategory> query=em.createQuery(sql,ItemCategory.class);
		
		List<ItemCategory> items=query.getResultList();
		items.forEach(i->System.out.println(i.getItem().getName()+"\t"+i.getCategoryName()));
		
	}
}
