package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.controller.MemberController;
import com.mycompany.webapp.dao.MemberDao;
import com.mycompany.webapp.dto.Cart;
import com.mycompany.webapp.dto.Coupon;
import com.mycompany.webapp.dto.Member;
import com.mycompany.webapp.dto.QnA;
import com.mycompany.webapp.dto.WishList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	// 열거 타입 선언
	public enum JoinResult {
		SUCCESS, FAIL, DUPLICATED
	}

	public enum LoginResult {
		SUCCESS, FAIL_MID, FAIL_MPASSWORD, FAIL
	}

	@Resource
	private MemberDao memberDao;

	// 회원 가입을 처리하는 비즈니스 메소드(로직)
	public JoinResult join(Member member) {
		try {
			// 이미 가입된 아이디인지 확인
			Member dbMember = memberDao.selectByMid(member.getMid());

			// DB에 회원 정보를 저장
			if (dbMember == null) {
				memberDao.insert(member);
				return JoinResult.SUCCESS;
			} else {
				return JoinResult.DUPLICATED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JoinResult.FAIL;
		}
	}

	public LoginResult login(Member member) {
		try {
			// 이미 가입된 아이디인지 확인
			Member dbMember = memberDao.selectByMid(member.getMid());

			// 확인 작업
			if (dbMember == null) {
				return LoginResult.FAIL_MID;
			} else if (!dbMember.getMpassword().equals(member.getMpassword())) {
				return LoginResult.FAIL_MPASSWORD;
			} else {
				return LoginResult.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return LoginResult.FAIL;
		}
	}
	
	public Member selectInfo(String mid) {
		return memberDao.selectInfoByMid(mid);
	}

	public List<WishList> selectWishlist(String mid) {
		return memberDao.selectWishlist(mid);
	}

	public List<Coupon> selectCouponlist(String mid) {
		return memberDao.selectCouponlist(mid);
	}

	public List<Cart> selectMycart(String mid) {
		return memberDao.selectMycart(mid);
	}

	public List<QnA> selectQnaList(String mid) {
		return memberDao.selectQnaList(mid);
	}
	
	public int createWishList(WishList wish) {
		log.info("createService");
		return memberDao.createWishList(wish);
	}
	
	public void deleteWishList(WishList wish) {
		memberDao.deleteWishList(wish);
	}

	public void deleteCartItem(Cart mycart) {
		memberDao.deleteCartItem(mycart);
	}

	public void updateCart(Cart updatecart) {
		memberDao.updateCart(updatecart);
		
	}
	
	public int createCoupon(Coupon coupon) {
		return memberDao.createCoupon(coupon);
	}

	public void addtocart(Cart mycart) {
		memberDao.insertCart(mycart);
	}
	
	public int updateCoupon(Coupon coupon) {
		return memberDao.updateCoupon(coupon);
	}
	
	public int updatePoint(Member member) {
		return memberDao.updatePoint(member);
	}
}
