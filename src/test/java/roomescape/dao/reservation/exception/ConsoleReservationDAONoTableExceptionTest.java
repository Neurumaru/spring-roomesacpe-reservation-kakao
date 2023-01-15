package roomescape.dao.reservation.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dao.reservation.ReservationDAO;
import roomescape.dao.reservation.SpringReservationDAO;
import roomescape.dto.Reservation;

@DisplayName("콘솔용 데이터베이스 접근 예외처리 테이블")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("test")
@Sql(value = "classpath:/drop.sql")
public class ConsoleReservationDAONoTableExceptionTest {

    private static final LocalDate DATE_DATA = LocalDate.parse("2022-08-01");
    private static final LocalTime TIME_DATA = LocalTime.parse("13:00");
    private static final String NAME_DATA = "test";
    private static final Long THEME_ID_DATA = 2L;

    private ReservationDAO reservationDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        reservationDAO = new SpringReservationDAO(jdbcTemplate);
    }

    @DisplayName("예약 생성) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void insertReservation() {
        Reservation reservation = new Reservation(DATE_DATA, TIME_DATA, NAME_DATA, THEME_ID_DATA);
        assertThat(reservationDAO.create(reservation)).isNull();
    }

    @DisplayName("예약 조회) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void findReservation() {
        assertThat(reservationDAO.find(1L)).isNull();
    }

    @DisplayName("예약 삭제")
    @Test
    void removeReservation() {
        reservationDAO.remove(1L);
    }

    @DisplayName("예약 존재 확인) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void existReservation() {
        Reservation reservation = new Reservation(DATE_DATA, TIME_DATA, NAME_DATA, THEME_ID_DATA);
        assertThat(reservationDAO.exist(reservation)).isNull();
    }

    @DisplayName("예약 아이디 존재 확인) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void existReservationId() {
        assertThat(reservationDAO.existId(1L)).isNull();
    }
}
