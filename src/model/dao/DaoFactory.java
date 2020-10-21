package model.dao;

public class DaoFactory {
	
	public static sellerDao.createSellerDao(){
		return new SellerDaoJDBC();
	}
}
