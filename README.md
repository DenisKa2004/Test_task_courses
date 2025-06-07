# Тестовое приложение «Курсы»

> Демонстрирует современный Android-стек: Jetpack Compose, Hilt, Retrofit, Room и анимации.

---

## 📺 Демонстрация

![Демонстрации](video_2025-06-07_02-52-39_1.mp4)  


---

## Экранные модули

### 🔐 LoginScreen
- Ввод Email / Пароля с валидацией (маска `текст@текст.текст`, без кириллицы)  
- Кнопки «Регистрация» и «Забыл пароль» неактивны  
- Социальный логин (VK, OK) через Intent в браузер  

### 📚 MainScreen
- **Поиск** по названию курса  
- **Сортировка** по дате публикации нажатием «По дате добавления»  
- Список карточек курсов с:
  - Обложкой (Compose Image)  
  - Бейджем рейтинга и датой на полупрозрачном блюр-фоне  
  - Заголовком и описанием (обрезка до 2 строк)  
  - Ценой и ссылкой «Подробнее →»  
  - Кнопкой «Избранное» (toggle + запись в Room)  

### ⭐ FavouritesScreen
- TopAppBar с заголовком **«Избранное»**  
- Отображает только курсы с `hasLike == true`  
- Возможность снять/поставить «избранное»  

---

## 🚀 Стек технологий

- **UI & Animation**  
  - Jetpack Compose (Material 3)  
  - Navigation Compose + Accompanist Navigation-Animation  
  - Плавные переходы: `slideInHorizontally`, `fadeIn`, `scaleIn`  

- **DI & Codegen**  
  - Hilt + Kotlin KSP  

- **Сеть & JSON**  
  - Retrofit 2 + OkHttp + Gson  
  - `LocalJsonInterceptor` для локального `courses.json`  

- **Локальная БД**  
  - Room (`CourseEntity`, `CourseDao`)  
  - Reactive Flow: `Flow<List<CourseEntity>>`  
  - Слияние JSON + текущие избранные  

- **Асинхронность**  
  - Coroutines + `StateFlow`  

- **Дата/Время**  
  - `java.time` для парсинга `yyyy-MM-dd` и форматирования в «dd MMMM yyyy» (RU)  

---

## 🏗️ Архитектура

1. **Data Layer**  
   - `CourseApiService` (Retrofit)  
   - `CourseDao` (Room)  
2. **Repository**  
   - `CourseRepository` ← слияние remote + local  
3. **Presentation**  
   - `MainScreenViewModel` → `StateFlow<List<UiCourse>>`  
   - Composables → `collectAsState()`  

---

