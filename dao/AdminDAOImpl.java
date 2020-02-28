package com.capgemini.storesmanagementsystem.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;

public class AdminDAOImpl implements AdminDAO{

	@Override
	public boolean addManufacturer(ManufacturerInfoBean manufacturer) {
		  System.out.println(manufacturer);
		  CollectionDbClass.productSet.add(manufacturer.getProduct());
		  CollectionDbClass.manufacturerSet.add(manufacturer);
		  return true;
	}

	@Override
	public ManufacturerInfoBean updateManufacturerDetails(ManufacturerInfoBean manufacturer) {
		ManufacturerInfoBean found;
		Iterator<ManufacturerInfoBean> itr =  CollectionDbClass.manufacturerSet.iterator();
		while(itr.hasNext()) {
			found = itr.next();
			if(found.getManufacturerId()==manufacturer.getManufacturerId()) {
				found = manufacturer;
				return found;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public ManufacturerInfoBean getManufacturerDetails(int id) {
		ManufacturerInfoBean found;
		Iterator<ManufacturerInfoBean> itr =  CollectionDbClass.manufacturerSet.iterator();
		while(itr.hasNext()) {
			found = itr.next();
			if(found.getManufacturerId()==id) {
				return found;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public List<ManufacturerInfoBean> getAllManufacturersDetails() {
		return new ArrayList<ManufacturerInfoBean>(CollectionDbClass.manufacturerSet);
	}

}
