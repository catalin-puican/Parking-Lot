package org.example.parkinglot1.ejb;

import org.example.parkinglot1.common.UserDto;
import org.example.parkinglot1.entities.User;
import org.example.parkinglot1.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class UserBean {
    @Inject
    PasswordBean passwordBean;
    private static final Logger LOG = Logger.getLogger(org.example.parkinglot1.ejb.UserBean.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try{
            TypedQuery<User> typedQuery =entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

    }
    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            UserDto dto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }
    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
    public Collection<String> findUsernamesByUserIds(Collection<Long> userIds) {
        LOG.info("findUsernamesByUserIds");
        List<String> usernames =
                entityManager.createQuery("SELECT u.username FROM User u WHERE u.id IN :userIds", String.class)
                        .setParameter("userIds", userIds)
                        .getResultList();
        return usernames;
    }
    public void updateUser(Long userId, String username, String email, String newPassword) {
        LOG.info("updateUser");
        User user = entityManager.find(User.class, userId);

        user.setUsername(username);
        user.setEmail(email);

        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordBean.convertToSha256(newPassword));
        }
    }
}