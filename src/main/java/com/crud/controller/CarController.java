package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.model.Car;
import com.crud.service.CarService;

@Controller
public class CarController {
	
	@Autowired
	CarService carService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex(@ModelAttribute("message") final String message,
								@ModelAttribute("error") final String error) {
		
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("listCars", carService.getAllCars());
		mav.addObject("message", message);
		mav.addObject("error", error);
		
		return mav;
	}
	
	@RequestMapping(value = "view/read", method = RequestMethod.GET)
	public ModelAndView read() {
		return new ModelAndView("read", "listCars", carService.getAllCars());
	}
	
	
	@RequestMapping(value = "view/insert", method = RequestMethod.GET)
	public ModelAndView insert() {
		
		ModelAndView mav = new ModelAndView("insert");
		mav.addObject("car", new Car());
		
		return mav;
	}
	
	@RequestMapping(value = "view/insert", method = RequestMethod.POST)
	public String submitInsert(@ModelAttribute("car")Car car,
								BindingResult result,
								ModelMap model,
								RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Houve um erro!");
		} else {
			carService.insertCar(car);
			redirectAttributes.addFlashAttribute("message", "Novo Carro inserido!");
		}
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "view/delete", method = RequestMethod.GET)
	public ModelAndView delete(Integer id) {
		
		ModelAndView mav = new ModelAndView("delete");
		
		// Ao buscar o ID do carro deve ser removido do Optional antes de enviar a VIEW
		Car car = carService.getCarById(id).orElseThrow(() -> new RuntimeException("carro nao encontrado"));
		mav.addObject("car", car);
		
		return mav;
	}
	
	@RequestMapping(value = "view/delete", method = RequestMethod.POST)
	public String submitDelete(@ModelAttribute("car") Car car,
								BindingResult result,
								ModelMap model,
								RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Houve um erro!");
		} else {
			carService.deleteCarById(car.getId());
			redirectAttributes.addFlashAttribute("mesage", "Car excluido!");
		}
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "view/update", method = RequestMethod.GET)
	public ModelAndView update(Integer id) {
		
		ModelAndView mav = new ModelAndView("update");
		mav.addObject("car", carService.getCarById(id).get());
		
		return mav;
	}
	
	@RequestMapping(value = "view/update", method = RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("car") Car car,
								BindingResult result,
								ModelMap model,
								RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Houve um erro!");
		} else {
			carService.updateCar(car);
			redirectAttributes.addFlashAttribute("mesage", "Car Alterado!");
		}
		return "redirect:/";
	}
	
	

}
