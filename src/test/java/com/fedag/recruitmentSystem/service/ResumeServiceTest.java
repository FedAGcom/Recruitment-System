package com.fedag.recruitmentSystem.service;

class ResumeServiceTest {
//    @Mock
//    private ResumeRepository resumeRepository;
//
//    @Mock
//    private ResumeMapper resumeMapper;
//
//    @Mock
//    private ExperienceMapper experienceMapper;
//
//    private ResumeServiceImpl resumeService;
//
//    private AutoCloseable autoClosable;
//
//    @BeforeEach
//    void setUp() {
//        autoClosable = MockitoAnnotations.openMocks(this);
//        resumeService = new ResumeServiceImpl(resumeRepository, resumeMapper, experienceMapper);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        autoClosable.close();
//    }
//
//    @Test
//    void itShouldSaveNewResume() {
//        Long resumeId = 1L;
//        Resume mockResume = TestDataProvider.getTestResume(1L, 1L, resumeId);
//        ResumeRequest mockResumeRequest = TestDataProvider.getTestResumeRequest(TestDataProvider.getTestResume(1L, 1L, 1L));
//        BDDMockito.given(resumeMapper.dtoToModel(mockResumeRequest)).willReturn(mockResume);
//        BDDMockito.given(resumeRepository.save(mockResume)).willReturn(mockResume);
//        assertDoesNotThrow(() -> resumeService.save(mockResumeRequest));
//    }
//
//    @Test
//    void itShouldFindResumeById() {
//        Long resumeId = 1L;
//        Resume mockResume = TestDataProvider.getTestResume(1L, 1L, resumeId);
//        ResumeResponse mockResponse = TestDataProvider.getTestResumeResponse(mockResume);
//        BDDMockito.given(resumeRepository.findById(resumeId)).willReturn(java.util.Optional.of(mockResume));
//        BDDMockito.given(resumeMapper.modelToDto(mockResume)).willReturn(mockResponse);
//        ResumeResponse resultResume = resumeService.findById(resumeId);
//        assertThat(resultResume)
//                .isNotNull()
//                .usingRecursiveComparison()
//                .isEqualTo(mockResponse);
//    }
//
//    @Test
//    void itShouldThrowExWhenFindResumeById() {
//        Long resumeId = 1L;
//        Optional<Resume> res = Optional.empty();
//        BDDMockito.given(resumeRepository.findById(resumeId)).willReturn(res);
//        Exception exception = assertThrows(ObjectNotFoundException.class, () -> resumeService.findById(resumeId));
//        String expectedMessage = "Resume with id: " + resumeId + " not found";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
//
//    @ParameterizedTest(name = "test {index}: expected resume names = {1} resume list data = {0}")
//    @MethodSource("dataForTest")
//    void itShouldFindAllResumes(List<Resume> resumes, List<ResumeResponse> resumeResponses, List<String> expected) {
//        BDDMockito.given(resumeRepository.findAll()).willReturn(resumes);
//        BDDMockito.given(resumeMapper.modelToDto(resumes)).willReturn(resumeResponses);
//        List<ResumeResponse> actualPagesResponse = resumeService.getAllResumes();
//        List<String> resumeNames = actualPagesResponse
//                .stream()
//                .map(ResumeResponse::getResumeName)
//                .collect(Collectors.toList());
//        assertThat(resumeNames)
//                .usingRecursiveComparison()
//                .isEqualTo(expected);
//    }
//
//    @ParameterizedTest(name = "test {index}: expected resume names = {1} resume list data = {0}")
//    @MethodSource("dataForTest")
//    void itShouldFindPagebleResumes(List<Resume> resumes, List<ResumeResponse> resumeResponses, List<String> expected) {
//        int limit = 5;
//        Page<Resume> mockedPages = new PageImpl<>(resumes);
//        Page<ResumeResponse> mockedPagesResponse = new PageImpl<>(resumeResponses);
//        BDDMockito.given(resumeRepository.findAll(PageRequest.of(0, limit))).willReturn(mockedPages);
//        BDDMockito.given(resumeMapper.modelToDto(mockedPages)).willReturn(mockedPagesResponse);
//        Page<ResumeResponse> actualPagesResponse = resumeService.getAllResumes(PageRequest.of(0, limit));
//        List<String> resumeNames = actualPagesResponse
//                .get()
//                .map(ResumeResponse::getResumeName)
//                .collect(Collectors.toList());
//        assertThat(resumeNames)
//                .usingRecursiveComparison()
//                .isEqualTo(expected);
//    }
//
//    @Test
//    void itShouldDeleteNewResume() {
//        Long resumeId = 1L;
//        Mockito.doNothing().when(resumeRepository).deleteById(resumeId);
//        assertDoesNotThrow(() -> resumeService.deleteById(resumeId));
//    }
//
//    @Test
//    void testFindResumeByPosition() {
//        List<ResumeResponse> resumeList = new ArrayList<>();
//        Pageable pageable = Mockito.mock(Pageable.class);
//        resumeList.add(new ResumeResponse(1L, "Java developer", ActiveStatus.ACTIVE
//                , LocalDateTime.now(),null, null));
//        Page<ResumeResponse> resumePage = new PageImpl<>(resumeList, pageable, resumeList.size());
//        Mockito.when(resumeService.findByTextFilter("Java developer", pageable)).thenReturn(resumePage);
//        assertEquals(resumeService.findByTextFilter("Java developer", pageable), resumePage);
//    }
//
//    static Stream<Arguments> dataForTest() {
//        // set one
//        List<Resume> resumesArrOne = List.of(
//                TestDataProvider.getTestResume(1L, 1L, 1L),
//                TestDataProvider.getTestResume(2L, 2L, 2L),
//                TestDataProvider.getTestResume(3L, 3L, 3L)
//        );
//
//        List<ResumeResponse> resumeResponsesArrOne = List.of(
//         TestDataProvider.getTestResumeResponse(resumesArrOne.get(0)),
//         TestDataProvider.getTestResumeResponse(resumesArrOne.get(1)),
//         TestDataProvider.getTestResumeResponse(resumesArrOne.get(2))
//        );
//
//        List<String> expectedOne = List.of("Ivan CV", "Ivan CV", "Ivan CV");
//        // set two
//        List<Resume> resumesArrTwo = List.of(
//                TestDataProvider.getTestResume(1L, 1L, 1L),
//                TestDataProvider.getTestResume(2L, 2L, 2L),
//                TestDataProvider.getTestResume(3L, 3L, 3L)
//        );
//
//        List<ResumeResponse> resumeResponsesArrTwo = List.of(
//                TestDataProvider.getTestResumeResponse(resumesArrOne.get(0)),
//                TestDataProvider.getTestResumeResponse(resumesArrOne.get(1)),
//                TestDataProvider.getTestResumeResponse(resumesArrOne.get(2))
//        );
//
//        List<String> expectedTwo = List.of("Ivan CV", "Ivan CV", "Ivan CV");
//
//        return Stream.of(
//                Arguments.of(resumesArrOne, resumeResponsesArrOne, expectedOne),
//                Arguments.of(resumesArrTwo, resumeResponsesArrTwo, expectedTwo)
//        );
//    }
}