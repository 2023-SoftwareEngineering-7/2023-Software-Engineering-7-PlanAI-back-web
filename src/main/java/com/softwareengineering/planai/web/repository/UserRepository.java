package com.softwareengineering.planai.web.repository;

import com.softwareengineering.planai.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByNameAndPhoneNumber(String name, String phoneNumber);
    public List<User> findByNameAndEmail(String name, String email);
    public List<User> findByEmail(String email);
}
