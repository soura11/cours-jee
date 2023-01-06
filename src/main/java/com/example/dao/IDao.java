package com.example.dao;

import java.sql.SQLException;
import java.util.List;

// TODO: Auto-generated Javadoc
//DAO -> Data Access Object

//declaration de methodes CRUD 
/**
 * The Interface IDao.
 *
 * @param <T> the generic type
 */
// (create-read-update-delete)
public interface IDao<T> {

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ClassNotFoundException the class not found exception
	 */
	List<T> findAll() throws ClassNotFoundException;
	
	/**
	 * Save.
	 *
	 * @param o the o
	 * @return the t
	 * @throws ClassNotFoundException the class not found exception
	 */
	T save(T o) throws ClassNotFoundException;
	
	/**
	 * Update.
	 *
	 * @param o the o
	 * @return the t
	 * @throws ClassNotFoundException the class not found exception
	 */
	T update(T o) throws ClassNotFoundException;
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 * @throws ClassNotFoundException the class not found exception
	 */
	T findById(int id) throws ClassNotFoundException;
	
	/**
	 * Removes the.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 */
	boolean remove(int id)throws ClassNotFoundException;	
	
	/**
	 * Insert and find all.
	 *
	 * @param o the o
	 * @return the list
	 * @throws ClassNotFoundException the class not found exception
	 */
	List<T> insertAndFindAll(T o) throws ClassNotFoundException;
}
