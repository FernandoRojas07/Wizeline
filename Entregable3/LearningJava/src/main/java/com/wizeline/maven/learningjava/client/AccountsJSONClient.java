package com.wizeline.maven.learningjava.client;

import com.wizeline.maven.learningjava.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="getAccountsClient", url="https://jsonplaceholder.typicode.com/")
public interface AccountsJSONClient {
    @GetMapping(value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);
}
