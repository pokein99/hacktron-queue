package com.dbs.hacktron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.hacktron.entity.QueueInformation;

@Repository
public interface QueueDao extends JpaRepository<QueueInformation, Integer>{

}
