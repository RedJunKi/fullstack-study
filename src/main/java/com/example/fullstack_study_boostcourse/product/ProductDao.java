package com.example.fullstack_study_boostcourse.product;

import com.example.fullstack_study_boostcourse.reservation.Comment;
import com.example.fullstack_study_boostcourse.reservation.CommentImage;
import com.example.fullstack_study_boostcourse.reservation.DisplayInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        String sql = "SELECT " +
                "p.id AS productId, " +
                "di.id AS displayInfoId, " +
                "p.description AS productDescription, " +
                "p.content AS productContent, " +
                "di.place_name AS placeName, " +
                "f.save_file_name AS productImageUrl " +
                "FROM product p " +
                "LEFT JOIN display_info di ON p.id = di.product_id " +
                "LEFT JOIN product_image pi ON p.id = pi.product_id AND pi.type = 'th' " +
                "LEFT JOIN file_info f ON pi.file_id = f.id " +
                "WHERE p.category_id = :categoryId " +
                "ORDER BY p.id ASC LIMIT :start, :limit";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("categoryId", categoryId);
        params.addValue("start", start);
        params.addValue("limit", limit);

        return jdbc.query(sql, params, new ProductRowMapper());
    }

    public List<Product> selectAllProducts(int start, int limit) {
        String sql = "SELECT " +
                "p.id AS productId, " +
                "di.id AS displayInfoId, " +
                "p.description AS productDescription, " +
                "p.content AS productContent, " +
                "di.place_name AS placeName, " +
                "f.save_file_name AS productImageUrl " +
                "FROM product p " +
                "LEFT JOIN display_info di ON p.id = di.product_id " +
                "LEFT JOIN product_image pi ON p.id = pi.product_id AND pi.type = 'th' " +
                "LEFT JOIN file_info f ON pi.file_id = f.id " +
                "ORDER BY p.id ASC LIMIT :start, :limit";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("start", start);
        params.addValue("limit", limit);

        return jdbc.query(sql, params, new ProductRowMapper());
    }

    public DisplayInfoResponse selectDisplayInfoById(Integer displayInfoId) {
        String sql = "SELECT product_id FROM display_info WHERE display_info.id = :displayInfoId";
        Map<String, Object> params = new HashMap<>();
        params.put("displayInfoId", displayInfoId);
        Integer productId = jdbc.queryForObject(sql, params, Integer.class);

        List<Comment> comments = getComments(productId);
        DisplayInfo displayInfo = getDisplayInfo(displayInfoId);
        DisplayInfoImage displayInfoImage = getDisplayInfoImage(displayInfoId);
        List<ProductImage> productImages = getProductImages(productId);
        List<ProductPrice> productPrices = getProductPrices(productId);

        BigDecimal avgScore = BigDecimal.ZERO;

        if (comments.size() > 0) {
            avgScore = comments.stream()
                    .map(Comment::getScore)
                    .reduce(BigDecimal.ZERO, BigDecimal::add) // 모든 점수 합산
                    .divide(new BigDecimal(comments.size()), 2, RoundingMode.HALF_UP);
        }


        return DisplayInfoResponse.builder()
                .comments(comments)
                .displayInfo(displayInfo)
                .displayInfoImage(displayInfoImage)
                .productImages(productImages)
                .productPrices(productPrices)
                .averageScore(avgScore)
                .build();
    }

    private List<ProductPrice> getProductPrices(Integer productId) {
        String sql = "SELECT " +
                "p.id AS productId, " +
                "pp.create_date AS createDate, " +
                "pp.discount_rate AS discountRate, " +
                "pp.modify_date AS modifyDate, " +
                "pp.price AS price, " +
                "pp.price_type_name AS priceTypeName, " +
                "pp.id AS productPriceId " +
                "FROM product p " +
                "LEFT JOIN product_price pp ON pp.product_id = p.id " +
                "WHERE p.id = :productId " +
                "ORDER BY pp.id DESC";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);

        return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(ProductPrice.class));
    }

    private List<ProductImage> getProductImages(Integer productId) {
        String sql = "SELECT " +
                "p.id AS productId, " +
                "fi.content_type AS contentType, " +
                "fi.create_date AS createDate, " +
                "fi.delete_flag AS deleteFlag, " +
                "fi.id AS fileInfoId, " +
                "fi.file_name AS fileName, " +
                "fi.modify_date AS modifyDate, " +
                "pi.id AS productImageId, " +
                "fi.save_file_name AS saveFileName, " +
                "pi.type AS type " +
                "FROM product p " +
                "LEFT JOIN product_image pi ON pi.product_id = p.id AND pi.type != 'th' " +
                "LEFT JOIN file_info fi ON fi.id = pi.file_id " +
                "WHERE p.id = :productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);

        return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(ProductImage.class));
    }

    private DisplayInfoImage getDisplayInfoImage(Integer displayInfoId) {
        String sql = "SELECT " +
                "di.id AS displayInfoId, " +
                "fi.content_type AS contentType, " +
                "fi.create_date AS createDate, " +
                "fi.modify_date AS modifyDate, " +
                "fi.delete_flag AS deleteFlag, " +
                "dii.id AS displayInfoImageId, " +
                "fi.id AS fileId, " +
                "fi.file_name AS fileName, " +
                "fi.save_file_name AS saveFileName " +
                "FROM display_info di " +
                "LEFT JOIN display_info_image dii ON dii.display_info_id = di.id " +
                "LEFT JOIN file_info fi ON fi.id = dii.file_id " +
                "WHERE di.id = :displayInfo";

        Map<String, Object> params = new HashMap<>();
        params.put("displayInfo", displayInfoId);

        return jdbc.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(DisplayInfoImage.class));
    }

    private DisplayInfo getDisplayInfo(Integer displayInfoId) {
        String sql = "SELECT " +
                "di.id AS displayInfoId, " +
                "di.product_id AS productId, " +
                "di.opening_hours AS openingHours, " +
                "di.place_name AS placeName, " +
                "di.place_street AS placeStreet, " +
                "di.place_lot AS placeLot, " +
                "di.tel AS telephone, " +
                "di.homepage AS homepage, " +
                "di.email AS email, " +
                "di.create_date AS createDate, " +
                "di.modify_date AS modifyDate, " +
                "p.description AS productDescription, " +
                "p.content AS productContent, " +
                "p.event AS productEvent, " +
                "c.id AS categoryId, " +
                "c.name AS categoryName " +
                "FROM display_info di " +
                "LEFT JOIN product p ON p.id = di.product_id " +
                "LEFT JOIN category c ON c.id = p.category_id " +
                "WHERE di.id = :displayInfoId";
        Map<String, Object> params = new HashMap<>();
        params.put("displayInfoId", displayInfoId);

        return jdbc.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(DisplayInfo.class));
    }

    private List<Comment> getComments(Integer productId) {
        String sql = "SELECT " +
                "ruc.id AS commentId, " +
                "ruc.comment AS comment, " +
                "ruc.create_date AS createDate, " +
                "ruc.modify_date AS modifyDate, " +
                "ruc.score AS score, " +
                "ruc.product_id AS productId, " +
                "ruc.reservation_info_id AS reservationInfoId, " +
                "ri.reservation_date AS reservationDate, " +
                "ri.reservation_email AS reservationEmail, " +
                "ri.reservation_name AS reservationName, " +
                "ri.reservation_tel AS reservationTelephone " +
                "FROM reservation_user_comment ruc " +
                "LEFT JOIN reservation_info ri ON ri.id = ruc.reservation_info_id " +
                "WHERE ruc.product_id = :productId " +
                "ORDER BY ruc.id DESC";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);

        List<Comment> comments = jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(Comment.class));
        comments.forEach(comment -> {
            List<CommentImage> commentImages = getCommentImages(comment.getCommentId());
            List<CommentImage> filteredImages = commentImages.stream()
                    .filter(image -> image.getImageId() != null)
                    .collect(Collectors.toList());
            comment.setCommentImages(filteredImages);
        });

        return comments;
    }

    private List<CommentImage> getCommentImages(Integer commentId) {
        String sql = "SELECT " +
                "ruci.id AS imageId, " +
                "ruci.reservation_info_id AS reservationInfoId, " +
                "ruci.reservation_user_comment_id AS reservationUserCommentId, " +
                "ruci.file_id AS fileId, " +
                "fi.file_name AS fileName, " +
                "fi.content_type AS contentType, " +
                "fi.delete_flag AS deleteFlag, " +
                "fi.create_date AS createDate, " +
                "fi.modify_date AS modifyDate, " +
                "fi.save_file_name AS saveFileName " +
                "FROM reservation_user_comment ruc " +
                "LEFT JOIN reservation_user_comment_image ruci ON ruci.reservation_user_comment_id = ruc.id " +
                "LEFT JOIN file_info fi ON fi.id = ruci.file_id " +
                "WHERE ruc.id = :commentId";

        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);

        return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(CommentImage.class));
    }

    public Integer getAllDisplayInfoCount() {
        String sql = "SELECT COUNT(*) FROM display_info";
        return jdbc.queryForObject(sql, Collections.emptyMap(), Integer.class);
    }

    public Integer getDisplayInfoCountByCategory(int categoryId) {
        String sql = "SELECT COUNT(*) " +
                "FROM display_info di " +
                "LEFT JOIN product p ON p.id = di.product_id " +
                "WHERE p.category_id = :categoryId";

        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);

        return jdbc.queryForObject(sql, params, Integer.class);
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

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .displayInfoId(rs.getInt("displayInfoId"))
                    .productId(rs.getInt("productId"))
                    .productDescription(rs.getString("productDescription"))
                    .productContent(rs.getString("productContent"))
                    .placeName(rs.getString("placeName"))
                    .productImageUrl(rs.getString("productImageUrl"))
                    .build();
        }
    }
}