package com.microservice.cards.constants;

public class Constants {

    private Constants() {
    }

    public final static String CARD_TYPE_PREPAID = "PREPAID";
    public final static String CARD_TYPE_DEBIT = "DEBIT";
    public final static String CARD_TYPE_CREDIT = "CREDIT";
    public final static Integer CARD_LIMIT = 100000;
    public final static String STATUS_200 = "200";
    public final static String STATUS_113 = "113";
    public final static String STATUS_114 = "114";
    public final static String STATUS_201 = "201";
    public final static String MESSAGE_200 = "Request Processed Successfully";
    public final static String MESSAGE_201 = "Card created Successfully";
    public final static String MESSAGE_113_UPDATE = "Request did not processed. Unable to Update Data";
    public final static String MESSAGE_114_DELETE = "Request did not processed. Unable to Delete Data";

}
