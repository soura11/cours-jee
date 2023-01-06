package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.IDao;
import com.example.models.User;
import com.example.utils.MyConnection;

public class UserDaoImpl implements IDao<User> {
	
	private MyConnection connection;
	
	public UserDaoImpl(MyConnection connection) {
		this.connection = connection;
	}

	// Ici, on retoune une collection de type User de la db
	public List<User> findAll() throws ClassNotFoundException {
		List<User> users = new ArrayList<User>();
		Connection c = null;
		try {
			c = connection.getConnection();
			// Preparation de la requete
			String request = "SELECT * FROM user";
			// creation de la requete (PreparedStatement =>
			// contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request);
			// Execution de la requete
			ResultSet r = ps.executeQuery();
			// Recuperation des donnees
			while (r.next()) {
				User u = new User();
				// on indique chaque fois le nom de la colonne et le type
				u.setId(r.getInt("id"));
				u.setFirstName(r.getString("firstname"));
				u.setLastName(r.getString("lastname"));
				u.setSalary(r.getDouble("salary"));

				users.add(u);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public User save(User o) throws ClassNotFoundException {
		Connection c = null;
		try {
			c = connection.getConnection();
			// Preparation de la requete
			String request = "INSERT INTO user (firstname, lastname, salary) VALUES (?,?,?)";
			// creation de la requete (PreparedStatement => contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, o.getFirstName());
			ps.setString(2, o.getLastName());
			ps.setDouble(3, o.getSalary());

			// Execution de la requete
			ps.executeUpdate();

			ResultSet resultat = ps.getGeneratedKeys();
			if (resultat.next()) {
				o.setId(resultat.getInt(1));
				return o;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public User update(User o) throws ClassNotFoundException {
		Connection c = null;
		try {
			c = connection.getConnection();

			String request = "UPDATE user SET firstname = ?, lastname = ?, salary = ? WHERE id = ?";

			PreparedStatement ps = c.prepareStatement(request);
			ps.setString(1, o.getFirstName());
			ps.setString(2, o.getLastName());
			ps.setDouble(3, o.getSalary());
			ps.setInt(4, o.getId());

			int count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("Updated queries: " + count);
				return o;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public User findById(int id) throws ClassNotFoundException {
		Connection c = null;
		try {
			c = connection.getConnection();
			// Preparation de la requete
			String request = "SELECT * FROM user WHERE id = ?;";
			// creation de la requete (PreparedStatement => contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request);

			ps.setInt(1, id);
			// Execution de la requete
			ResultSet r = ps.executeQuery();

			// Recuperation des donnees
			if (r.next()) {
				return new User(id, r.getString("firstname"), r.getString("lastname"), r.getDouble("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean remove(int id) throws ClassNotFoundException {
		Connection c = null;
		try {
			c = connection.getConnection();
			// Preparation de la requete
			String request = "DELETE FROM user WHERE id = ?";
			// creation de la requete (PreparedStatement => contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows == 1) {
				System.out.println("Le tuple a bien ete supprime!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	// Creation d'une transaction
	// Une transaction traite une seule instruction SQL ou un groupe
	// d instructions SQL comme une unit� logique, et si une instruction echoue,
	// la transaction entiere echoue.
	@Override
	public List<User> insertAndFindAll(User o) throws ClassNotFoundException {
		List<User> users = new ArrayList<User>();
		Connection c = null;
		try {
			c = connection.getConnection();
			// definir la gestion des transactions manuelles
			c.setAutoCommit(false);

			// Preparation de la requete
			String request1 = "INSERT INTO user (firstname, lastname, salary) VALUES (?,?,?)";
			// creation de la requete (PreparedStatement => contre les injections SQL)
			PreparedStatement ps = c.prepareStatement(request1);

			ps.setString(1, o.getFirstName());
			ps.setString(2, o.getLastName());
			ps.setDouble(3, o.getSalary());

			// Execution de la requete
			ps.executeUpdate();

			// Preparation de la requete
			String request2 = "SELECT * FROM user";
			// creation de la requete (PreparedStatement =>
			// contre les injections SQL)
			PreparedStatement ps2 = c.prepareStatement(request2);
			// Execution de la requete
			ResultSet r = ps2.executeQuery();
			// Recuperation des donnees
			while (r.next()) {
				User u = new User();
				// on indique chaque fois le nom de la colonne et le type
				u.setId(r.getInt("id"));
				u.setFirstName(r.getString("firstname"));
				u.setLastName(r.getString("lastname"));
				u.setSalary(r.getDouble("salary"));

				users.add(u);
			}

			// S'il n'y a pas d'erreur.
			c.commit();

			return users;
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.stop();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
