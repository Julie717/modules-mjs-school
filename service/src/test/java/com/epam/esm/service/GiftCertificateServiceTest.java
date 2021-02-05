package com.epam.esm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

public class GiftCertificateServiceTest {
   /* @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private TagService tagService;
    @Spy
    private final TagConverterImpl tagConverter = new TagConverterImpl();
    @Spy
    private final GiftCertificateConverterImpl giftCertificateConverter = new GiftCertificateConverterImpl(tagConverter);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTestEmptyList() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        Mockito.when(giftCertificateDao.findAll()).thenReturn(giftCertificates);
        List<GiftCertificateDto> giftCertificatesDto = new ArrayList<>();

        List<GiftCertificateDto> actual = giftCertificateService.findAll();

        verify(giftCertificateConverter).convertTo(giftCertificates);
        assertEquals(giftCertificatesDto, actual);
    }

    @Test
    void findAllTestPositive() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        giftCertificates.add(new GiftCertificate(1, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null));
        Mockito.when(giftCertificateDao.findAll()).thenReturn(giftCertificates);
        List<GiftCertificateDto> giftCertificatesDto = new ArrayList<>();
        giftCertificatesDto.add(new GiftCertificateDto(1, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null));

        List<GiftCertificateDto> actual = giftCertificateService.findAll();

        verify(giftCertificateConverter, times(1)).convertTo(giftCertificates);
        assertEquals(giftCertificatesDto, actual);
    }

    @Test
    void findAllWithTagsTest() {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "gift"));
        tags.add(new Tag(2, "sport"));
        tags.add(new Tag(7, "make you fun"));
        giftCertificates.add(new GiftCertificate(1, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), tags));
        tags = new ArrayList<>();
        tags.add(new Tag(2, "sport"));
        tags.add(new Tag(5, "wonderful gift"));
        tags.add(new Tag(7, "make you fun"));
        giftCertificates.add(new GiftCertificate(2, "Fitness", "Physical fitness is a state of health and " +
                "well-being and, more specifically, the ability to perform aspects of sports, " +
                "occupations and daily activities. Physical fitness is generally achieved through" +
                " proper nutrition, moderate-vigorous physical exercise, and sufficient rest.",
                BigDecimal.valueOf(80), 30, Timestamp.valueOf("2021-01-11 10:30:01"),
                Timestamp.valueOf("2021-01-11 10:30:01"), tags));
        Mockito.when(giftCertificateDao.findAllWithTags()).thenReturn(giftCertificates);
        List<GiftCertificateDto> giftCertificatesDto = new ArrayList<>();
        List<TagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new TagDto(1, "gift"));
        tagsDto.add(new TagDto(2, "sport"));
        tagsDto.add(new TagDto(7, "make you fun"));
        giftCertificatesDto.add(new GiftCertificateDto(1, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), tagsDto));
        tagsDto = new ArrayList<>();
        tagsDto.add(new TagDto(2, "sport"));
        tagsDto.add(new TagDto(5, "wonderful gift"));
        tagsDto.add(new TagDto(7, "make you fun"));
        giftCertificatesDto.add(new GiftCertificateDto(2, "Fitness", "Physical fitness is a state of health and " +
                "well-being and, more specifically, the ability to perform aspects of sports, " +
                "occupations and daily activities. Physical fitness is generally achieved through" +
                " proper nutrition, moderate-vigorous physical exercise, and sufficient rest.",
                BigDecimal.valueOf(80), 30, Timestamp.valueOf("2021-01-11 10:30:01"),
                Timestamp.valueOf("2021-01-11 10:30:01"), tagsDto));

        List<GiftCertificateDto> actual = giftCertificateService.findAllWithTags();

        assertEquals(giftCertificatesDto, actual);
    }

    @Test
    void findByIdTestPositive() {
        Long id = 2;
        Optional<GiftCertificate> giftCertificate = Optional.of(new GiftCertificate(2, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null));
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(giftCertificate);
        GiftCertificateDto expected = new GiftCertificateDto(2, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);

        GiftCertificateDto actual = giftCertificateService.findById(id);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdTestNegative() {
        Long id = 25;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findById(id));
    }

    @Test
    void addTestPositive() {
        GiftCertificate giftCertificate = new GiftCertificate(2, "Skating",
                "Ice skating is a sport in which people slide " +
                        "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                        "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        Mockito.when(giftCertificateDao.findGiftCertificateByName(giftCertificate.getName())).thenReturn(Optional.empty());
        Mockito.when(giftCertificateDao.add(any(GiftCertificate.class))).thenReturn(giftCertificate);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(2, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);

        GiftCertificateDto actual = giftCertificateService.add(giftCertificateDto);

        actual.setCreateDate(giftCertificateDto.getCreateDate());
        actual.setLastUpdateDate(giftCertificateDto.getLastUpdateDate());
        assertEquals(giftCertificateDto, actual);
    }

    @Test
    void addTestNegative() {
        Mockito.when(giftCertificateDao.findGiftCertificateByName(anyString())).thenReturn(Optional.of(new GiftCertificate()));
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("Skating");

        assertThrows(ResourceAlreadyExistsException.class, () -> giftCertificateService.add(giftCertificateDto));
    }

    @Test
    void deleteByIdTestPositive() {
        Long id = 8;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.of(new GiftCertificate()));
        Mockito.when(giftCertificateDao.deleteFromGiftCertificateTags(id)).thenReturn(true);
        Mockito.when(giftCertificateDao.deleteById(id)).thenReturn(true);

        assertDoesNotThrow(() -> giftCertificateService.deleteById(id));
    }

    @Test
    void deleteByIdTestNegative() {
        Long id = 8;
        Mockito.when(giftCertificateDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.deleteById(id));
    }

    @Test
    void findByParametersTestPositive() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("nameGiftCertificate", "gift");
        parameters.put("description", "beautiful");
        parameters.put("sort", "nameGiftCertificate,-createDate");
        String partQuery = " WHERE name_gift_certificate LIKE '%gift%' AND description LIKE '%beautiful%' " +
                "ORDER BY name_gift_certificate ASC, create_date DESC";
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        GiftCertificate giftCertificate = new GiftCertificate(2, "Skating",
                "Ice skating is a sport in which people slide " +
                        "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                        "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        giftCertificates.add(giftCertificate);
        Mockito.when(giftCertificateDao.findByParameters(partQuery)).thenReturn(giftCertificates);
        List<GiftCertificateDto> giftCertificatesDto = new ArrayList<>();
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(2, "Skating", "Ice skating is a sport in which people slide " +
                "over a smooth ice surface on steel-bladed skates. Millions of people skate in " +
                "those parts of the world where the winters are cold enough.", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        giftCertificatesDto.add(giftCertificateDto);

        List<GiftCertificateDto> actual = giftCertificateService.findByParameters(parameters);

        assertEquals(giftCertificatesDto, actual);
    }

    @Test
    void findByParametersTestIncorrectInputParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("color", "gift");
        parameters.put("description", "beautiful");

        assertThrows(IllegalParameterException.class, () -> giftCertificateService.findByParameters(parameters));
    }

    @Test
    void findByParametersTestNotFound() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("nameGiftCertificate", "gift");
        String partQuery = " WHERE name_gift_certificate LIKE '%gift%'";
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        Mockito.when(giftCertificateDao.findByParameters(partQuery)).thenReturn(giftCertificates);

        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findByParameters(parameters));
    }

    @Test
    void updateGiftCertificateTestPositive() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(2, "Skating",
                "It's wonderful", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        GiftCertificate giftCertificate = new GiftCertificate(2, "Fitness",
                "It's wonderful", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        Mockito.when(giftCertificateDao.findById(giftCertificateDto.getId()))
                .thenReturn(Optional.of(giftCertificate));
        Mockito.when(giftCertificateDao.findGiftCertificateByName(giftCertificateDto.getName()))
                .thenReturn(Optional.empty());
        giftCertificate.setName(giftCertificateDto.getName());
        Mockito.when(giftCertificateDao.update(giftCertificate)).thenReturn(giftCertificate);

        GiftCertificateDto actual = giftCertificateService.updateGiftCertificate(giftCertificateDto);

        Timestamp lastUpdateDate = Timestamp.valueOf(LocalDateTime.now());
        actual.setLastUpdateDate(lastUpdateDate);
        giftCertificateDto.setLastUpdateDate(lastUpdateDate);
        assertEquals(giftCertificateDto, actual);
    }

    @Test
    void updateGiftCertificateTestNotFound() {
        Mockito.when(giftCertificateDao.findById(anyInt())).thenReturn(Optional.empty());
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setId(15);

        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.updateGiftCertificate(giftCertificateDto));
    }

    @Test
    void updateGiftCertificateTestAlreadyExist() {
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(2, "Skating",
                "It's wonderful", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        GiftCertificate giftCertificate = new GiftCertificate(2, "Fitness",
                "It's wonderful", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        Mockito.when(giftCertificateDao.findById(giftCertificateDto.getId()))
                .thenReturn(Optional.of(giftCertificate));
        GiftCertificate giftCertificateFound = new GiftCertificate(14, "Skating",
                "It's wonderful", BigDecimal.valueOf(9),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        Mockito.when(giftCertificateDao.findGiftCertificateByName(giftCertificateDto.getName()))
                .thenReturn(Optional.of(giftCertificateFound));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> giftCertificateService.updateGiftCertificate(giftCertificateDto));
    }

    @Test
    void findGiftCertificateWithTagsTestPositive() {
        Long id = 2;
        GiftCertificate giftCertificate = new GiftCertificate(2, "Skating",
                "It's wonderful", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(2, "Skating"));
        giftCertificate.setTags(tags);
        Mockito.when(giftCertificateDao.findGiftCertificateWithTags(id)).thenReturn(Optional.of(giftCertificate));
        GiftCertificateDto expected = new GiftCertificateDto(2, "Skating",
                "It's wonderful", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        List<TagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new TagDto(2, "Skating"));
        expected.setTags(tagsDto);

        GiftCertificateDto actual = giftCertificateService.findGiftCertificateWithTags(id);

        assertEquals(expected, actual);
    }

    @Test
    void findGiftCertificateWithTagsTestNegative() {
        Long id = 25;
        Mockito.when(giftCertificateDao.findGiftCertificateWithTags(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> giftCertificateService.findGiftCertificateWithTags(id));
    }

    @Test
    void findGiftCertificateWithTagsByTagNameTestPositive() {
        Long id = 2;
        String name = "gift";
        GiftCertificate giftCertificate = new GiftCertificate(2, "Skating",
                "It's wonderful gift", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(2, "wonderful gift"));
        giftCertificate.setTags(tags);
        Mockito.when(giftCertificateDao.findGiftCertificateWithTagsByTagName(id, name))
                .thenReturn(Optional.of(giftCertificate));
        GiftCertificateDto expected = new GiftCertificateDto(2, "Skating",
                "It's wonderful gift", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        List<TagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new TagDto(2, "wonderful gift"));
        expected.setTags(tagsDto);

        GiftCertificateDto actual = giftCertificateService.findGiftCertificateWithTagsByTagName(id, name);

        assertEquals(expected, actual);
    }

    @Test
    void findGiftCertificateWithTagsByTagNameNegative() {
        Long id = 25;
        String name = "gift";
        Mockito.when(giftCertificateDao.findGiftCertificateWithTagsByTagName(id, name))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                giftCertificateService.findGiftCertificateWithTagsByTagName(id, name));
    }

    @Test
    void addTagsToGiftCertificateTestPositive() {
        Long id = 2;
        List<TagDto> tagsDto = new ArrayList<>();
        tagsDto.add(new TagDto(2, "wonderful gift"));
        tagsDto.add(new TagDto(5, "skating"));
        GiftCertificate giftCertificate = new GiftCertificate(2, "Skating",
                "It's wonderful gift", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(2, "wonderful gift"));
        giftCertificate.setTags(tags);
        Mockito.when(giftCertificateDao.findGiftCertificateWithTags(id))
                .thenAnswer(new Answer() {
                    private int count = 0;

                    public Object answer(InvocationOnMock invocation) {
                        if (count != 0) {
                            List<Tag> tags = new ArrayList<>();
                            tags.add(new Tag(2, "wonderful gift"));
                            tags.add(new Tag(5, "skating"));
                            giftCertificate.setTags(tags);
                        }
                        count++;
                        return Optional.of(giftCertificate);
                    }
                });
        List<TagDto> tagsAlreadyExist = new ArrayList<>();
        tagsAlreadyExist.add(new TagDto(2, "wonderful gift"));
        Mockito.when(tagService.findByRangeNames(tagsDto)).thenReturn(tagsAlreadyExist);
        Mockito.when(giftCertificateDao.isGiftCertificateWithTagExist(id,
                2)).thenReturn(true);
        Mockito.when(giftCertificateDao.isGiftCertificateWithTagExist(id,
                5)).thenReturn(false);
        TagDto tagDto = new TagDto(5, "skating");
        Mockito.when(tagService.add(tagDto)).thenReturn(tagDto);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto(2, "Skating",
                "It's wonderful gift", BigDecimal.valueOf(10),
                30, Timestamp.valueOf("2021-01-10 12:15:37"),
                Timestamp.valueOf("2021-01-10 12:15:37"), null);
        giftCertificateDto.setTags(tagsDto);

        GiftCertificateDto actual = giftCertificateService.addTagsToGiftCertificate(id, tagsDto);

        verify(giftCertificateDao, times(1)).addTagToGiftCertificate(id, 5);
        verify(giftCertificateDao, times(2)).findGiftCertificateWithTags(id);
        assertEquals(giftCertificateDto, actual);
    }

    @Test
    void addTagsToGiftCertificateTestNegative() {
        Long id = 2;
        Mockito.when(giftCertificateDao.findGiftCertificateWithTags(id)).thenThrow(ResourceNotFoundException.class);
        GiftCertificateDto giftCertificateDto = new GiftCertificateDto();
        giftCertificateDto.setName("Skating");

        assertThrows(ResourceNotFoundException.class,
                () -> giftCertificateService.addTagsToGiftCertificate(id, anyList()));
    }

    @Test
    void deleteTagFromGiftCertificateTestPositive() {
        Long id = 8;
        Long id = 2;
        Mockito.when(giftCertificateDao.findById(id))
                .thenReturn(Optional.of(new GiftCertificate()));
        Mockito.when(giftCertificateDao.isGiftCertificateWithTagExist(id, id))
                .thenReturn(true);
        Mockito.when(giftCertificateDao.deleteFromGiftCertificateTag(id, id))
                .thenReturn(true);

        assertDoesNotThrow(() -> giftCertificateService.deleteTagFromGiftCertificate(id, id));
    }

    @Test
    void deleteTagFromGiftCertificateTestGiftCertificateNotFound() {
        Long id = 8;
        Long id = 2;
        Mockito.when(giftCertificateDao.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> giftCertificateService.deleteTagFromGiftCertificate(id, id));
    }

    @Test
    void deleteTagFromGiftCertificateTestGiftCertificateWithTagNotFound() {
        Long id = 8;
        Long id = 2;
        Mockito.when(giftCertificateDao.findById(id))
                .thenReturn(Optional.of(new GiftCertificate()));
        Mockito.when(giftCertificateDao.isGiftCertificateWithTagExist(id, id))
                .thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> giftCertificateService.deleteTagFromGiftCertificate(id, id));
    }*/
}