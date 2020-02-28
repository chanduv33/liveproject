package com.capgemini.storesmanagementsystem.service;

import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface CustomerService {
	public boolean buyProduct(DealerInfoBean dealer,CustomerInfoBean customer);
	public CustomerInfoBean getOrderDetails(int id);
}
