package io.slack.blockchain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.slack.blockchain.domain.users.SlackUser;

@Repository
public interface SlackUsersRepository extends JpaRepository<SlackUser, Long> {

}
