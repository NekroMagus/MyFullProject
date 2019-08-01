package com.football.manager.dao;

import com.football.manager.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo,Long> {

}
