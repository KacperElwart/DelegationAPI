package com.example.elwart;

import com.example.elwart.user.dto.DelegationDto;
import com.example.elwart.user.dto.UserDto;
import com.example.elwart.user.exception.BadAutoCapacityException;
import com.example.elwart.user.exception.NotKmException;
import com.example.elwart.user.exception.NotTicketPriceException;
import com.example.elwart.user.exception.UserNotFoundException;
import com.example.elwart.user.model.Delegation;
import com.example.elwart.user.model.User;
import com.example.elwart.user.service.DelegationService;

import com.example.elwart.user.service.UserService;
import com.example.elwart.user.transport.Transport;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@ComponentScan("com.example.elwart.user.service")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class DelegationTests {


    @Autowired
    private DelegationService delegationService;

    @Autowired
    private UserService userService;


    @Before
    public void setUp() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        UserDto userDto = new UserDto("test", "test", "tst", "test",
                "test", "test", "test", List.of("ROLE_USER"));
        userService.registerUser(userDto);
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, 2.3, null, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
    }

    @Test
    public void testRegisterUser() {
        int size = userService.getAllUsers().size();
        UserDto userDto = new UserDto("test", "test", "tst", "test",
                "test", "test", "test", List.of("ROLE_USER"));
        userService.registerUser(userDto);
        Assert.assertEquals(size + 1, userService.getAllUsers().size());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        Assert.assertEquals(users.size(), 1);
        UserDto userDto = new UserDto("test2", "test", "tst", "test",
                "test", "test", "test", List.of("ROLE_USER"));
        userService.registerUser(userDto);
        List<User> users2 = userService.getAllUsers();
        Assert.assertEquals(users2.size(), 2);
        Assert.assertEquals(users.get(0).getCompanyAddress(), "test");

    }

    @Test
    public void testChangePassword() {
        User user = userService.getAllUsers().get(0);
        Assert.assertEquals(user.getPassword(), "test");
        userService.changePassword(user.getId(), "new");
        Assert.assertEquals(userService.getAllUsers().get(0).getPassword(), "new");
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeleteUserById() {
        User user = userService.getAllUsers().get(0);
        Assert.assertEquals(userService.getUserById(user.getId()), user);
        Assert.assertTrue(userService.deleteUserById(user.getId()));
        userService.getUserById(user.getId());
    }

    @Test(expected = BadAutoCapacityException.class)
    public void testAddDelegationWithBadCapacity() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTO, null, 100, 140D, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
    }

    @Test(expected = NotKmException.class)
    public void testAddDelegationWithNotKmWithAuto() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTO, null, 1000, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
    }

    @Test(expected = NotTicketPriceException.class)
    public void testAddDelegationWithNotTicketPriceWithBus() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, null, null, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
    }

    @Test
    public void testAddDelegation() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        int size = delegationService.getAllDelegations().size();
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, 3D, null, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
        Assert.assertEquals(size + 1, delegationService.getAllDelegations().size());
    }

    @Test
    public void testRemoveDelegation() {
        UserDto userDto = new UserDto("test2", "test", "tst", "test",
                "test", "test", "test", List.of("ROLE_USER"));
        userService.registerUser(userDto);
        int size = userService.getAllUsers().size();
        Delegation delegation = delegationService.getAllDelegations().get(0);
        Assert.assertFalse(delegationService.removeDelegation(delegation.getId(), userService.getAllUsers().get(1).getId()));
        Assert.assertTrue(delegationService.removeDelegation(delegation.getId(), userService.getAllUsers().get(0).getId()));
        Assert.assertEquals(size, userService.getAllUsers().size());
    }

    @Test
    public void testChangeDelegation() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        Delegation delegation = delegationService.getAllDelegations().get(0);
        int size = delegationService.getAllDelegations().size();
        DelegationDto delegationDto = new DelegationDto("new Description", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, 3D, null, null, 3.3, 2.2, "test", 2D);
        Assert.assertNotEquals(delegation.getDescription(), "new Description");
        delegationService.changeDelegation(delegationDto, delegation.getId());
        Assert.assertEquals(delegationService.getAllDelegations().get(0).getDescription(), "new Description");
        Assert.assertEquals(delegationService.getAllDelegations().size(), size);
    }

    @Test
    public void testGetAllDelegations() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        int size = delegationService.getAllDelegations().size();
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now(), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, 3D, null, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
        Assert.assertEquals(delegationService.getAllDelegations().size(), size + 1);
        Assert.assertEquals(delegationService.getAllDelegations().get(0).getDescription(), "tet");
        Assert.assertEquals(delegationService.getAllDelegations().get(1).getDescription(), "tet");
    }

    @Test
    public void testGetAllDelegationsByTime() throws NotKmException, BadAutoCapacityException, NotTicketPriceException {
        DelegationDto delegationDto2 = new DelegationDto("tet", LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, 3D, null, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto2, userService.getAllUsers().get(0).getId());
        DelegationDto delegationDto = new DelegationDto("tet", LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1),
                3.4, 2, 2, 2, Transport.AUTOBUS, 3D, null, null, 3.3, 2.2, "test", 2D);
        delegationService.addDelegation(delegationDto, userService.getAllUsers().get(0).getId());
        boolean isOk = true;
        List<Delegation> delegations = delegationService.getAllByUserAndDateStartDesc(userService.getAllUsers().get(0).getId());
        for (int i = 0; i < delegations.size() -1; i++) {
            if (delegations.get(i).getDateTimeStart().isBefore(delegations.get(i+1).getDateTimeStart()))
                isOk = false;
        Assert.assertTrue(isOk);
        }
    }
    @Test
    public void testGetAllUsersByRole(){
        UserDto userDto = new UserDto("test2", "test", "tst", "test",
                "test", "test", "test", List.of("ROLE_USER"));
        UserDto userDto2 = new UserDto("test2", "test", "tst", "test",
                "test", "test", "test", List.of("ROLE_ADMIN"));
        userService.registerUser(userDto);
        userService.registerUser(userDto2);
        Assert.assertEquals(userService.getAllByRole("ROLE_USER").size(),2);
        Assert.assertEquals(userService.getAllByRole("ROLE_ADMIN").size(),1);

    }
}
