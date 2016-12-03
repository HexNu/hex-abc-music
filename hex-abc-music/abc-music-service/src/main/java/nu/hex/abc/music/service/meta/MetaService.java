package nu.hex.abc.music.service.meta;

import abc.music.core.domain.Project;

/**
 * Created 2016-dec-03
 *
 * @author hl
 */
public class MetaService {

    private static final AppInfo appInfo = new AppInfo();
    private final Project project;

    public MetaService(Project project) {
        this.project = project;
    }

    public static AppInfo getAppInfo() {
        return appInfo;
    }
}
