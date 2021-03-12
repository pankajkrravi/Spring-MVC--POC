package com.goomo.hplus.controllers;

import com.goomo.hplus.beans.Product;
import com.goomo.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class SearchController {

    @Autowired
    ProductRepository productRepository;

   /* @GetMapping("/search") // Async using Callable
    public Callable<String> search(@RequestParam("search") String search, Model model, HttpServletRequest httpServletRequest)
    {

        System.out.println("I am in Search Controller");
        System.out.println("Search Criteria "+search);
        System.out.println("Async supported in Application : "+httpServletRequest.isAsyncSupported());
        System.out.println("Thread from the servlet container : "+Thread.currentThread().getName());
        return ()-> //lembda expression
        {
            Thread.sleep(3000); //simulate the delay
            System.out.println("Thread from the spring MVC task executor  : "+Thread.currentThread().getName());
            List<Product> productList=new ArrayList<>();
            productList=productRepository.searchByName(search);
            model.addAttribute("products",productList);//add productList to model and we can fetch it in JSP page
            return "search";
        };
    }*/

    @Autowired
    private AsyncTaskExecutor  asyncTaskExecutor;

    @GetMapping("/search") // Async using DeferredResult
    public DeferredResult<String> search(@RequestParam("search") String search, Model model, HttpServletRequest httpServletRequest)
    {
            DeferredResult<String> stringDeferredResult=new DeferredResult<>();

        System.out.println("I am in Search Controller");
        System.out.println("Search Criteria "+search);
        System.out.println("Async supported in Application : "+httpServletRequest.isAsyncSupported());
        System.out.println("Thread from the servlet container : "+Thread.currentThread().getName());
            asyncTaskExecutor.execute(()->
            {
                try {
                    Thread.sleep(3000); //simulate the delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread from the spring MVC task executor  : "+Thread.currentThread().getName());
                List<Product> productList=new ArrayList<>();
                productList=productRepository.searchByName(search);
                model.addAttribute("products",productList);
                stringDeferredResult.setResult("search");
            });
        return  stringDeferredResult;
    }
}
