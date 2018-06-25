package com.pyshankov.microservices;

import com.pyshankov.microservices.domain.Order;
import com.pyshankov.microservices.domain.User;
import com.pyshankov.microservices.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexey on 30/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoOrderRepositoryTest {

    @Autowired
    private OrderRepository mongoRepository;

    private User user;

    private static final int DEFAULT_COAST = 100;

    @Before
    public void setUp() throws Exception {
        user = new User("admin@gmail.com","asdasd");
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setUser(user);
        order2.setUser(user);
        order1.setPrice(new BigDecimal(DEFAULT_COAST));
        order2.setPrice(new BigDecimal(DEFAULT_COAST*2));
        assertNull(order1.getId());
        assertNull(order2.getId());//null before save
        this.mongoRepository.save(order1);
        this.mongoRepository.save(order2);
        assertNotNull(order1.getId());
        assertNotNull(order2.getId());
    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        List<Order> orders = mongoRepository.findByUser(user);
        assertNotNull(orders);
        assertEquals(user, orders.get(0).getUser());
        /*Get all products, list should only have two*/
        Iterable<Order> users = mongoRepository.findAll();
        int count = 0;
        for(Order p : users){
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testDataUpdate(){
        /*Test update*/
        Order wearA = mongoRepository.findByUser(user).get(0);
        wearA.setPrice(BigDecimal.valueOf(3*DEFAULT_COAST));
        mongoRepository.save(wearA);
        Order wearB= mongoRepository.findByUser(user).get(0);
        assertNotNull(wearB);
        assertEquals(BigDecimal.valueOf(3*DEFAULT_COAST), wearB.getPrice());
    }

    @After
    public void tearDown() {
        this.mongoRepository.deleteAll();
    }
}
