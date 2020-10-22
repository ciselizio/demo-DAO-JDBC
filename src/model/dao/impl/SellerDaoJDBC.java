package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Seller result = null;

		try {
			pS =  conn.prepareStatement("select seller.*, department.Name as DepName " +
										"from seller inner join department " +
										"on seller.DepartmentId = department.Id " +
										"where DepartmentId = ? "+
										"order by name");
			pS.setInt(1, id);
			rS= pS.executeQuery();
		
			if (rS.next()) {  // testa se exist vendedor na lista
				Department dep = instantiateDepartment(rS);
				Seller obj = instantiateSeller(rS, dep);
				result = obj;
			}else {
				result = null;
			}
			
			return result;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeAll(pS, rS, false);
		}
	}

	private Seller instantiateSeller(ResultSet rS, Department dep) throws SQLException {
		    Seller obj = new Seller();
			obj.setId(rS.getInt("Id"));
			obj.setName(rS.getString("Name"));
			obj.setEmail(rS.getString("Email"));
			obj.setBaseSalary(rS.getDouble("BaseSalary"));
			obj.setBirthDate(rS.getDate("BirthDate"));
			obj.setDepartment(dep);		
			return obj;
	}

	private Department instantiateDepartment(ResultSet rS) throws SQLException {
		Department dep = new Department();
		dep.setId(rS.getInt("DepartmentId"));
		dep.setName(rS.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement pS = null;
		ResultSet rS = null;

		try {
			pS =  conn.prepareStatement("select seller.*, department.Name as DepName " +
										"from seller inner join department " +
										"on seller.DepartmentId = department.Id " +
										"order by name");
			
			rS= pS.executeQuery();
			
			List <Seller> list = new ArrayList<>();
			Map <Integer, Department> map = new HashMap<>();
		
			while (rS.next()) {  // testa se exist departamento na lista
				
				Department dep = map.get(rS.getInt("DepartmentId"));
				
				if (dep==null) {
				    dep = instantiateDepartment(rS);
				    map.put(rS.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rS, dep);
				list.add(obj);
			}
			
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeAll(pS, rS, false);
		}
	}

	@Override
	public List <Seller> findByDepartment(Department department) {
		PreparedStatement pS = null;
		ResultSet rS = null;

		try {
			pS =  conn.prepareStatement("select seller.*, department.Name as DepName " +
										"from seller inner join department " +
										"on seller.DepartmentId = department.Id " +
										"where DepartmentId = ? "+
										"order by name");
			
			pS.setInt(1, department.getId());
			rS= pS.executeQuery();
			
			List <Seller> list = new ArrayList<>();
			Map <Integer, Department> map = new HashMap<>();
		
			while (rS.next()) {  // testa se exist departamento na lista
				
				Department dep = map.get(rS.getInt("DepartmentId"));
				
				if (dep==null) {
				    dep = instantiateDepartment(rS);
				    map.put(rS.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rS, dep);
				list.add(obj);
			}
			
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeAll(pS, rS, false);
		}
	}
	

}
