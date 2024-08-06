package com.example.fullstack_study_boostcourse.reservation;

public class CommentImage {
    private String contentType;
    private String createDate;
    private boolean deleteFlag;
    private Integer fileId;
    private String fileName;
    private Integer imageId;
    private String modifyDate;
    private Integer reservationInfoId;
    private Integer reservationUserCommentId;
    private String saveFileName;

    public CommentImage(String contentType, String createDate, boolean deleteFlag, Integer fileId, String fileName, Integer imageId, String modifyDate, Integer reservationInfoId, Integer reservationUserCommentId, String saveFileName) {
        this.contentType = contentType;
        this.createDate = createDate;
        this.deleteFlag = deleteFlag;
        this.fileId = fileId;
        this.fileName = fileName;
        this.imageId = imageId;
        this.modifyDate = modifyDate;
        this.reservationInfoId = reservationInfoId;
        this.reservationUserCommentId = reservationUserCommentId;
        this.saveFileName = saveFileName;
    }

    public CommentImage() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(Integer reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public Integer getReservationUserCommentId() {
        return reservationUserCommentId;
    }

    public void setReservationUserCommentId(Integer reservationUserCommentId) {
        this.reservationUserCommentId = reservationUserCommentId;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }
}
