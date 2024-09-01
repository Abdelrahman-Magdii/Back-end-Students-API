package com.spring.student.config;

import com.spring.student.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class ConfigureRepositoryRestConfiguration implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    @Autowired
    public ConfigureRepositoryRestConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] unsupportedActions = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
        disableMethods(Student.class, config, unsupportedActions);
        exposeIds(config);
    }

    private void disableMethods(Class<?> clazz, RepositoryRestConfiguration config, HttpMethod[] unsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(clazz)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // Get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class<?>> entityClasses = new ArrayList<>();

        // Get the entity types for the entities
        for (EntityType<?> entityType : entities) {
            entityClasses.add(entityType.getJavaType());
        }

        // Expose the entity IDs for the array of entity/domain types
        Class<?>[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
