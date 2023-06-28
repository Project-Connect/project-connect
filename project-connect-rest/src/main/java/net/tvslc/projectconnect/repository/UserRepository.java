package net.tvslc.projectconnect.repository;

import net.tvslc.projectconnect.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(String username);


    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> deleteByUsername(String username);
}