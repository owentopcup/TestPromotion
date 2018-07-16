package com.example.app.controller;

import com.example.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class CustomerServiceController {
    @Autowired
    CustomerService customerService;


    @RequestMapping(path = "/findServiceChargeAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity findServiceChargeAll() {
        Object obj=null;
        try{
            obj=customerService.findServiceChargeAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(obj);
    }
    @RequestMapping(path = "/importCustomerTelUsageFile", method = RequestMethod.POST)

    public @ResponseBody  ResponseEntity importCustomerTelUsageFile(@RequestParam("file")  MultipartFile file) {
//        System.out.println(files.length);

//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return ResponseEntity.ok(true);
    }
    @RequestMapping(path = "/calculateServiceCharge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity calculateServiceCharge() {

        try{
            customerService.calculateServiceCharge();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("OK");
    }

}
