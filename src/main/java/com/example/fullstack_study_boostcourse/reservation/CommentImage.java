package com.example.fullstack_study_boostcourse.reservation;

public class CommentImage {
    private Integer imageId;
    private Integer reservationInfoId;
    private Integer reservationUserCommentId;
    private Integer fileId;
    private String fileName;
    private String contentType;
    private Boolean deleteFlag;
    private String createDate;
    private String modifyDate;
    private String saveFileName;

    public CommentImage(Integer imageId, Integer reservationInfoId, Integer reservationUserCommentId, Integer fileId, String fileName, String contentType, Boolean deleteFlag, String createDate, String modifyDate, String saveFileName) {
        this.imageId = imageId;
        this.reservationInfoId = reservationInfoId;
        this.reservationUserCommentId = reservationUserCommentId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.deleteFlag = deleteFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
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
