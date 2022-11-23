package com.first.myproject.repository;

import com.first.myproject.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userrepo extends JpaRepository<user,Long>{
}
