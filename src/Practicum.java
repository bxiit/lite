import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Practicum {
    private final static Logger log = LoggerFactory.getLogger(Practicum.class);

    public static void main(String[] args) {
        log.trace("Трасировочное сообщение");
        log.debug("Отладочное сообщение");
        log.info("Информационное сообщение");
        log.warn("Предупреждающее сообщение");
        log.error("Сообщение о критической ошибке");
    }
}