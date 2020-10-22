package Application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerdao = DaoFactory.createSellerDao();

		System.out.println("/n==seller findByID==");
		Seller sel1 = sellerdao.findById(3);
		System.out.println(sel1);

		System.out.println("/n==seller findByDepartment==");
		Department department = new Department(2, null);

		List<Seller> sel2 = sellerdao.findByDepartment(department);

		for (Seller seller : sel2) {
			System.out.println(seller);
		}

		System.out.println("/n==seller findAll==");

		sel2 = sellerdao.findAll();

		for (Seller seller : sel2) {
			System.out.println(seller);
		}

		System.out.println(";))");

		System.out.println("/n==seller Insert==");
		
		Seller newSeller = new Seller (null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);

		sellerdao.insert(newSeller);

		System.out.println("Inserido : Id "+ newSeller.getId());

		System.out.println(";))");

	}

}
