package com.example.demo.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Date createdDate;
    private Date lastModifiedDate;
}
