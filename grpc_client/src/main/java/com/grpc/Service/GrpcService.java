package com.grpc.Service;

import com.grpc.Dto.Request;
import com.grpc.Dto.Response;
import com.grpc.client.User;
import com.grpc.client.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GrpcService {

    public Response fetchUser(Request req){
        Response response = new Response();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();

        User.LoginRequest login = User.LoginRequest
                .newBuilder().setUsername(req.getUsername())
                .setPassword(req.getPassword())
                .build();

        userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);

        User.APIResponse resp = userStub.login(login);

        response.setResponsemessage(resp.getResponsemessage());
        response.setResponseCode(String.valueOf(resp.getResponseCode()));

        log.info("response prepared"+resp);

        return response;
    }
}
