package io.slack.blockchain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.slack.blockchain.domain.persitence.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
