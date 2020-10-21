package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
	    this.conn=conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement pS = null;
		ResultSet rS = null;
		
		try {
			pS =  conn.prepareStatement("select seller.*, department.Name as DepName " +
										"from seller inner join department " +
										"on seller.DepartmentId = department.Id " +
										"where DepartmentId = ? "+
										"order by name");
			pS.setInt(1, id);
			rS= pS.executeQuery();
		
			if (rS.next()) {  // testa se exist vendedor na lista
				Department dep = new Department();
				dep.setId(rS.getInt("DepartmentId"));
				dep.setName(rS.getString("DepName"));
				
				Seller obj = new Seller();
				obj.setId(rS.getInt("Id"));
				obj.setName(rS.getString("Name"));
				obj.setEmail(rS.getString("Email"));
				obj.setBaseSalary(rS.getDouble("BaseSalary"));
				obj.setBirthDate(rS.getDate("BirthDate"));
				obj.setDepartment(dep);
				return obj;
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeAll(pS, rS);
		}
	}

	@Override
	public List<Seller> findAll(Seller obj) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
