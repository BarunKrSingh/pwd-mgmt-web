package com.telefonica.pwdweb.exception;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.client.WebServiceIOException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler({ NullPointerException.class, WebServiceIOException.class, ConnectException.class, Exception.class })
	public ModelAndView serverError(Exception e) {		
		
		logger.info("Handling the webservice exception:");
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("message", e.getMessage());
		
		logger.error("Execption occured processing the request due to:",e);

		return mav;
	}
	
	/*@ExceptionHandler({NoSuchRequestHandlingMethodException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "PageNotFound")
	public ModelAndView handleException3(Exception e) throws IOException
	{
		logger.info("Handling the webservice exception:");
		
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("message", e.getMessage());
		
		logger.error("Execption occured processing the request due to:",e);

		return mav;
	}*/


}
