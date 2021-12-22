package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Cart;
import com.mycompany.webapp.dto.Coupon;
import com.mycompany.webapp.dto.Event;
import com.mycompany.webapp.dto.Member;
import com.mycompany.webapp.dto.QnA;
import com.mycompany.webapp.dto.WishList;
import com.mycompany.webapp.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

	@Resource
	private MemberService memberService;
	
	@Resource
	private EventController eventController;

//	@Resource
//	private AuthenticationManager authenticationManager;

//	@RequestMapping("sendAuth")
//	public Map<String, String> sendAuth(@RequestHeader("Authorization") String authorization) {
//	   log.info("실행");
//	   log.info(authorization);
//	   String jwt = authorization.substring(7);
//	   Map<String, String> map = new HashMap<>();
//	   map.put("jwt", jwt);
//	   return map;
//	}
//	
	// 회원정보 보기
	@GetMapping("/info")
	public Member getInfo(HttpServletRequest request) {
		log.info("실행");

//		String jwt = request.getHeader("Authorization").substring(7);
//		Claims claims = JwtUtil.validateToken(jwt);
//		String mid = JwtUtil.getMid(claims);
		
		String mid = request.getAttribute("mid").toString();
		Member member = memberService.selectInfo(mid);

		return member;
	}

	// mid로 위시리스트 받아오기
	@GetMapping("/wishlist")
	public List<WishList> getWishList(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<WishList> wishList = memberService.selectWishlist(mid);
		return wishList;
	}

	// mid로 보유중인 쿠폰 받아오기
	@GetMapping("/coupon")
	public List<Coupon> getCouponList(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<Coupon> couponList = memberService.selectCouponlist(mid);
		return couponList;
	}

	// mid로 qna목록 받아오기
	@GetMapping("/qna")
	public List<QnA> getqnaList(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<QnA> qnaList = memberService.selectQnaList(mid);
		log.info(mid);
		log.info(qnaList.toString());
		return qnaList;
	}

	// 장바구니 불러오기
	@GetMapping("/mycart")
	public List<Cart> getMycart(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		List<Cart> mycart = memberService.selectMycart(mid);
		return mycart;
	}
	
	//mid, pid로 wishlist 추가
	@PostMapping("/createwishlist")
	public int createWishList(HttpServletRequest request, String pid) {
		String mid = request.getAttribute("mid").toString();
		WishList wishList = new WishList();
		wishList.setMid(mid);
		wishList.setPid(pid);
		log.info(mid);
		log.info(pid);
		return memberService.createWishList(wishList);
	}
	
	//mid, pid로 wishlist 삭제
	@DeleteMapping("/deletewishlist")
	public void deleteWishList(HttpServletRequest request, String pid) {
		String mid = request.getAttribute("mid").toString();
		WishList wishList = new WishList();
		wishList.setMid(mid);
		wishList.setPid(pid);
		log.info(mid);
		log.info(pid);
		memberService.deleteWishList(wishList);
	}
	
	//장바구니 수정
	@PostMapping("/update")
	public void updateMyCart(HttpServletRequest request, Cart cart) {
		String mid = request.getAttribute("mid").toString();
		Cart updatecart = new Cart();
		updatecart.setMid(mid);
		updatecart.setPid(cart.getPid());
		updatecart.setPcolor(cart.getPcolor());
		updatecart.setPsize(cart.getPsize());
		updatecart.setAftercolor(cart.getAftercolor());
		updatecart.setAftersize(cart.getAftersize());
		updatecart.setPamount(cart.getPamount());
		memberService.updateCart(updatecart);
	}
	
	//장바구니 삭제
	@DeleteMapping("/{pid}/{pcolor}_{psize}")
	public Map<String, String> deleteMycart(HttpServletRequest request, @PathVariable String pid, @PathVariable String psize, @PathVariable String pcolor) {
		String mid = request.getAttribute("mid").toString();
		Cart mycart = new Cart();
		mycart.setMid(mid);
		mycart.setPid(pid);
		mycart.setPcolor(pcolor);
		mycart.setPsize(psize);
		memberService.deleteCartItem(mycart);
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
	
	//장바구니 추가
	@PostMapping("/createcart")
	public Map<String, String> addToCart(HttpServletRequest request, Cart cart) {
		String mid = request.getAttribute("mid").toString();
		Cart mycart = new Cart();
		mycart.setMid(mid);
		mycart.setPid(cart.getPid());
		mycart.setPcolor(cart.getPcolor());
		mycart.setPsize(cart.getPsize());
		mycart.setPamount(cart.getPamount());
		memberService.addtocart(mycart);
		
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
	
	//쿠폰 추가
	/*@PostMapping("/createcoupon")
	public int createCoupon(HttpServletRequest request, int eid, String ename, String cname) {
		Coupon coupon = new Coupon();
		Event event = eventController.getEvent(eid);
		String mid = request.getAttribute("mid").toString();
		String ccode = eid+ "" + event.getEamount();
		
		// 이벤트 eamount가 0인 경우
		int eamount = event.getEamount();
		if(eamount == 0) { return 0; }
		
		//eno와 mid가 중복될 경우
		List<Coupon> coupons = getCouponList(request);
		log.info(coupons+"");
		for(Coupon cou : coupons) {
			if(cou.getEid() == eid) {
				log.info("중복 쿠폰 발급");
				return 2;
			}
		}
		
		event.setEamount(eamount-1);
		//이벤트의 eamount-1
		eventController.updateEvent(event);
		
		coupon.setCcode(ccode);
		coupon.setEid(eid);
		coupon.setMid(mid);
		coupon.setCname(cname);
		coupon.setCstartdate(new Date());
		coupon.setCenddate(event.getEenddate());
		coupon.setCstate(0);
		
		return memberService.createCoupon(coupon);
	}*/
	
	@PostMapping("/createcoupon")
	public int createCoupon(HttpServletRequest request, Coupon coupon) {
		Event event = eventController.getEvent(coupon.getEid());
		String mid = request.getAttribute("mid").toString();
		String ccode = coupon.getEid()+ "" + event.getEamount();
		
		log.info(coupon.toString());
		
		// 이벤트 eamount가 0인 경우
		int eamount = event.getEamount();
		if(eamount == 0) { return 0; }
		
		//eno와 mid가 중복될 경우
		List<Coupon> coupons = getCouponList(request);
		//log.info(coupons+"");
		for(Coupon cou : coupons) {
			if(cou.getEid() == coupon.getEid()) {
				log.info("중복 쿠폰 발급");
				return 2;
			}
		}
		
		event.setEamount(eamount-1);
		//이벤트의 eamount-1
		eventController.updateEvent(event);
		
		coupon.setCcode(ccode);
		coupon.setEid(coupon.getEid());
		coupon.setMid(mid);
		coupon.setCname(coupon.getCname());
		coupon.setCstartdate(new Date());
		coupon.setCenddate(event.getEenddate());
		coupon.setCstate(0);
		
		return memberService.createCoupon(coupon);
	}
	
	//update coupon
	@PostMapping("/updatecoupon")
	public int updateCoupon(String ccode, int cstate) {
		Coupon coupon = new Coupon();
		coupon.setCcode(ccode);
		coupon.setCstate(cstate);
		return memberService.updateCoupon(coupon);
	}
	
	//update mileage
	@PostMapping("/updatepoint")
	public int updatePoint(HttpServletRequest request, int point) {
		Member member = new Member();
		String mid = request.getAttribute("mid").toString();
		
		member.setMid(mid);
		member.setMpoint(point);
		return memberService.updatePoint(member);
	}
	
	@GetMapping("/mycartNum")
	public int getMycartAmount(HttpServletRequest request) {
		String mid = request.getAttribute("mid").toString();
		return memberService.getMycartAmount(mid);
	}
}
