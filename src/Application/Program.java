package Application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerdao = DaoFactory.createSellerDao();
		System.out.println("==seller findByID==");
		Seller sel  = sellerdao.findById(3);
		System.out.println(sel);
		System.out.println(";))");
		
	}

}
