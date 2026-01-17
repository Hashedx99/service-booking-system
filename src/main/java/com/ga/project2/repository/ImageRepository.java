package com.ga.project2.repository;
import com.ga.project2.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Image findByUrl(String url);
}
