package com.example.fullstack_study.category.domain;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);


    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> selectAll() {
        String sql = "SELECT c.id, c.name, COUNT(p.id) AS count " +
                "FROM category c " +
                "LEFT JOIN product p ON c.id = p.category_id " +
                "GROUP BY c.id, c.name";

        return jdbc.query(sql, rowMapper);
    }
}
