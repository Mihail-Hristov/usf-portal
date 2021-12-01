package com.example.backend.service.impl;

import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserRole;
import com.example.backend.model.entity.enumeration.UserRoleNameEnum;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsfPortalUserServiceImplTest {

    private User testUser;
    private User notActivateUser;
    private UserRole adminRole, userRole;

    private UsfPortalUserServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        serviceToTest = new UsfPortalUserServiceImpl(mockUserRepository);

        testUser = new User();
        testUser.setUsername("misho@misho.bg");
        testUser.setFirstName("Mihail");
        testUser.setLastName("Hristov");
        testUser.setNickname("Тъпана");
        testUser.setPassword("123");
        testUser.setActive(true);

        notActivateUser = new User();
        notActivateUser.setUsername("not-activ@misho.bg");
        notActivateUser.setFirstName("Mihail");
        notActivateUser.setLastName("Hristov");
        notActivateUser.setNickname("Тъпана");
        notActivateUser.setPassword("123");
        notActivateUser.setActive(false);

    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("invalid@not-exist.bg")
        );
    }

    @Test
    void testUserFound() {

        Mockito.when(mockUserRepository.findFirstByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
    }

    @Test
    void testUserIsNotActivate() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("not-activ@misho.bg")
        );
    }
}