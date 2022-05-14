package proiect.Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	public static List <Sistems> cartList = new ArrayList<Sistems>();
	public static List <Sales> cartListSales = new ArrayList<Sales>();
	public static SpecialCommand cartListSpecial;

	public static List<Sales> getCartListSales() {
		return cartListSales;
	}

	public static void setCartListSales(List<Sales> cartListSales) {
		Cart.cartListSales = cartListSales;
	}

	public static List<Sistems> getCartList() {
		return cartList;
	}

	public static void setCartList(List<Sistems> cartList) {
		Cart.cartList = cartList;
	}

	public static SpecialCommand getCartListSpecial() {
		return cartListSpecial;
	}

	public static void setCartListSpecial(SpecialCommand cartListSpecial) {
		Cart.cartListSpecial = cartListSpecial;
	}
	
	
}
