package com.zaifu.SpringBootWeb.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaifu.SpringBootWeb.Dao.MemberRepository;
import com.zaifu.SpringBootWeb.Model.MemberAccount;
import com.zaifu.SpringBootWeb.Model.MemberAccountJPA;
import com.zaifu.SpringBootWeb.Service.MemberService;



@Controller
public class MemberController {
	//透過 @RequestMapping 指定從/會被對應到此hello()方法
//	@Autowired
//	MemberAccount memberAccount;
//	
//	@Autowired
//	MemberService memberService;
	
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DataSource dataSource;
	
	
	
	
	/*
    @RequestMapping("/")
    public String hello(){
    	memberAccount = new MemberAccount();
    	memberAccount.setPassword("12345678");
    	memberAccount.setEmail("test@gmail.com");
    	memberAccount.setCellphone("0912345789");
    	memberAccount.setAddress("台北市");
    	memberService.addMember(memberAccount);
        return "新增會員成功";
    }*/
    
    
	@GetMapping("/addMemberPage")
    public String addMemberPage(){

		List<MemberAccountJPA> memberAccountJPA= new ArrayList<MemberAccountJPA>();
		memberAccountJPA = memberRepository.findAll();

		for(int i=0;i<memberAccountJPA.size();i++){
			System.out.println(memberAccountJPA.get(i).getId());
		}
        return "addMemberPage";
    }
    
    
	@GetMapping("/login")
    public String login(@ModelAttribute MemberAccountJPA memberAccountJPA){

        return "login";
    }
	
	@PostMapping("/doLogin")
    public String doLogin(@ModelAttribute MemberAccountJPA memberAccountJPA,HttpSession session){
		String email = memberAccountJPA.getEmail();
		String password = memberAccountJPA.getPassword();
		List<MemberAccountJPA> MemberAccountJPAList = new ArrayList<MemberAccountJPA>();
		MemberAccountJPAList = memberRepository.findCheckMemberAccount(email, password);
		MemberAccount memberAccount = new  MemberAccount();
		memberAccount.setPassword(password);
		memberAccount.setEmail(email);;

		if(MemberAccountJPAList.size()==0){
			return "login";
		}
		else{
			session.setAttribute("uid", memberAccount);
	        return "welcome";
		}

    }
	
	
	
	
    
}