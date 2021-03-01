package com.epam.esm.controller;

import com.epam.esm.exception.ResourceAlreadyExistsException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.model.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.util.HateoasLinkBuilder;
import com.epam.esm.util.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * The type Tag controller is used to receive REST API requests
 * with mapping "/tags".
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/tags", produces = APPLICATION_JSON_VALUE)
@Validated
public class TagController {
    private final TagService tagService;

    /**
     * Find all tags.
     *
     * @param pagination contains number of page and amount of pages on each page
     * @return the list
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("permitAll()")
    public List<TagDto> findAll(@NotNull @Valid Pagination pagination) {
        List<TagDto> tags = tagService.findAll(pagination);
        HateoasLinkBuilder.buildTagsLink(tags);
        return tags;
    }

    /**
     * Find tag by id.
     *
     * @param id is the id of tag
     * @return the tag DTO
     * @throws ResourceNotFoundException if tag isn't found
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("permitAll()")
    public TagDto findById(@PathVariable @Positive Long id) {
        TagDto tag = tagService.findById(id);
        HateoasLinkBuilder.buildTagLink(tag);
        return tag;
    }

    /**
     * Find the most widely used tag of a user with the highest cost of all orders.
     *
     * @param pagination contains number of page and amount of pages on each page
     * @return the list of tag DTO
     */
    @GetMapping("/top")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<TagDto> findTopTag(@NotNull @Valid Pagination pagination) {
        List<TagDto> tags = tagService.findTopTag(pagination);
        HateoasLinkBuilder.buildTagsLink(tags);
        return tags;
    }

    /**
     * Add tag.
     *
     * @param tagDto is the tag dto that should be added
     * @return the tag DTO
     * @throws ResourceAlreadyExistsException if tag with such name already exists
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TagDto addTag(@Valid @RequestBody TagDto tagDto) {
        TagDto tag = tagService.add(tagDto);
        HateoasLinkBuilder.buildTagLink(tag);
        return tag;
    }

    /**
     * Delete tag.
     *
     * @param id is the id of tag
     * @throws ResourceNotFoundException if tag with such id isn't found
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteTag(@PathVariable @Positive Long id) {
        tagService.deleteById(id);
    }
}