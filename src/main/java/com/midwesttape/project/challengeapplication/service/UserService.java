package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate template;

    public User user(final Long userId) {
        try {

            return template.queryForObject(
                "select " +
                    "u.id, " +
                    "u.firstName, " +
                    "u.lastName, " +
                    "u.username, " +
                    "u.password, " +
                    "a.id AS address_id, " +
                    "a.address1, " +
                    "a.address2, " +
                    "a.city, " +
                    "a.state, " + 
                    "a.postal " +
                    "from User u " +
                    "left join Address a on u.address_id = a.id " +
                    "where u.id = ?",
                    (rs, rowNum) -> {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setFirstName(rs.getString("firstName"));
                        user.setLastName(rs.getString("lastName"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
    
                        Address address = new Address();
                        address.setId(rs.getLong("address_id"));
                        address.setAddress1(rs.getString("address1"));
                        address.setAddress2(rs.getString("address2"));
                        address.setCity(rs.getString("city"));
                        address.setState(rs.getString("state"));
                        address.setPostal(rs.getString("postal"));
    
                        user.setAddress(address);
    
                        return user;
                    },
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

}
