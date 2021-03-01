package com.epam.esm.util;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.PurchaseController;
import com.epam.esm.controller.TagController;
import com.epam.esm.controller.UserController;
import com.epam.esm.model.GiftCertificateDto;
import com.epam.esm.model.PurchaseResponseDto;
import com.epam.esm.model.TagDto;
import com.epam.esm.model.UserResponseDto;
import org.springframework.hateoas.Link;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class HateoasLinkBuilder {
    private static final String USER = "user_";
    private static final String GIFT_CERTIFICATE = "giftCertificate_";
    private static final String TAG = "tag_";
    private static final String PURCHASE = "purchase_";

    public static void buildTagsLink(List<TagDto> tags) {
        if (!ObjectUtils.isEmpty(tags)) {
            tags.forEach(HateoasLinkBuilder::buildTagLink);
        }
    }

    public static void buildTagLink(TagDto tag) {
        tag.add(linkTo(TagController.class).slash(tag.getId()).withRel(TAG + tag.getId()));
    }

    public static void buildGiftCertificateLink(GiftCertificateDto giftCertificate) {
        giftCertificate.add(linkTo(GiftCertificateController.class).slash(giftCertificate.getId())
                .withRel(GIFT_CERTIFICATE + giftCertificate.getId()));
        List<TagDto> tags = giftCertificate.getTags();
        buildTagsLink(tags);
    }

    public static void buildGiftCertificatesLink(List<GiftCertificateDto> giftCertificates) {
        if (!ObjectUtils.isEmpty(giftCertificates)) {
            giftCertificates.forEach(HateoasLinkBuilder::buildGiftCertificateLink);
        }
    }

    public static void buildPurchaseLink(PurchaseResponseDto purchase) {
        purchase.add(linkTo(PurchaseController.class).slash(purchase.getId()).withRel(PURCHASE + purchase.getId()));
        List<Long> certificatesId = purchase.getIdGiftCertificates();
        List<Link> links = certificatesId.stream().map(id -> linkTo(GiftCertificateController.class)
                .slash(id).withRel(GIFT_CERTIFICATE + id))
                .collect(Collectors.toList());
        purchase.add(links);
        purchase.add(linkTo(UserController.class).slash(purchase.getIdUser()).
                withRel(USER + purchase.getIdUser()));
    }

    public static void buildPurchasesLink(List<PurchaseResponseDto> purchases) {
        if (!ObjectUtils.isEmpty(purchases)) {
            purchases.forEach(HateoasLinkBuilder::buildPurchaseLink);
        }
    }

    public static void buildUserLink(UserResponseDto user) {
        user.add(linkTo(UserController.class).slash(user.getId()).withRel(USER + user.getId()));
    }

    public static void buildUsersLink(List<UserResponseDto> users) {
        if (!ObjectUtils.isEmpty(users)) {
            users.forEach(HateoasLinkBuilder::buildUserLink);
        }
    }
}