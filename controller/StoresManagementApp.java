package com.capgemini.storesmanagementsystem.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.capgemini.storesmanagementsystem.dao.AdminDAO;
import com.capgemini.storesmanagementsystem.dao.AdminDAOImpl;
import com.capgemini.storesmanagementsystem.dao.DealerDAO;
import com.capgemini.storesmanagementsystem.dao.DealerDAOImpl;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAO;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAOImpl;
import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.AdminService;
import com.capgemini.storesmanagementsystem.service.AdminServiceImpl;
import com.capgemini.storesmanagementsystem.service.CustomerService;
import com.capgemini.storesmanagementsystem.service.CustomerServiceImpl;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;

public class StoresManagementApp {
	public static void main(String[] args) {
		System.out.println("\t \t Welcome to Stores Management System Appication  ");
		Scanner sc = new Scanner(System.in);
		AdminService adminSer = new AdminServiceImpl();
		DealerService dealerSer = new DealerServiceImpl();
		ManufacturerService manSer = new ManufacturerServiceImpl();
		CustomerService cusSer = new CustomerServiceImpl();
		while (true) {
			System.out.println("Who You are..??");
			System.out.println("Available options \n " + "1. Admin \n " + "2. Manufacturer \n " + "3. Dealer \n "
					+ "4. Customer \n " + "5. Exit \n " + "Enter Your Choice");
			System.out.println("========================================================");

			// int choice = sc.nextInt();
			try {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Welcome Admin");
					while (true) {
						System.out.println("Operation you would like to perform ?");
						System.out.println(" 1. Add Manufacturer \n " + "2. Update Manufacturer Details \n "
								+ "3. Get Manufacturer Details \n " + "4. Get All Manufacturers Details \n "
								+ "5. Exit as Admin");
						System.out.println("Enter Your Choice");
						System.out.println("========================================================");
						try {
							int adminChoice = sc.nextInt();
							switch (adminChoice) {
							case 1:
								ManufacturerInfoBean manufacturer = new ManufacturerInfoBean();
								System.out.println("Enter Manufacturer Name");
								manufacturer.setManufacturerName(sc.next());
								System.out.println("Add Product");

								ProductInfoBean product = new ProductInfoBean();
								System.out.println("Enter Product Id");
								product.setProductId(sc.nextInt());
								System.out.println("Enter Product Cost");
								double productCost = sc.nextDouble();
								product.setCostPrice(productCost);
								manufacturer.setProductCost(productCost);
								System.out.println("Enter Product Name");
								product.setProductName(sc.next());

								System.out.println("Enter Description about Product");
								manufacturer.setDescription(sc.next());
								System.out.println("Enter Manufacturer Id");
								manufacturer.setManufacturerId(sc.nextInt());

								manufacturer.setProduct(product);
								product.setManufacturer(manufacturer);

								if (adminSer.addManufacturer(manufacturer)) {
									System.out.println("Manufacturer Added Successfully");
								} else {
									System.out.println("Adding Manufacturer Failed");
								}
								break;
							case 3:
								System.out.println("Enter Manufacturer Id");
								int manId = sc.nextInt();
								ManufacturerInfoBean man = adminSer.getManufacturerDetails(manId);
								System.out.println(
										"==========================================================================="
												+ "=====================================");
								System.out.println(" ManufacturerName = " + man.getManufacturerName() + " \t Product = "
										+ man.getProduct().getProductName() + " \t Description = "
										+ man.getDescription() + " \t Product Cost = " + man.getProductCost());
								break;
							case 2:
								ManufacturerInfoBean bean = new ManufacturerInfoBean();
								System.out.println("Enter Id to Update");
								bean.setManufacturerId(sc.nextInt());
								System.out.println("Enter New Name for Manufacturer");
								bean.setManufacturerName(sc.next());
								System.out.println("Enter new Description");
								bean.setDescription(sc.next());
								if (adminSer.updateManufacturerDetails(bean) != null) {
									System.out.println("Updated Successfully");
								} else {
									System.out.println("Updation Failed");
								}

								break;
							case 4:
								System.out.println(
										"==========================================================================="
												+ "=====================================");
								List<ManufacturerInfoBean> manufacturers = adminSer.getAllManufacturersDetails();
								Iterator<ManufacturerInfoBean> itr = manufacturers.iterator();
								while (itr.hasNext()) {
									ManufacturerInfoBean mans = itr.next();
									System.out.println(" ManufacturerName = " + mans.getManufacturerName()
											+ " \t || Product = " + mans.getProduct().getProductName()
											+ " \t || Description = " + mans.getDescription() + " \t || Product Cost = "
											+ mans.getProductCost());
								}
								System.out.println(
										"==========================================================================="
												+ "=====================================");
								break;
							case 5:
								break;
							}
						} catch (InputMismatchException e) {
							try {
								throw new EnterValidInputException();
							} catch (EnterValidInputException exp) {
								System.out.println(exp.getMessage());
							}

						}

						break;
					}
					break;
				case 2:
					System.out.println("Welcome Manufacturer");
					System.out.println("Operation you would like to perform ?");
					System.out.println("1. Add Dealer \n " + "2. Set CostPrice \n" + " " + "3. Get Payment Details \n "
							+ "4. Exit as Manufacturer");
					System.out.println("Enter Your Choice");
					System.out.println("========================================================");
					try {
						int manufacturerChoice = sc.nextInt();
						switch (manufacturerChoice) {
						case 1:
							DealerInfoBean dealer = new DealerInfoBean();
							System.out.println("Enter Dealer Name");
							dealer.setDealerName(sc.next());
							System.out.println("Enter Dealer Id");
							dealer.setDealerId(sc.nextInt());
							if (manSer.addDealer(dealer)) {
								System.out.println("Dealer Added Successfully");
							} else {
								System.out.println("adding dealer has been failed");
							}
							break;
						case 2:
							System.out.println("Enter Product id");
							ProductInfoBean product = new ProductInfoBean();
							product.setProductId(sc.nextInt());
							System.out.println("Enter new Cost Price");
							product.setCostPrice(sc.nextDouble());
							manSer.setCostPrice(product);
							break;
						case 3:
							System.out.println("Enter Order Id to get Payment Details");
							ProductInfoBean bean = manSer.getPaymentDetails(sc.nextInt());
							System.out.println(
									" Order Id " + bean.getOrderId() + " \t ProductName " + bean.getProductName()
											+ " \t Date Of Order " + bean.getDateOfOrder() + " \t Amount "
											+ bean.getAmount() + " \t Date of Delivery " + bean.getDateOfDelivery());
						}
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
						}

					}

					break;
				case 3:
					System.out.println("Welcome Dealer");
					System.out.println("Operation you would like to perform ?");
					System.out.println(" 1. Place Order \n " + "2. Set Selling Price \n" + "3. Get Payment Details \n "
							+ "4. Exit as Dealer");
					System.out.println("Enter Your Choice");
					System.out.println("========================================================");
					try {
						int dealerChoice = sc.nextInt();
						switch (dealerChoice) {
						case 1:
							System.out.println("Enter Product");
							String productName = sc.next();
							System.out.println("Enter Manufacturer Name");
							String manName = sc.next();
							System.out.println("Enter Quantity");
							int quantity = sc.nextInt();
							System.out.println("Enter Dealer Id");
							int id = sc.nextInt();
							Iterator<ManufacturerInfoBean> itr = CollectionDbClass.manufacturerSet.iterator();
							while (itr.hasNext()) {
								ManufacturerInfoBean manBean = itr.next();
								if (manBean.getManufacturerName().equalsIgnoreCase(manName)
										&& manBean.getProduct().getProductName().equalsIgnoreCase(productName)) {
									System.out.println("Enter Order Id");
									manBean.getProduct().setOrderId(sc.nextInt());
									dealerSer.placeOrder(manBean.getProduct(), quantity, id);
								}
							}
							break;
						case 2:
							DealerInfoBean dealer = new DealerInfoBean();
							System.out.println("Enter Selling Price");
							dealer.setSellingPrice(sc.nextDouble());
							System.out.println("Enter Dealer Id");
							dealer.setDealerId(sc.nextInt());
							System.out.println("Enter Product Id");
							dealer.getProduct().setProductId(sc.nextInt());
							dealerSer.setSellingPrice(dealer);
							break;
						}
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
						}

					}

					break;
				case 4:
					System.out.println("Welcome Customer");
					System.out.println("Available Choices are..");
					System.out.println(" 1. Buy Product \n " + "2. Get Order Dealits \n " + "3. Exit");
					System.out.println("Enter Your Choice");
					System.out.println("========================================================");
					try {
						int customerChoice = sc.nextInt();
						switch (customerChoice) {

						case 1:
							Set<ProductInfoBean> prods = CollectionDbClass.productSet;
							Iterator<ProductInfoBean> itr = prods.iterator();
							while (itr.hasNext()) {
								System.out.println("Available Products are.");
								System.out.println(itr.next().getProductName() + "	\t Dealers "
										+ itr.next().getDealer().getDealerName());
							}
							System.out.println("Enter Product You Want to Buy");
							DealerInfoBean dealer = new DealerInfoBean();
							ProductInfoBean product = new ProductInfoBean();
							product.setProductName(sc.next());
							dealer.setProduct(product);
							System.out.println("Enter Dealer Name");
							dealer.setDealerName(sc.next());
							CustomerInfoBean customer = new CustomerInfoBean();
							System.out.println("Enter Customer Id");
							customer.setCustomerId(sc.nextInt());
							System.out.println("Enter Order Id");
							customer.setOrderId(sc.nextInt());
							cusSer.buyProduct(dealer, customer);
							break;
						case 2:
							System.out.println("Enter Order Id to get Payment Details");
							int oid = sc.nextInt();
							CustomerInfoBean bean = cusSer.getOrderDetails(oid);
							System.out.println(" Order Id " + bean.getOrderId() + " \t ProductName "
									+ bean.getProduct().getProductName() + " \t Date Of Order " + bean.getDateOfOrder()
									+ " \t Amount " + bean.getAmount());
							break;
						case 3:
							break;
						}
					} catch (InputMismatchException e) {
						try {
							throw new EnterValidInputException();
						} catch (EnterValidInputException exp) {
							System.out.println(exp.getMessage());
						}

					}

					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Invalid Choice");
				}

			} catch (InputMismatchException e) {

				// System.out.println("Enter valid Input");

				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.out.println(exp.getMessage());
				}

				break;
			}
		}
	}
}
