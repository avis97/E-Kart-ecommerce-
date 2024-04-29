package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    public Category findByName(String name);

    @Query("Select c from Category c Where c.name=:name AND c.parentCategory.name=:parentCategoryName")
    public Category findByNameAndParent(@Param("name") String name,
                                        @Param("parentCategoryName")
                                        String parentCategoryName);
}
