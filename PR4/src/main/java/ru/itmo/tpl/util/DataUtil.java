package ru.itmo.tpl.util;

import ru.itmo.tpl.model.Color;
import ru.itmo.tpl.model.MenuElement;
import ru.itmo.tpl.model.Post;
import ru.itmo.tpl.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mikhail Mirzayanov", Color.RED),
            new User(2, "tourist", "Genady Korotkevich", Color.RED),
            new User(3, "emusk", "Elon Musk", Color.GREEN),
            new User(5, "pashka", "Pavel Mavrin", Color.RED),
            new User(7, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "cannon147", "Erofey Bashunov", Color.GREEN),
            new User(13, "Sagolbah", "Daniil Boger", Color.BLUE)
    );

    private static final List<MenuElement> MENU = Arrays.asList(
            new MenuElement("Index", "/index"),
            new MenuElement("Help", "/misc/help"),
            new MenuElement("Users", "/users")
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "О сорванном раунде Технокубка", "Скоро мы поднимем m666.codeforces.com. Главное не бухтеть и переждать, всё схвачено.", 1),
            new Post(2, "Tool - Fear Inoculum", "Immunity, long overdue\n" +
                    "Contagion, I exhale you\n" +
                    "Naive, I opened up to you\n" +
                    "Venom and mania\n" +
                    "Now, contagion, I exhale you\n" +
                    "\n" +
                    "The deceiver says, he says\n" +
                    "\"You belong to me\n" +
                    "You don't wanna breathe the light of the others\n" +
                    "Fear the light, fear the breath\n" +
                    "Fear the others for eternity\"\n" +
                    "But I hear them now, inhale the clarity\n" +
                    "Hear the venom, the venom in\n" +
                    "What you say inoculated\n" +
                    "Bless this immunity\n" +
                    "Bless this immunity\n" +
                    "Bless this immunity\n" +
                    "\n" +
                    "Exhale, expel\n" +
                    "Recast my tale\n" +
                    "Weave my allegorical elegy\n" +
                    "\n" +
                    "Enumerate all that I'm to do\n" +
                    "Calculating steps away from you\n" +
                    "My own mitosis\n" +
                    "Growing through division from mania\n" +
                    "\n" +
                    "Exhale, expel\n" +
                    "Recast my tale\n" +
                    "Weave my allegorical elegy\n" +
                    "\n" +
                    "Forfeit all control\n" +
                    "You poison, you spectacle\n" +
                    "Exorcise the spectacle\n" +
                    "Exorcise the malady\n" +
                    "Exorcise the disparate\n" +
                    "Poison for eternity\n" +
                    "Purge me and evacuate\n" +
                    "The venom and the fear that binds me\n" +
                    "\n" +
                    "Your veil now, lift away\n" +
                    "I see you runnin'\n" +
                    "Deceiver chased away\n" +
                    "A long time comin'", 13),
            new Post(3, "Юморески", "Учёные провели эксперимент: включили классическую музыку цветку и он расцвёл. Когда дело дошло до панк рока цветок завял, а горшок умер.", 3),
            new Post(4, "Codeforces Round #595 (Div. 3)", "UPD: Спасибо Дарье ZeroAmbition Степановой, Михаилу PikMike Пикляеву и Артему Rox Плоткину за помощь с подготовкой раунда!\n" +
                    "\n" +
                    "<almost-copy-pasted-part>\n" +
                    "\n" +
                    "Привет! В вторник, 22 октября 2019 г. в 17:35 начнётся Codeforces Round #595 (Div. 3) — очередной Codeforces раунд для третьего дивизиона. В этом раунде будет 6 или 7 задач (или 8), которые подобраны по сложности так, чтобы составить интересное соревнование для участников с рейтингами до 1600. Однако все желающие, чей рейтинг 1600 и выше могут зарегистрироваться на раунд вне конкурса.\n" +
                    "\n" +
                    "Раунд пройдет по правилам образовательных раундов. Таким образом, во время раунда задачи будут тестироваться на предварительных тестах, а после раунда будет 12-ти часовая фаза открытых взломов. Я постарался сделать приличные тесты — так же как и вы буду расстроен, если у многих попадают решения после окончания контеста.", 1),
            new Post(5, "Hello World", "Это последний пост", 13)
    );

    private static List<User> getUsers() {
        return USERS;
    }

    private static List<MenuElement> getMenu() {
        return MENU;
    }

    private static List<Post> getPosts() {
        return POSTS;
    }

    public static void putData(Map<String, Object> data) {
        data.put("users", getUsers());
        data.put("menu", getMenu());
        data.put("posts", getPosts());
        for (User user : getUsers()) {
            if (Long.toString(user.getId()).equals(data.get("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }

}
