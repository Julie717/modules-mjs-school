package com.epam.esm.controller;

import com.epam.esm.model.PurchaseRequestDto;
import com.epam.esm.model.PurchaseResponseDto;
import com.epam.esm.model.UserDto;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.UserService;
import com.epam.esm.util.HateoasLinkBuilder;
import com.epam.esm.util.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
@Validated
public class UserController {
    private final UserService userService;
    private final PurchaseService purchaseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAllUsers(@Valid @NotNull Pagination pagination,
                                      @Size(min = 1, max = 50) String surname) {
        List<UserDto> users;
        if (surname == null || surname.isEmpty()) {
            users = userService.findAll(pagination);
        } else {
            users = userService.findBySurname(pagination, surname);
        }
        HateoasLinkBuilder.buildUsersLink(users);
        return users;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findById(@PathVariable @Positive Long id) {
        UserDto user = userService.findById(id);
        HateoasLinkBuilder.buildUserLink(user);
        return user;
    }

    @PostMapping("/{idUser}/purchases")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseResponseDto makePurchase(@PathVariable @Positive Long idUser,
                                            @RequestBody PurchaseRequestDto purchaseDto) {
        purchaseDto.setIdUser(idUser);
        PurchaseResponseDto purchase = purchaseService.makePurchase(purchaseDto);
        HateoasLinkBuilder.buildPurchaseLink(purchase);
        return purchase;
    }
}