package com.example.fullstack_study_boostcourse.product;

import com.example.fullstack_study_boostcourse.reservation.Comment;
import com.example.fullstack_study_boostcourse.reservation.DisplayInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDao {

    private SimpleJdbcInsert insertAction;
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    public List<Product> selectAllProductsByCategory(Integer categoryId, Integer start, Integer limit) {
        String sql = "SELECT * FROM product WHERE category_id = :categoryId " +
                "ORDER BY id DESC LIMIT :start, :limit";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryId", categoryId);
        params.addValue("start", start);
        params.addValue("limit", limit);

        return jdbc.query(sql, params, rowMapper);
    }

    // 모든 제품 조회
    public List<Product> selectAllProducts(int start, int limit) {
        String sql = "SELECT * FROM product " +
                "ORDER BY id DESC LIMIT :start, :limit";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("start", start);
        params.addValue("limit", limit);

        return jdbc.query(sql, params, rowMapper);
    }

    public DisplayInfoResponse selectDisplayInfoById(Integer id) {
        String sql = "SELECT " +
                "di.id AS displayInfoId, " +
                "di.product_id AS productId, " +
                "di.opening_hours AS openingHours, " +
                "di.place_name AS placeName, " +
                "di.place_lot AS placeLot, " +
                "di.place_street AS placeStreet, " +
                "di.tel AS telephone, " +
                "di.homepage AS homepage, " +
                "di.email AS email, " +
                "di.create_date AS displayCreateDate, " +
                "di.modify_date AS displayModifyDate, " +
                "(SELECT AVG(score) FROM reservation_user_comment WHERE product_id = p.id) AS averageScore " +
                "c.id AS categoryId " +
                "c.name AS categoryName " +
                "p.description AS productDescription " +
                "p.content AS productContent " +
                "p.event AS productEvent " +
                "FROM display_info di " +
                "LEFT JOIN product p ON di.product_id = p.id " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE di.id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        DisplayInfoResponse displayInfo = jdbc.queryForObject(sql, params, new DisplayInfoResponseRowMapper());

        List<Comment> comments = selectCommentsByProductId(displayInfo.getDisplayInfo().getProductId());
        DisplayInfoImage displayInfoImages = selectDisplayInfoImagesById(id);
        List<ProductImage> productImages = selectProductImagesByProductId(displayInfo.getDisplayInfo().getProductId());
        List<ProductPrice> productPrices = selectProductPricesByProductId(displayInfo.getDisplayInfo().getProductId());

        displayInfo.setComments(comments);
        displayInfo.setDisplayInfoImage(displayInfoImages);
        displayInfo.setProductImages(productImages);
        displayInfo.setProductPrices(productPrices);

        return displayInfo;
    }

    public Integer getCount() {
        String sql = "SELECT COUNT(*) FROM product";
        return jdbc.queryForObject(sql, Collections.emptyMap(), Integer.class);
    }

    private static class DisplayInfoResponseRowMapper implements RowMapper<DisplayInfoResponse> {
        @Override
        public DisplayInfoResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            DisplayInfo displayInfo = DisplayInfo.builder()
                    .productId(rs.getInt("productId"))
                    .categoryId(rs.getInt("categoryId"))
                    .displayInfoId(rs.getInt("id"))
                    .categoryName(rs.getString("categoryName"))
                    .productDescription(rs.getString("productDescription"))
                    .productContent(rs.getString("productContent"))
                    .productEvent(rs.getString("productEvent"))
                    .openingHours(rs.getString("openingHours"))
                    .placeName(rs.getString("placeName"))
                    .placeLot(rs.getString("placeLot"))
                    .placeStreet(rs.getString("placeStreet"))
                    .telephone(rs.getString("telephone"))
                    .homepage(rs.getString("homepage"))
                    .email(rs.getString("email"))
                    .createDate(rs.getString("createDate"))
                    .modifyDate(rs.getString("modifyDate"))
                    .build();

            return DisplayInfoResponse.builder()
                    .averageScore(rs.getBigDecimal("averageScore"))
                    .displayInfo(displayInfo)
                    .build();
        }
    }

    private List<Comment> selectCommentsByProductId(Integer productId) {
        String sql = "SELECT * FROM reservation_user_comment WHERE product_id = :productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(Comment.class));
    }

    private DisplayInfoImage selectDisplayInfoImagesById(Integer displayInfoId) {
        String sql = "SELECT * FROM display_info_image WHERE display_info_id = :displayInfoId";
        Map<String, Object> params = new HashMap<>();
        params.put("displayInfoId", displayInfoId);
        return jdbc.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(DisplayInfoImage.class));
    }

    private List<ProductImage> selectProductImagesByProductId(Integer productId) {
        String sql = "SELECT * FROM product_image WHERE product_id = :productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(ProductImage.class));
    }

    private List<ProductPrice> selectProductPricesByProductId(Integer productId) {
        String sql = "SELECT * FROM product_price WHERE product_id = :productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(ProductPrice.class));
    }
}