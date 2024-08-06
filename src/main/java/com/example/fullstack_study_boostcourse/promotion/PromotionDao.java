package com.example.fullstack_study_boostcourse.promotion;

import com.example.fullstack_study_boostcourse.category.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PromotionDao {

    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

    public PromotionDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Promotion> selectAll() {
        String sql = "SELECT p.id, p.product_id, f.file_name as productImageUrl " +
                "FROM promotion p " +
                "LEFT JOIN product_image pi ON p.product_id = pi.product_id AND pi.type = 'th' " +
                "LEFT JOIN file_info f ON pi.file_id = f.id";

        return jdbc.query(sql, rowMapper);
    }
}
