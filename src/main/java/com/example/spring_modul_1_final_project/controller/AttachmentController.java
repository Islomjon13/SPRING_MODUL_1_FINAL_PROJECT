package com.example.spring_modul_1_final_project.controller;

import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public Response upload(MultipartHttpServletRequest request){
        return attachmentService.upload(request);
    }

}
