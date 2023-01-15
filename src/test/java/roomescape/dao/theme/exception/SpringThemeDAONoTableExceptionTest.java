package roomescape.dao.theme.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import roomescape.dao.theme.SpringThemeDAO;
import roomescape.dao.theme.ThemeDAO;
import roomescape.dto.Theme;

@DisplayName("JDBC 데이터베이스 접근 - 테마 테이블이 존재하지 않을 경우 null 리턴")
@JdbcTest
@ActiveProfiles("test")
@Sql(value = "classpath:/drop.sql")
public class SpringThemeDAONoTableExceptionTest {

    private static final String NAME_DATA = "워너고홈";
    private static final String DESC_DATA = "병맛 어드벤처 회사 코믹물";
    private static final int PRICE_DATA = 29000;

    private ThemeDAO themeDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        themeDAO = new SpringThemeDAO(jdbcTemplate);
    }

    @DisplayName("테마 생성) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void returnNullWhenCreateIfNotExistingTable() {
        Theme theme = new Theme(NAME_DATA, DESC_DATA, PRICE_DATA);
        assertThat(themeDAO.create(theme)).isNull();
    }

    @DisplayName("테마 조회) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void returnNullWhenFindIfNotExistingTable() {
        assertThat(themeDAO.find(1L)).isNull();
    }

    @DisplayName("테마 목록 조회) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void returnNullWhenListIfNotExistingTable() {
        assertThat(themeDAO.list()).isNull();
    }

    @DisplayName("테마 존재 확인) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void returnNullWhenExistIfNotExistingTable() {
        Theme theme = new Theme(NAME_DATA, DESC_DATA, PRICE_DATA);
        assertThat(themeDAO.exist(theme)).isNull();
    }

    @DisplayName("테마 아이디 존재 확인) 테이블이 존재하지 않을 경우 null 리턴")
    @Test
    void returnNullWhenExistIdIfNotExistingTable() {
        assertThat(themeDAO.existId(1L)).isNull();
    }
}
