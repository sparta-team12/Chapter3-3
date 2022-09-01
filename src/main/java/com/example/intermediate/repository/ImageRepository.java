package com.example.intermediate.repository;

import com.example.intermediate.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {

    Optional<Image> findByImgURL(String imgUrl);

}
