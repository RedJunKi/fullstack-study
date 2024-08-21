package com.example.fullstack_study_boostcourse.reservation;

import com.example.fullstack_study_boostcourse.guestbook.dto.Guestbook;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private SimpleJdbcInsert commentInsertAction;
    private SimpleJdbcInsert fileInfoInsertAction;
    private SimpleJdbcInsert reservationUserCommentImageInsertAction;
    private RowMapper<DisplayInfo> displayInfoRowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info")
                .usingGeneratedKeyColumns("id");
        this.commentInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment")
                .usingGeneratedKeyColumns("id");
        this.fileInfoInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("file_info")
                .usingGeneratedKeyColumns("id");
        this.reservationUserCommentImageInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_user_comment_image")
                .usingGeneratedKeyColumns("id");
    }

    public List<ReservationInfo> selectByEmail(String email) {

        String sql = "SELECT " +
                "ri.id AS reservationInfoId, " +
                "ri.product_id AS productId, " +
                "ri.display_info_id AS displayInfoId, " +
                "ri.reservation_name AS reservationName, " +
                "ri.reservation_tel AS reservationTelephone, " +
                "ri.reservation_email AS reservationEmail, " +
                "ri.cancel_flag AS cancelYn, " +
                "ri.reservation_date AS reservationDate, " +
                "ri.create_date AS createDate, " +
                "ri.modify_date AS modifyDate, " +
                "c.id AS categoryId, " +
                "c.name AS categoryName, " +
                "p.description AS productDescription, " +
                "p.content AS productContent, " +
                "p.event AS productEvent, " +
                "di.opening_hours AS openingHours, " +
                "di.place_name AS placeName, " +
                "di.place_lot AS placeLot, " +
                "di.place_street AS placeStreet, " +
                "di.tel AS telephone, " +
                "di.homepage AS homepage, " +
                "di.email AS email, " +
                "di.create_date AS displayCreateDate, " +
                "di.modify_date AS displayModifyDate, " +
                "(" +
                "SELECT SUM(rip.count * pp.price) " +
                "FROM reservation_info_price rip " +
                "JOIN product_price pp ON rip.product_price_id = pp.id " +
                "WHERE rip.reservation_info_id = ri.id" +
                ") AS totalPrice " +
                "FROM reservation_info ri " +
                "LEFT JOIN display_info di ON ri.display_info_id = di.id " +
                "LEFT JOIN product p ON ri.product_id = p.id " +
                "LEFT JOIN category c ON p.category_id = c.id " +
                "WHERE ri.reservation_email = :email";


        Map<String, Object> params = new HashMap<>();
        params.put("email", email);

        return jdbc.query(sql, params, new ReservationRowMapper());
    }

    public Long insertReservation(ReservationInfo reservationInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", reservationInfo.getProductId());
        params.put("displayInfoId", reservationInfo.getDisplayInfoId());
        params.put("reservationName", reservationInfo.getReservationName());
        params.put("reservationTel", reservationInfo.getReservationTelephone());
        params.put("reservationEmail", reservationInfo.getReservationEmail());
        params.put("cancelFlag", reservationInfo.isCancelYn());
        params.put("reservationDate", reservationInfo.getReservationDate());
        params.put("createDate", reservationInfo.getCreateDate());
        params.put("modifyDate", reservationInfo.getModifyDate());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);

        return insertAction.executeAndReturnKey(sqlParameterSource).longValue();
    }

    public DisplayInfo findDisplayInfoById(Integer displayInfoId) {
        String sql = "SELECT * FROM display_info WHERE id = :displayInfoId";
        Map<String, Object> params = new HashMap<>();
        params.put("displayInfoId", displayInfoId);

        return jdbc.queryForObject(sql, params, displayInfoRowMapper);
    }

    public Integer calculateTotalPrice(Integer productId) {
        String sql = "SELECT SUM(price) FROM product_price WHERE product_id = :productId";
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);

        return jdbc.queryForObject(sql, params, Integer.class);
    }

    public int updateReservation(ReservationInfo reservationInfo) {
        String sql = "UPDATE reservation_info SET cancel_flag = :cancelYn WHERE id = :reservationInfoId";
        SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);

        return jdbc.update(sql, params);
    }

    public int deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservation_info WHERE id = :reservationId";
        Map<String, Object> params = new HashMap<>();
        params.put("reservationId", reservationId);
        return jdbc.update(sql, params);
    }

    public ReservationInfo selectByReservationId(int reservationId) {
        String sql = "SELECT * FROM reservation_info WHERE id = :reservationId";
        Map<String, Object> params = new HashMap<>();
        params.put("reservationId", reservationId);

        return jdbc.queryForObject(sql, params, new RowMapper<ReservationInfo>() {
            @Override
            public ReservationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ReservationInfo.builder()
                        .reservationInfoId(rs.getInt("id"))
                        .displayInfoId(rs.getInt("display_info_id"))
                        .productId(rs.getInt("product_id"))
                        .reservationName(rs.getString("reservation_name"))
                        .reservationTelephone(rs.getString("reservation_tel"))
                        .reservationEmail(rs.getString("reservation_email"))
                        .reservationDate(rs.getString("reservation_date"))
                        .cancelYn(rs.getBoolean("cancel_flag"))
                        .createDate(rs.getString("create_date"))
                        .modifyDate(rs.getString("modify_date"))
                        .build();
            }
        });
    }

    public Long insertComment(Comment commentBuild) {

        Map<String, Object> params = new HashMap<>();
        params.put("productId", commentBuild.getProductId());
        params.put("reservationInfoId", commentBuild.getReservationInfoId());
        params.put("score", commentBuild.getScore());
        params.put("comment", commentBuild.getComment());
        params.put("createDate", commentBuild.getCreateDate());
        params.put("modifyDate", commentBuild.getModifyDate());

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        return commentInsertAction.executeAndReturnKey(sqlParameterSource).longValue();
    }

    public long insertFileInfo(MultipartFile file, String filename, String saveFilename) {
//        String sql = "INSERT INTO file_info (file_name, save_file_name, content_type, delete_flag, create_date, modify_date) " +
//                "VALUE (:fileName, :saveFileName, :contentType, :deleteFlag, now(), now())";

        Map<String, Object> params = new HashMap<>();

        params.put("contentType", file.getContentType());
        params.put("fileName", filename);
        params.put("saveFileName", saveFilename);
        params.put("deleteFlag", false);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);

        return fileInfoInsertAction.executeAndReturnKey(sqlParameterSource).longValue();
    }

    public void insertReservationUserCommentImage(int reservationInfoId, long fileInfoId, int commentId) {
//        String sql = "INSERT INTO reservation_user_comment_image (reservation_info_id, reservation_user_comment_id, file_id) " +
//                "VALUES (:reservationInfoId, :commentId, :fileInfoId)";

        Map<String, Object> params = new HashMap<>();
        params.put("reservationInfoId", reservationInfoId);
        params.put("fileId", fileInfoId);
        params.put("reservationUserCommentId", commentId);

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        reservationUserCommentImageInsertAction.executeAndReturnKey(sqlParameterSource);
    }

    public void insertReservationPrice(ReservationPrice prices, long reservationId) {
        String sql = "INSERT INTO reservation_info_price (reservation_info_id, product_price_id, count) " +
                "VALUES (:reservationInfoId, :productPriceId, :count)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        Integer reservationInfoId = (int) reservationId;
        Integer productPriceId = prices.getProductPriceId();
        Integer count = prices.getCount();

        System.out.println("reservationInfoId = " + reservationInfoId);
        System.out.println("productPriceId = " + productPriceId);
        System.out.println("count = " + count);

        Map<String, Object> params = new HashMap<>();
        params.put("reservationInfoId", reservationInfoId);
        params.put("productPriceId", productPriceId);
        params.put("count", count);

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);

        jdbc.update(sql, sqlParameterSource, keyHolder, new String[]{"id"});
    }

    private static class ReservationRowMapper implements RowMapper<ReservationInfo> {
        @Override
        public ReservationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            DisplayInfo displayInfo = DisplayInfo.builder()
                    .productId(rs.getInt("productId"))
                    .categoryId(rs.getInt("categoryId"))
                    .displayInfoId(rs.getInt("displayInfoId"))
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

            ReservationInfo result = ReservationInfo.builder()
                    .reservationInfoId(rs.getInt("reservationInfoId"))
                    .productId(rs.getInt("productId"))
                    .displayInfoId(rs.getInt("displayInfoId"))
                    .reservationName(rs.getString("reservationName"))
                    .reservationTelephone(rs.getString("reservationTelephone"))
                    .reservationEmail(rs.getString("reservationEmail"))
                    .cancelYn(rs.getBoolean("cancelYn"))
                    .reservationDate(rs.getString("reservationDate"))
                    .createDate(rs.getString("displayCreateDate"))
                    .modifyDate(rs.getString("displayModifyDate"))
                    .displayInfo(displayInfo)
                    .totalPrice(rs.getInt("totalPrice"))
                    .build();

            return result;
        }
    }
}

