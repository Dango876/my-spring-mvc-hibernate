папка hiber - лишняя. Дополнительная вложенность здесь не нужна - YES

public String allUser - непонятно, что делает метод по названию - YES

String userInfo - аналогично - YES

if (user.getId() == null) {
userService.add(user);
LOGGER.info("User create: " + user);
} else {
userService.update(user);
LOGGER.info("User update: " + user);
} - для update должен быть отдельный контроллер - YES

UserDaoImpl , UserServiceImpl - переименовать методы - YES

String jpql = "SELECT u FROM User u"; - в константу или сразу в параметры метода - YES