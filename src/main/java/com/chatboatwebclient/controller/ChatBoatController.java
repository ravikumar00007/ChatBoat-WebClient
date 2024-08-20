package com.chatboatwebclient.controller;

import com.chatboatwebclient.dto.ChatBoatDto;
import com.chatboatwebclient.dto.Content;
import com.chatboatwebclient.dto.TextClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ChatBoatController {
    @Autowired
    private RestTemplate template;
    String serviceUrl="https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent";
    //ResponseEntity<String> response=restTemplate.getForEntity(serviceUrl,String.class);
    @PostMapping("/show")
    public ResponseEntity<String> showRsult(@RequestBody TextClass txt) throws JsonProcessingException {
        TextClass text=new TextClass();
        text.setText(txt.getText());

        List<TextClass> parts=new ArrayList<>();
        parts.add(text);

        Content content=new Content();
        content.setRole("user");
        content.setParts(parts);


        ChatBoatDto dto = new ChatBoatDto();
        List<Content> contents = new ArrayList<>();
        contents.add(content);
        dto.setContents(contents);


        //ResponseEntity<String> entity = template.getForEntity(url, String.class);
        /*String json_Body="{\"contents\":[\n" +
                "            {\"role\": \"user\",\n" +
                "              \"parts\":[{\"text\": \"what is capital of Pakistan\"}]}]}";*/
        //List<Content> json_Body1 = dto.getContents();
        ObjectMapper mapper=new ObjectMapper();

            String json_Body=mapper.writeValueAsString(dto);



        System.out.println(json_Body);
        System.out.println("ravi");
        HttpHeaders headers=new HttpHeaders();
        headers.set("x-goog-api-key","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        headers.setContentType(MediaType.APPLICATION_JSON);
        //prepare HttpRequest as HttpEntity obj having head body
        HttpEntity<String> entity1=new HttpEntity<>(json_Body,headers);

        ResponseEntity<String> stringResponseEntity = template.postForEntity(serviceUrl, entity1, String.class);
        JsonNode jsonNode = mapper.readTree(stringResponseEntity.getBody());
        JsonNode path = jsonNode.path("candidates").get(0).path("content").path("parts").get(0).path("text");
        String text1 = path.asText();
        String jsonTextResponse = "{\n\t \"text\": \"" + text1 + "\" \n}";


        //System.out.println(jsonTextResponse);
        //System.out.println(body);

        //return new ResponseEntity<String>(jsonTextResponse, HttpStatus.OK);
        return new ResponseEntity<String>(text1, HttpStatus.OK);
    }

}
