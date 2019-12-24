package com.test.FundTransfer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.FundTransfer.model.Account;
import com.test.FundTransfer.service.FundTrnsService;



@Controller
public class FundTrnsController {

	
	@Autowired
	private FundTrnsService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listCustomers", service.getAllCustomers());
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		
		return "new_customer";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("account") Account account) {
		service.saveCustomer(account);
		
		return "redirect:/";
	}
	
	@RequestMapping("/fundTransfer/{id}")
	public ModelAndView fundTransfer(@PathVariable(name = "id") Long id) {
		ModelAndView model = new ModelAndView("fund_transfer");
		Account account = service.getAccountDetailsById(id);
		model.addObject("account", account); 
		
		List<Account> otherAccounts = service.getOtherAccoutDetails(id);
		model.addObject("listAccounts", otherAccounts);
		return model;
	}
	
	@RequestMapping(value = "/sendFundTransfer", method = RequestMethod.POST)
	public String sendFundTransfer(@ModelAttribute("account") Account account) {
		service.sendFundTransfer(account);
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		Account account = service.getAccountDetailsById(id);
		mav.addObject("account", account);
		
		return mav;
	}
}
