/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.renisp;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runners.MethodSorters;

import com.renisp.model.Result;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGreeting() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void A1createUser() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"friends\": [\"andy@example.com\", \"john@example.com\"]}";
    	HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/createFriends", request, Result.class);
        assertEquals(true, result.isSuccess());
    }
    
    @Test
    public void A2createUser() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"friends\": [\"andy@example.com\", \"peter@example.com\"]}";
    	HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/createFriends", request, Result.class);

        assertEquals(true, result.isSuccess());
    }
    
    @Test
    public void A3createUser() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"friends\": [\"john@example.com\", \"peter@example.com\"]}";
    	HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/createFriends", request, Result.class);

        assertEquals(true, result.isSuccess());
    }
    
    @Test
    public void A4createUser() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"friends\": [\"john@example.com\", \"andy@example.com\"]}";
    	HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/createFriends", request, Result.class);

        assertEquals(true, result.isSuccess());
    }
    
    @Test
    public void B1retrieveFriends() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"email\": \"andy@example.com\"}";
    	HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/retrieveFriends", request, Result.class);
        System.out.println("Andy's 2 friends are:"+result.getFriends());
       //Check if 2 friends for Andy
        assertEquals(new Integer(2), result.getCount());
    }
    
    @Test
    public void C1retrieveCommonFriends() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"friends\": [\"andy@example.com\", \"john@example.com\"]}";
    	HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/retrieveCommonFriends", request, Result.class);
        System.out.println("Andy and John's common friends are:"+result.getFriends());
        //Check if 1 common friend for Andy and John
        assertEquals(new Integer(1), result.getCount());
    }
    
    @Test
    public void D1subscribe() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"requestor\": \"lisa@example.com\",\"target\": \"john@example.com\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/subscribe", request, Result.class);
        //Check if 1 common friend for Andy and John
        assertEquals(true, result.isSuccess());
    }   
    
    @Test
    public void E1block() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"requestor\": \"andy@example.com\",\"target\": \"john@example.com\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/block", request, Result.class);
        //Check if 1 common friend for Andy and John
        assertEquals(true, result.isSuccess());
    }
    
     @Test
    public void F1validEmails() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"sender\": \"john@example.com\",\"text\": \"Hello World! kate@example.com\"}";
        HttpEntity<String> request = new HttpEntity<String>(requestJson,headers);
        Result result = (Result)restTemplate.postForObject("http://localhost:"+ this.port + "/validEmails", request, Result.class);
        System.out.println("Recipients are:"+result.getRecipients());
        //Check if 1 common friend for Andy and John
        assertEquals(true, result.isSuccess());
    }       
    
    
}
