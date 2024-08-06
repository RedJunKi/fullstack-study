package com.example.fullstack_study_boostcourse.product;

public class DisplayInfoImage {
    private String contentType;
    private String createDate;
    private boolean deleteFlag;
    private Integer displayInfoId;
    private Integer displayInfoImageId;
    private Integer fileId;
    private String fileName;
    private String modifyDate;
    private String saveFileName;

    public DisplayInfoImage() {
    }

    public DisplayInfoImage(String contentType, String createDate, boolean deleteFlag, Integer displayInfoId, Integer displayInfoImageId, Integer fileId, String fileName, String modifyDate, String saveFileName) {
        this.contentType = contentType;
        this.createDate = createDate;
        this.deleteFlag = deleteFlag;
        this.displayInfoId = displayInfoId;
        this.displayInfoImageId = displayInfoImageId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.modifyDate = modifyDate;
        this.saveFileName = saveFileName;
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

    public Integer getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Integer displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public Integer getDisplayInfoImageId() {
        return displayInfoImageId;
    }

    public void setDisplayInfoImageId(Integer displayInfoImageId) {
        this.displayInfoImageId = displayInfoImageId;
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

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }
}
