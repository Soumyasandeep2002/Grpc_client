package com.grpc.controller;

import com.grpc.Dto.Request;
import com.grpc.Dto.Response;
import com.grpc.Service.GrpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class control {

    @Autowired
    private GrpcService grpcService;

    @PostMapping("grpc/test")

    public Response fetch(@RequestBody Request req){
        log.info("request received"+req);
        return grpcService.fetchUser(req);
    }
}
