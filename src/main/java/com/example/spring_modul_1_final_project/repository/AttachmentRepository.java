package com.example.spring_modul_1_final_project.repository;

import com.example.spring_modul_1_final_project.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    List<Attachment> findAllByAttachmentCollectionId(Long attachmentCollection_id);
}
