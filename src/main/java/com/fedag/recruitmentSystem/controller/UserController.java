package com.fedag.recruitmentSystem.controller;


import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Контроллер пользователей", description = "Работа с пользователями")
public class UserController {

  @Schema(name = "Сервис пользователей", description = "Содержит имплементацию методов для работы с репозиторием")
  private final UserServiceImpl userService;

  @Operation(summary = "Получение списка пользователей")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<UserResponse> getAllUsers(@PageableDefault(size = 5) Pageable pageable) {
    return userService.getAllUsers(pageable);
  }

  @Operation(summary = "Сортировка списка пользователей по вступительным экзаменам")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список отсортирован",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/filter")
  public List<UserResponse> findByEntranceExamScore(@RequestParam(defaultValue = "0", required = false) int score) {
    return userService.getByEntranceExamScore(score);
  }

  @Operation(summary = "Получение пользователя по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Пользователь получен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable Long id) {
    return userService.findById(id);
  }

  @Operation(summary = "Добавление пользователя")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Пользователь добавлен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addNewUser(@RequestBody UserRequest user) {
    userService.save(user);
  }

  @Operation(summary = "Изменение пользователя")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Пользователь изменен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping
  public void updateUser(@RequestBody UserUpdateRequest user) {
    userService.update(user);
  }

  @Operation(summary = "Удаление пользователя")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Пользователь удален",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
  }

  @Operation(summary = "Сортировка списка пользователей по рейтингу")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список отсортирован",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/filter/stars")
  public List<UserResponse> findByStars(@RequestParam(defaultValue = "0", required = false) byte stars) {
    return userService.getByStars(stars);
  }

  @Operation(summary = "Сортировка списка пользователей по опыту работы")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список отсортирован",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/filter/exp={max}")
  public List<UserResponse> findByExperience(@PathVariable(name = "max") int max) { //если max 0, то общий опыт. если 1, то общий опыт
    return userService.getByExperience(max);
  }
}
