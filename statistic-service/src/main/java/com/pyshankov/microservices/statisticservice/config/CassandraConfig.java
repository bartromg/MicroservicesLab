package com.pyshankov.microservices.statisticservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    //    @Value("${cassandra.contactpoints}")
    private String CASSANDRA_CONTACTPOINT = "statistic-cassandra";

    //    @Value("${cassandra.port}")
    private Integer CASSANDRA_PORT = 9042;

    //    @Value("${cassandra.keyspace}")
    private String CASSANDRA_KEYSPACE = "event";

    @Bean
    @Override
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setContactPoints(CASSANDRA_CONTACTPOINT);
//        bean.setPort(CASSANDRA_PORT);
//        bean.setUsername(USERNAME);
//        bean.setPassword(PASSWORD);
        return bean;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return CASSANDRA_KEYSPACE;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.pyshankov.microservices.statisticservice.domain"};
    }


    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
        createKeyspaceSpecifications.add(getKeySpaceSpecification());
        return createKeyspaceSpecifications;
    }

    private CreateKeyspaceSpecification getKeySpaceSpecification() {
        return CreateKeyspaceSpecification.createKeyspace(CASSANDRA_KEYSPACE).ifNotExists(true).withSimpleReplication();
    }
}

