package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Cart;
import com.mycompany.webapp.dto.Coupon;
import com.mycompany.webapp.dto.Member;
import com.mycompany.webapp.dto.QnA;
import com.mycompany.webapp.dto.WishList;

@Mapper
public interface MemberDao {
	public int insert(Member member);	
	public Member selectByMid(String mid);
	public Member selectInfoByMid(String mid);
	public List<WishList> selectWishlist(String mid);
	public List<Coupon> selectCouponlist(String mid);
	public List<Cart> selectMycart(String mid);
	public List<QnA> selectQnaList(String mid);
	public int createWishList(WishList wish);
	public int deleteWishList(WishList wish);
	public int deleteCartItem(Cart mycart);
	public int updateCart(Cart updatecart);
	public int createCoupon(Coupon coupon);
	public int insertCart(Cart mycart);
	public int updateCoupon(Coupon coupon);
	public int updatePoint(Member member);
	public int selectMycartAmount(String mid);
}