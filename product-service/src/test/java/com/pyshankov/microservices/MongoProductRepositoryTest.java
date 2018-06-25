package com.pyshankov.microservices;

import com.pyshankov.microservices.domain.Product;
import com.pyshankov.microservices.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Alexey on 30/4/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoProductRepositoryTest {

    @Autowired
    private ProductRepository mongoRepository;

    @Before
    public void setUp() throws Exception {
        Product tablet = new Product("tablet", "desc1", new BigDecimal(1));
        Product wear = new Product( "wear", "desc1", new BigDecimal(2));
        //save product, verify has ID value after save
        assertNull(tablet.getId());
        assertNull(wear.getId());//null before save
        mongoRepository.save(tablet);
        mongoRepository.save(wear);
        assertNotNull(tablet.getId());
        assertNotNull(wear.getId());
    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        Product wear = mongoRepository.findByName("wear");
        assertNotNull(wear);
        assertEquals("wear", wear.getName());
        /*Get all products, list should only have two*/
        Iterable<Product> users = mongoRepository.findAll();
        int count = 0;
        for(Product p : users){
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testDataUpdate(){
        /*Test update*/
        Product wearA = mongoRepository.findByName("wear");
        wearA.setPrice(new BigDecimal(100));
        mongoRepository.save(wearA);
        Product wearB= mongoRepository.findByName("wear");
        assertNotNull(wearB);
        assertEquals(new BigDecimal(100), wearB.getPrice());
    }

    @After
    public void tearDown() {
        this.mongoRepository.deleteAll();
    }

}