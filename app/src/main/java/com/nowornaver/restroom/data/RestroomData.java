package com.nowornaver.restroom.data;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestroomData {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {

        @SerializedName("header")
        @Expose
        private Header header;
        @SerializedName("body")
        @Expose
        private Body body;

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }

    }

    public class Header {

        @SerializedName("resultCode")
        @Expose
        private String resultCode;
        @SerializedName("resultMsg")
        @Expose
        private String resultMsg;
        @SerializedName("type")
        @Expose
        private String type;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    public class Body {

        @SerializedName("items")
        @Expose
        private List<Item> items = null;
        @SerializedName("totalCount")
        @Expose
        private String totalCount;
        @SerializedName("numOfRows")
        @Expose
        private String numOfRows;
        @SerializedName("pageNo")
        @Expose
        private String pageNo;

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(String numOfRows) {
            this.numOfRows = numOfRows;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

    }

    public class Item {

        @SerializedName("toiletType")
        @Expose
        private String toiletType;
        @SerializedName("toiletNm")
        @Expose
        private String toiletNm;
        @SerializedName("rdnmadr")
        @Expose
        private String rdnmadr;
        @SerializedName("lnmadr")
        @Expose
        private String lnmadr;
        @SerializedName("unisexToiletYn")
        @Expose
        private String unisexToiletYn;
        @SerializedName("menToiletBowlNumber")
        @Expose
        private String menToiletBowlNumber;
        @SerializedName("menUrineNumber")
        @Expose
        private String menUrineNumber;
        @SerializedName("menHandicapToiletBowlNumber")
        @Expose
        private String menHandicapToiletBowlNumber;
        @SerializedName("menHandicapUrinalNumber")
        @Expose
        private String menHandicapUrinalNumber;
        @SerializedName("menChildrenToiletBowlNumber")
        @Expose
        private String menChildrenToiletBowlNumber;
        @SerializedName("menChildrenUrinalNumber")
        @Expose
        private String menChildrenUrinalNumber;
        @SerializedName("ladiesToiletBowlNumber")
        @Expose
        private String ladiesToiletBowlNumber;
        @SerializedName("ladiesHandicapToiletBowlNumber")
        @Expose
        private String ladiesHandicapToiletBowlNumber;
        @SerializedName("ladiesChildrenToiletBowlNumber")
        @Expose
        private String ladiesChildrenToiletBowlNumber;
        @SerializedName("institutionNm")
        @Expose
        private String institutionNm;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("openTime")
        @Expose
        private String openTime;
        @SerializedName("installationYear")
        @Expose
        private String installationYear;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("toiletPossType")
        @Expose
        private String toiletPossType;
        @SerializedName("toiletPosiType")
        @Expose
        private String toiletPosiType;
        @SerializedName("careSewerageType")
        @Expose
        private String careSewerageType;
        @SerializedName("emgBellYn")
        @Expose
        private String emgBellYn;
        @SerializedName("enterentCctvYn")
        @Expose
        private String enterentCctvYn;
        @SerializedName("dipersExchgPosi")
        @Expose
        private String dipersExchgPosi;
        @SerializedName("modYear")
        @Expose
        private String modYear;
        @SerializedName("referenceDate")
        @Expose
        private String referenceDate;
        @SerializedName("insttCode")
        @Expose
        private String insttCode;

        public String getToiletType() {
            return toiletType;
        }

        public void setToiletType(String toiletType) {
            this.toiletType = toiletType;
        }

        public String getToiletNm() {
            return toiletNm;
        }

        public void setToiletNm(String toiletNm) {
            this.toiletNm = toiletNm;
        }

        public String getRdnmadr() {
            return rdnmadr;
        }

        public void setRdnmadr(String rdnmadr) {
            this.rdnmadr = rdnmadr;
        }

        public String getLnmadr() {
            return lnmadr;
        }

        public void setLnmadr(String lnmadr) {
            this.lnmadr = lnmadr;
        }

        public String getUnisexToiletYn() {
            return unisexToiletYn;
        }

        public void setUnisexToiletYn(String unisexToiletYn) {
            this.unisexToiletYn = unisexToiletYn;
        }

        public String getMenToiletBowlNumber() {
            return menToiletBowlNumber;
        }

        public void setMenToiletBowlNumber(String menToiletBowlNumber) {
            this.menToiletBowlNumber = menToiletBowlNumber;
        }

        public String getMenUrineNumber() {
            return menUrineNumber;
        }

        public void setMenUrineNumber(String menUrineNumber) {
            this.menUrineNumber = menUrineNumber;
        }

        public String getMenHandicapToiletBowlNumber() {
            return menHandicapToiletBowlNumber;
        }

        public void setMenHandicapToiletBowlNumber(String menHandicapToiletBowlNumber) {
            this.menHandicapToiletBowlNumber = menHandicapToiletBowlNumber;
        }

        public String getMenHandicapUrinalNumber() {
            return menHandicapUrinalNumber;
        }

        public void setMenHandicapUrinalNumber(String menHandicapUrinalNumber) {
            this.menHandicapUrinalNumber = menHandicapUrinalNumber;
        }

        public String getMenChildrenToiletBowlNumber() {
            return menChildrenToiletBowlNumber;
        }

        public void setMenChildrenToiletBowlNumber(String menChildrenToiletBowlNumber) {
            this.menChildrenToiletBowlNumber = menChildrenToiletBowlNumber;
        }

        public String getMenChildrenUrinalNumber() {
            return menChildrenUrinalNumber;
        }

        public void setMenChildrenUrinalNumber(String menChildrenUrinalNumber) {
            this.menChildrenUrinalNumber = menChildrenUrinalNumber;
        }

        public String getLadiesToiletBowlNumber() {
            return ladiesToiletBowlNumber;
        }

        public void setLadiesToiletBowlNumber(String ladiesToiletBowlNumber) {
            this.ladiesToiletBowlNumber = ladiesToiletBowlNumber;
        }

        public String getLadiesHandicapToiletBowlNumber() {
            return ladiesHandicapToiletBowlNumber;
        }

        public void setLadiesHandicapToiletBowlNumber(String ladiesHandicapToiletBowlNumber) {
            this.ladiesHandicapToiletBowlNumber = ladiesHandicapToiletBowlNumber;
        }

        public String getLadiesChildrenToiletBowlNumber() {
            return ladiesChildrenToiletBowlNumber;
        }

        public void setLadiesChildrenToiletBowlNumber(String ladiesChildrenToiletBowlNumber) {
            this.ladiesChildrenToiletBowlNumber = ladiesChildrenToiletBowlNumber;
        }

        public String getInstitutionNm() {
            return institutionNm;
        }

        public void setInstitutionNm(String institutionNm) {
            this.institutionNm = institutionNm;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getInstallationYear() {
            return installationYear;
        }

        public void setInstallationYear(String installationYear) {
            this.installationYear = installationYear;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getToiletPossType() {
            return toiletPossType;
        }

        public void setToiletPossType(String toiletPossType) {
            this.toiletPossType = toiletPossType;
        }

        public String getToiletPosiType() {
            return toiletPosiType;
        }

        public void setToiletPosiType(String toiletPosiType) {
            this.toiletPosiType = toiletPosiType;
        }

        public String getCareSewerageType() {
            return careSewerageType;
        }

        public void setCareSewerageType(String careSewerageType) {
            this.careSewerageType = careSewerageType;
        }

        public String getEmgBellYn() {
            return emgBellYn;
        }

        public void setEmgBellYn(String emgBellYn) {
            this.emgBellYn = emgBellYn;
        }

        public String getEnterentCctvYn() {
            return enterentCctvYn;
        }

        public void setEnterentCctvYn(String enterentCctvYn) {
            this.enterentCctvYn = enterentCctvYn;
        }

        public String getDipersExchgPosi() {
            return dipersExchgPosi;
        }

        public void setDipersExchgPosi(String dipersExchgPosi) {
            this.dipersExchgPosi = dipersExchgPosi;
        }

        public String getModYear() {
            return modYear;
        }

        public void setModYear(String modYear) {
            this.modYear = modYear;
        }

        public String getReferenceDate() {
            return referenceDate;
        }

        public void setReferenceDate(String referenceDate) {
            this.referenceDate = referenceDate;
        }

        public String getInsttCode() {
            return insttCode;
        }

        public void setInsttCode(String insttCode) {
            this.insttCode = insttCode;
        }

    }

}