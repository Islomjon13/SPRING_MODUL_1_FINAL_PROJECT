package com.example.spring_modul_1_final_project.service;

import com.example.spring_modul_1_final_project.entity.Attachment;
import com.example.spring_modul_1_final_project.entity.AttachmentCollection;
import com.example.spring_modul_1_final_project.entity.AttachmentContent;
import com.example.spring_modul_1_final_project.payload.Response;
import com.example.spring_modul_1_final_project.repository.AttachmentCollectionRepository;
import com.example.spring_modul_1_final_project.repository.AttachmentContentRepository;
import com.example.spring_modul_1_final_project.repository.AttachmentRepository;
import com.example.spring_modul_1_final_project.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class AttachmentService implements Messages {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private final AttachmentCollectionRepository attachmentCollectionRepository;

    public Response upload(MultipartHttpServletRequest request) {
        AttachmentCollection attachmentCollection = new AttachmentCollection();
        AttachmentCollection savedAttachmentCollection = attachmentCollectionRepository.save(attachmentCollection);
        Iterator<String> fileNames = request.getFileNames();
        if (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                Attachment attachment = new Attachment();
                attachment.setName(file.getOriginalFilename());
                attachment.setContentType(file.getContentType());
                attachment.setSize(file.getSize());
                attachment.setAttachmentCollection(savedAttachmentCollection);
                Attachment savedAttachment = attachmentRepository.save(attachment);

                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(savedAttachment);
                try {
                    attachmentContent.setBytes(file.getBytes());
                    attachmentContentRepository.save(attachmentContent);
                } catch (IOException e) {
                    attachmentContentRepository.deleteById(savedAttachment.getId());
                    attachmentCollectionRepository.deleteById(savedAttachmentCollection.getId());
                    return new Response(Messages.FILES_ARE_NOT_VALID, false);
                }
            }
        }
        return new Response(UPLOADED, true, savedAttachmentCollection.getId());
    }

    /*public Response downloadById(Long id, HttpServletResponse response) throws IOException {
        Optional<AttachmentCollection> optionalAttachmentCollection = attachmentCollectionRepository.findById(id);
        if (optionalAttachmentCollection.isEmpty()) return new Response(ATTACHMENT_NOT_FOUND, false);
        List<Attachment> allByAttachmentCollectionId = attachmentRepository.findAllByAttachmentCollectionId(id);
        for (Attachment attachment : allByAttachmentCollectionId) {
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
            response.setHeader("Content-disposition", "attachment; filename=" + attachment.getName());
            FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());
        }
        return new Response(SUCCESS,true);
    }

    public Response getById(Long id) {
        boolean exists = attachmentCollectionRepository.existsById(id);
        if (!exists) return new Response(ATTACHMENT_NOT_FOUND, false);
        List<Attachment> allByAttachmentCollectionId = attachmentRepository.findAllByAttachmentCollectionId(id);
        return new Response(SUCCESS, true, allByAttachmentCollectionId);
    }*/
}
