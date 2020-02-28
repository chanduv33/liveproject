package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class DealerDAOImpl implements DealerDAO{
	
	private ProductInfoBean product;
	
	@Override
	public boolean placeOrder(ProductInfoBean product, int quantity,int id) {
		
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while(itr.hasNext()) {
			DealerInfoBean bean  = itr.next();
			if(bean.getDealerId()==id) {
				bean.setQuantity(quantity);
				LocalDate date  =  LocalDate.now();
				product.setDateOfOrder(date);
				product.setDateOfDelivery(LocalDate.now().plusDays(2));
				product.setDealer(bean);
				bean.setProduct(product);
				product.setAmount(quantity*product.getCostPrice());
				System.out.println(product);
				return true;
			}	
		}
		return false;
	}

	@Override
	public boolean setSellingPrice(DealerInfoBean dealer) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while(itr.hasNext()) {
			DealerInfoBean bean  = itr.next();
			if(bean.getDealerId()==dealer.getDealerId()) {
				Iterator<ProductInfoBean> itr1 = CollectionDbClass.productSet.iterator();
				while(itr1.hasNext()) {
					ProductInfoBean prodBean = itr1.next();
					if(prodBean.getProductId()==dealer.getProduct().getProductId()) {
						bean.setSellingPrice(dealer.getSellingPrice());
						return true;
					}
				}
			}	
		}
		return false;
	}

	public void checkQuantity(DealerInfoBean dealer) {
		int quantity = dealer.getQuantity()-1;
		dealer.setQuantity(quantity);
		if(dealer.getQuantity()<=dealer.getMinimumQuantity()) {
			autoBuyStocks(dealer.getProduct(), dealer.getQuantity()*2);
		}
	}

	
	public void autoBuyStocks(ProductInfoBean product, int quantity) {
		int newOid =product.getOrderId()+1;
		placeOrder(product, quantity, newOid);
	}
}
