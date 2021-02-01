package com.epam.esm.dao;

public class Queries {
    public static final String SELECT_ALL_TAG = "FROM Tag";
    public static final String SELECT_TAG_BY_NAME = "FROM Tag t WHERE t.name = ?1";
    public static final String SELECT_TAG_BY_NAME_IN_RANGE = "FROM Tag t WHERE t.name IN ?1";
    public static final String SELECT_ALL_GIFT_CERTIFICATES = "FROM GiftCertificate";
    public static final String SELECT_GIFT_CERTIFICATE_BY_NAME = "FROM GiftCertificate g WHERE g.name = ?1";
    public static final String SELECT_ALL_GIFT_CERTIFICATES_BY_TAG_ID = "FROM GiftCertificate g JOIN FETCH g.tags t " +
            "WHERE t.id = ?1";
    public static final String SELECT_GIFT_CERTIFICATE_BY_TAG_ID = "FROM GiftCertificate g JOIN FETCH g.tags t " +
            "WHERE g.id = ?1 AND t.id = ?2";
    public static final String DELETE_TAGS_FROM_GIFT_CERTIFICATE_TAG = "DELETE FROM gift_certificate_tag " +
            "WHERE id_tag = ?";
    public static final String SELECT_ALL_USERS = "FROM User";
    public static final String SELECT_USER_BY_NAME = "FROM User u WHERE u.name = ?1";
    public static final String SELECT_ALL_PURCHASES = "FROM Purchase";

    private Queries() {
    }
}