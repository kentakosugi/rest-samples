package jp.co.techmatrix.store.services.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import io.swagger.annotations.Api;
import jp.co.techmatrix.store.model.Book;
import jp.co.techmatrix.store.services.Cart;

/** 
 * RESTやWebサービス、EJBの大元となる抽象クラス
 * @author kosugi
 *
 */
@Named
@RequestScoped
@Transactional
@Path("/cart")
@Api(value = "/cart", produces = MediaType.APPLICATION_JSON + ";charset=UTF-8;")
public class CartResource implements Cart{

	/**
	 * JPAのEntityManager
	 * Glassfishがインジェクションする。
	 * トランザクションもすべて自動管理。
	 */
	@PersistenceContext(unitName = "bookstore")
	private EntityManager em;
	
	/**
	 * @see Cart#getItemByTitle(String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	@GET
	@Path("/get/books")
	@XmlElementWrapper
	@XmlElement(name = "books")
	public List<Book> getItemByTitle(@QueryParam("title") String title){
		return this.em.createNamedQuery(Book.SELECT_ALL_BOOKS_WHERE_TITLE)
				.setParameter("title", "%" + title + "%").getResultList();
	}

	/**
	 * @see Cart#getItemById(int)
	 */
	@GET
	@Path("/get/book")
	@Override
	public Book getItemById(@QueryParam("id") int id){
		return this.em.find(Book.class, id);
	}

	/**
	 * @see Cart#addNewBook(Book)
	 */
	@PUT
	@Path("/add/book")
	@Override
	public void addNewBook(Book book){
		this.em.merge(book);
	}

	/**
	 * @see Cart#deleteBook(int)
	 */
	@DELETE
	@Path("/delete/book")
	@Override
	public void deleteBook(@QueryParam("id") int id){
		Book book = this.em.find(Book.class, id);
		this.em.remove(book);
	}
}
