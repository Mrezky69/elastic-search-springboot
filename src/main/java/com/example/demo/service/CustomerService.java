package com.example.demo.service;

import com.example.demo.dto.CustomerResponse;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerResponse> list() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> response = new ArrayList<>();
        for (Customer data : customers) {
            response.add(CustomerResponse.builder()
                    .id(data.getId())
                    .name(data.getName())
                    .address(data.getAddress())
                    .phoneNumber(data.getPhoneNumber())
                    .createdDate(data.getCreatedDate())
                    .lastModifiedDate(data.getLastModifiedDate())
                    .build());
        }
        return response;
    }

    public void edit(Customer request, Long id){
        Customer data = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found"));

        data.setName(request.getName());
        data.setAddress(request.getAddress());
        data.setPhoneNumber(request.getPhoneNumber());
        data.setLastModifiedDate(new Date());
        customerRepository.save(data);
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found"));
        customerRepository.delete(customer);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

}


