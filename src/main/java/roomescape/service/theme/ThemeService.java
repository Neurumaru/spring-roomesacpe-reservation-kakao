package roomescape.service.theme;

import java.util.List;
import roomescape.dao.theme.ThemeDAO;
import roomescape.dto.Theme;
import roomescape.exception.BadRequestException;

public class ThemeService implements ThemeServiceInterface {

    private final ThemeDAO themeDAO;

    public ThemeService(ThemeDAO themeDAO) {
        this.themeDAO = themeDAO;
    }

    @Override
    public Long create(Theme theme) {
        validateCreateTheme(theme);
        return themeDAO.create(theme);
    }

    private void validateCreateTheme(Theme theme) {
        throwIfInvalidTheme(theme);
        throwIfExistTheme(theme);
    }

    private void throwIfInvalidTheme(Theme theme) {
        if (theme.getName() == null || theme.getDesc() == null) {
            throw new BadRequestException();
        }
    }

    private void throwIfExistTheme(Theme theme) {
        if (themeDAO.exist(theme)) {
            throw new BadRequestException();
        }
    }

    @Override
    public List<Theme> list() {
        return themeDAO.list();
    }

    @Override
    public void remove(Long id) {
        throwIfNotExistId(id);
        themeDAO.remove(id);
    }

    private void throwIfNotExistId(Long id) {
        if(!themeDAO.existId(id)) {
            throw new BadRequestException();
        }
    }
}
