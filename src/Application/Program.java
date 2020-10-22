package Application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerdao = DaoFactory.createSellerDao();
		System.out.println("==seller findByID==");
		Seller sel1  = sellerdao.findById(3);
		System.out.println(sel1);
		System.out.println("==seller findByDepartment==");
		Department department = new Department(2,null);
		
		List <Seller> sel2  = sellerdao.findByDepartment(department);
		
	    for (Seller seller : sel2) {
	    	System.out.println(seller);
		}
	    
		System.out.println(";))");
		
	}

}
